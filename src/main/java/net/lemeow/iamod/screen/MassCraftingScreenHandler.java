package net.lemeow.iamod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.CraftingResultSlot;


// the idea is so that when i put in shulker boxes of items, it crafts a shulker box of items, maybe i should do this as
// an extention for the crafting table, but there are certain things i want to add
public class MassCraftingScreenHandler extends AbstractRecipeScreenHandler<CraftingInventory>{
    public final CraftingInventory input;
    private final CraftingResultInventory result;
    private final ScreenHandlerContext context;
    private final PlayerEntity player;

    public MassCraftingScreenHandler(int syncId, PlayerInventory playerInventory){
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }




    public MassCraftingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.MASS_CRAFTING_TABLE_HANDLER, syncId);
        this.input = new CraftingInventory(this, 3, 3);
        this.result = new CraftingResultInventory();
        this.context = context;
        this.player = playerInventory.player;
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.input, this.result, 0, 124, 35));



    }


    @Override
    public void populateRecipeFinder(RecipeMatcher finder) {

    }

    @Override
    public void clearCraftingSlots() {

    }

    @Override
    public boolean matches(Recipe<? super CraftingInventory> recipe) {
        return false;
    }

    @Override
    public int getCraftingResultSlotIndex() {
        return 0;
    }

    @Override
    public int getCraftingWidth() {
        return 0;
    }

    @Override
    public int getCraftingHeight() {
        return 0;
    }

    @Override
    public int getCraftingSlotCount() {
        return 0;
    }

    @Override
    public RecipeBookCategory getCategory() {
        return null;
    }

    @Override
    public boolean canInsertIntoSlot(int index) {
        return false;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }
}
