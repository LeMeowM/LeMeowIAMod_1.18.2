package net.lemeow.aimod.block.entity;


import net.lemeow.aimod.item.ModItems;
import net.lemeow.aimod.item.inventory.ImplementedInventory;
import net.lemeow.aimod.recipe.InfusionTableRecipe;
import net.lemeow.aimod.screen.InfusionTableScreenHandler;
import net.lemeow.aimod.screen.slot.ModResultSlot;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
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

import java.util.Optional;

public class InfusionTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 6000;


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
                if(ModResultSlot.validInputSources.contains(entity.inventory.get(4).getItem())){
                    entity.progress++;
                    if (entity.progress > entity.maxProgress) {
                        craftItem(entity, entity.inventory.get(4).getItem());
                    }
                }
            }
        else entity.resetProgress();
    }




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
        entity.removeStack(4, 1);

        entity.setStack(4, new ItemStack(ModResultSlot.IODictionary.get(input),
                1));

        entity.resetProgress();
    }

    private void resetProgress() {
        this.progress = 0;
    }





}
