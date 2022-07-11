package net.lemeow.aimod.block.entity;


import net.lemeow.aimod.item.ModItems;
import net.lemeow.aimod.item.inventory.ImplementedInventory;
import net.lemeow.aimod.screen.InfusionTableScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class InfusionTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 60;


    public InfusionTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INFUSION_TABLE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> InfusionTableBlockEntity.this.progress;
                    case 1 -> InfusionTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> InfusionTableBlockEntity.this.progress = value;
                    case 1 -> InfusionTableBlockEntity.this.maxProgress = value;
                }
            }
            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Infusion Table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new InfusionTableScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("infusion.progress", progress);


    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("infusion.progress");

    }

    // for hardcoding ingots
    public boolean isFueledByItem(Item item){
        for(int i = 0; i<4; i++){
            if(!inventory.get(i).isOf(item)) return false;
        }
        return true;
    }
    // inventory.get(4).isOf(Items.NETHERITE_INGOT)

    public static void tick(World world, BlockPos pos, BlockState state, InfusionTableBlockEntity entity) {
        // hardcode crafting ingots
        if (entity.isFueledByItem(ModItems.VOID_QUARTZ_SHARD)) {
            if(entity.inventory.get(4).isOf(Items.NETHERITE_INGOT)) {
                entity.progress++;
                if (entity.progress > entity.maxProgress) {
                    craftIngot(entity);
                }
            }
        } else if (entity.isFueledByItem(ModItems.VOID_QUARTZ_INGOT)) {
                if(validInputSources.contains(entity.inventory.get(4).getItem())){
                    entity.progress++;
                    if (entity.progress > entity.maxProgress) {
                        craftItem(entity, entity.inventory.get(4).getItem());
                    }
                }
            }
        else entity.resetProgress();
    }



    // this is a terrible way of doing it but i dont want to work with json anymore ;-;
    // i cannot for the life of me get the recipefactory to work so this is what i will be doing
    public static void craftIngot(InfusionTableBlockEntity entity){
        World world = entity.world;
        if(world==null) return;

        for(int i = 0; i<4; i++) {
            entity.removeStack(i, 1);
        }
        entity.removeStack(4, 1);

        entity.setStack(4, new ItemStack(ModItems.VOID_QUARTZ_INGOT,
                1));

        entity.resetProgress();
        }


    public static void craftItem(InfusionTableBlockEntity entity, Item input){
        World world = entity.world;
        if(world==null) return;

        for(int i = 0; i<4; i++) {
            entity.removeStack(i, 1);
        }
        // nbt to make sure enchanted and named items stay that way
        NbtCompound nbt =  entity.getStack(4).getNbt();
        ItemStack output = new ItemStack(IODictionary.get(input),
                1);
        output.setNbt(nbt);

        // replacing the item
        entity.removeStack(4, 1);
        entity.setStack(4, output);

        // resetting the infusion progress
        entity.resetProgress();
    }

    private void resetProgress() {
        this.progress = 0;
    }


    // necessary evils for hardcoding and not absurdly hardcoding
    // order matters due to the IODictionary being created in order
    public static List<Item> validInputSources = List.of(
            Items.NETHERITE_AXE,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_HOE,
            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_HELMET,
            Items.NETHERITE_BOOTS,
            Items.NETHERITE_INGOT,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_SHOVEL,
            Items.NETHERITE_SWORD
    );

    public static List<Item> validOutputSources = List.of(
            ModItems.VOID_QUARTZ_AXE,
            ModItems.VOID_QUARTZ_CHESTPLATE,
            ModItems.VOID_QUARTZ_HOE,
            ModItems.VOID_QUARTZ_PICKAXE,
            ModItems.VOID_QUARTZ_HELMET,
            ModItems.VOID_QUARTZ_BOOTS,
            ModItems.VOID_QUARTZ_INGOT,
            ModItems.VOID_QUARTZ_LEGGINGS,
            ModItems.VOID_QUARTZ_SHOVEL,
            ModItems.VOID_QUARTZ_SWORD
    );

    public static Dictionary<Item, Item> IODictionary = (Dictionary<Item, Item>)
            createDictionary(validInputSources, validOutputSources);

    public static Dictionary<?,?> createDictionary(List<?> list1, List<?> list2){
        Dictionary<Object, Object> output = new Hashtable<>();
        if(list1.size()!=list2.size()) return output;
        for(int i = 0; i<list1.size(); i++){
            output.put(list1.get(i), list2.get(i));
        }
        return output;
    }





}
