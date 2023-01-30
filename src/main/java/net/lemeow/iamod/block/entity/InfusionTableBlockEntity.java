package net.lemeow.iamod.block.entity;



import net.lemeow.iamod.item.inventory.ImplementedInventory;
import net.lemeow.iamod.recipe.InfusionTableRecipe;
import net.lemeow.iamod.screen.infusiontable.InfusionTableScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
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

/**
 * Is created for every {@link net.lemeow.iamod.block.custom.InfusionTableBlock}
 * Contains the inventory of the block which needs to be queried for the screen to be made.
 */
public class InfusionTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {


    /**
     * i should probably change this to a defaulted list of itemstacks but this is how i built it, changing it
     * is a pain in the ass.
     * i do not like the hardcoding of the size but i guess it has to be like that somewhat...
     */
    private final SimpleInventory inventory =
            new SimpleInventory(5);

    /**
     * this is the properties that are being cached and changed in the block like fuel progress and the maximum progress
     */
    protected final PropertyDelegate propertyDelegate;

    /**
     * should probably make this more specific and maybe add a way to
     * cache the item being infused so you can't hotswap them
     */
    private int progress = 0;
    /**
     * in ticks, this seems absurdly short...
     */
    private int maxProgress = 60;

    /**
     * Sets the slot of the inventory with the ItemStack
     * @param slot the slot
     * @param itemStack the stack
     */
    @Override
    public void setStack(int slot, ItemStack itemStack){
        this.inventory.setStack(slot, itemStack);
    }

    /**
     * Constructor, used to place new blocks of this type in the world,
     * if I add a new property, I need to add to the get and set in here.
     * @param pos Position where this block entity is placed
     * @param state The BlockState of the block placed, usually meant for blocks with multiple states, not this one
     */
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

    // this is so beyond stupid but SimpleInventory doesnt have a getStacks() command

    /**
     * A way to get items from the inventory inside the BlockEntity, why does SimpleInventory not have a getItems() itself???
     * who knows
     * @return The inventory inside the block entity, NOT the SimpleInventory but a usable list.
     */
    @Override
    public DefaultedList<ItemStack> getItems() {
        DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for(int i = 0; i< inventory.size();i++){
            itemStacks.set(i, inventory.getStack(i));
        }

        return itemStacks;
    }

    /**
     * A way to get FUEL but not the rest from the inventory inside the BlockEntity, why does SimpleInventory not have a getItems() itself???
     * who knows
     * @return The fuel inside the block entity, NOT the SimpleInventory but a usable list.
     */
    public DefaultedList<ItemStack> getFuel() {
        DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for(int i = 0; i< inventory.size()-1;i++){
            itemStacks.set(i, inventory.getStack(i));
        }

        return itemStacks;
    }



    /**
     * Functional literal text instead of a translatable text for the display of the item, should probably be refactored
     * to use translatable text.
     * @return "Infusion Table" in a manner that can't be translated
     */
    @Override
    public Text getDisplayName() {
        return new LiteralText("Infusion Table");
    }

    /**
     * Creates a New {@link  InfusionTableScreenHandler} for GUI use
     * @param syncId ID for the GUI so that they are indexable
     * @param inv The player's inventory
     * @param player The player opening the GUI
     */
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new InfusionTableScreenHandler(syncId, inv, this.inventory, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.getItems());
        nbt.putInt("infusion.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        DefaultedList<ItemStack> inv = DefaultedList.ofSize(5, ItemStack.EMPTY);
        Inventories.readNbt(nbt, inv);
        for(int i=0; i<inv.size();i++){
            this.setStack(i, inv.get(i));
        }
        progress = nbt.getInt("infusion.progress");

    }

    public static Optional<InfusionTableRecipe> getMatchedRecipe(InfusionTableBlockEntity entity){
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i< entity.inventory.size(); i++){
            inventory.setStack(i, entity.getStack(i));
        }
        return world.getRecipeManager()
                .getFirstMatch(InfusionTableRecipe.Type.INSTANCE, inventory, world);
    }


    public static void tick(World world, BlockPos pos, BlockState state, InfusionTableBlockEntity entity) {

        if(getMatchedRecipe(entity).isPresent()){
            entity.progress++;
            if (entity.progress > entity.maxProgress) {
                craftItem(entity);
            }
        } else entity.resetProgress();

        // dropping items if the block is broken, this doesnt seem very efficient but its ok
        // i should change this to use the stacks inside of SimpleInventory instead
        if(entity.removed){
            for(ItemStack itemStack: entity.getItems()){
                Block.dropStack(world, pos, itemStack);
            }
        }

    }



    public static void craftItem(InfusionTableBlockEntity entity){
        // idk why this is necessary but without it the world sometimes crashes
        World world = entity.world;
        if(world==null) return;


        // nbt to make sure enchanted and named items stay that way

        ItemStack output = new ItemStack(getMatchedRecipe(entity).get().getOutput().getItem(),
                1);
        if(entity.getStack(4).getNbt() != null) {
            NbtCompound nbt = entity.getStack(4).getNbt();
            output.setNbt(nbt);
        }

        // removes fuel
        for(int i = 0; i<4; i++) {
            entity.removeStack(i, 1);
        }

        // replacing the item
        entity.removeStack(4, 1);

        // THIS DOESNT WORK??? WHYYYYYYY
        entity.setStack(4, output);

        // resetting the infusion progress
        entity.resetProgress();
        entity.markDirty();
    }

    private void resetProgress() {
        this.progress = 0;
    }

}
