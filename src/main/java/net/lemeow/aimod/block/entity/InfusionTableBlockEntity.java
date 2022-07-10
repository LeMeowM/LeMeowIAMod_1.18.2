package net.lemeow.aimod.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.lemeow.aimod.item.inventory.ImplementedInventory;
import net.lemeow.aimod.recipe.InfusionTableRecipe;
import net.lemeow.aimod.screen.InfusionTableScreenHandler;
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

public class InfusionTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(5, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 0;


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
        System.out.println("creating menu");
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


    public static void tick(World world, BlockPos pos, BlockState state, InfusionTableBlockEntity entity) {

        if(hasRecipe(entity)){
            entity.progress++;
            if(entity.progress> entity.maxProgress){
                craftItem(entity);
            }
        }
        else entity.resetProgress();
    }



    private static boolean hasRecipe(InfusionTableBlockEntity entity) {
        World world = entity.world;
        if(world==null) return false;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        Optional<InfusionTableRecipe> match = world.getRecipeManager()
                .getFirstMatch(InfusionTableRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(5).getItem() == output.getItem() || inventory.getStack(5).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(5).getMaxCount() > inventory.getStack(5).getCount();
    }

    private static void craftItem(InfusionTableBlockEntity entity) {
        World world = entity.world;
        if(world==null) return;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        Optional<InfusionTableRecipe> match = world.getRecipeManager()
                .getFirstMatch(InfusionTableRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            for(int i = 0; i<4; i++) {
                entity.removeStack(i, 1);
            }

            entity.setStack(5, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(5).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }





}
