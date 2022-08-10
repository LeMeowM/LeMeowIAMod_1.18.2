package net.lemeow.aimod.block.entity;



import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.item.inventory.ImplementedInventory;
import net.lemeow.aimod.recipe.InfusionTableRecipe;
import net.lemeow.aimod.screen.InfusionTableScreenHandler;
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

// THIS IS UTTERLY BROKEN OH DEAR GOD I NEED TO FIX THIS -> hasRecipe doesnt work for some reason,
// it seems getFirstMatch() isnt working correctly
public class InfusionTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {


    private final SimpleInventory inventory =
            new SimpleInventory(5);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 60;

    @Override
    public void setStack(int i, ItemStack itemStack){
        this.inventory.setStack(i, itemStack);
    }


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
    @Override
    public DefaultedList<ItemStack> getItems() {
        DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for(int i = 0; i< inventory.size();i++){
            itemStacks.set(i, inventory.getStack(i));
        }

        return itemStacks;
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Infusion Table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new InfusionTableScreenHandler(syncId, inv, this.inventory, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, getItems());
        nbt.putInt("infusion.progress", progress);


    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, getItems());
        super.readNbt(nbt);
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
    }

    private void resetProgress() {
        this.progress = 0;
    }

}
