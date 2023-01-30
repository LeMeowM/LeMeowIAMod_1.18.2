package net.lemeow.iamod.screen.infusiontable;



import net.lemeow.iamod.screen.ModScreenHandlers;
import net.lemeow.iamod.screen.slot.ModResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;


public class InfusionTableScreenHandler extends ScreenHandler {
    private final SimpleInventory inventory;
    private final PropertyDelegate propertyDelegate;


    public InfusionTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(5), new ArrayPropertyDelegate(2));
    }

    // tested this works fine
    public InfusionTableScreenHandler(int syncId, PlayerInventory playerInventory,
                                      SimpleInventory inventory, PropertyDelegate delegate) {
        super(ModScreenHandlers.INFUSION_TABLE_SCREEN_HANDLER, syncId);

        checkSize(inventory, 4);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;

        // Creating the GUI


        this.addSlot(new Slot(inventory, 0, 80, 22));
        this.addSlot(new Slot(inventory, 1, 34, 68));
        this.addSlot(new Slot(inventory, 2, 80, 114));
        this.addSlot(new Slot(inventory, 3, 126, 68));
        this.addSlot(new ModResultSlot(inventory, 4, 80, 68));


        addPlayerInvetory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(delegate);

    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }


    // this is to move blocks from one place to another
    // todo: shift clicking a stack into the last stack doesnt work as intended.
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        // gets the slot of index invSlot
        Slot slot = this.slots.get(invSlot);
        // creates a new empty stack -> the one that will be replacing the stack in said index slot
        ItemStack newStack = ItemStack.EMPTY;
        // checks if the slot that we're trying to move to exists and if that slot already has items, if both yes
        if(slot != null && slot.hasStack()){
            ItemStack ogStack = slot.getStack();
            newStack = ogStack.copy();
            if(invSlot < this.inventory.size()-1 && !this.insertItem(ogStack, this.inventory.size(), this.slots.size(), true)){
                return ItemStack.EMPTY;
            }
            /**
            else if(invSlot == this.inventory.size()-1 && ogStack.getMaxCount()==1 && ){

            }
             **/
            else if(!this.insertItem(ogStack, 0, this.slots.size(), false)) return ItemStack.EMPTY;

            if (ogStack.isEmpty()){
                slot.setStack(ItemStack.EMPTY);
            }
            else slot.markDirty();

        }
        return newStack;
    }

    private void addPlayerInvetory(PlayerInventory playerInventory){
        for(int i = 0; i<3; ++i){
            for(int j = 0; j<9; ++j){
                // never trust code you found in the mc source
                this.addSlot(new Slot(playerInventory, j+i*9+9, 8+j*18, 151+i*18));
            }
        }

    }

    private void addPlayerHotbar(PlayerInventory playerInventory){
        for(int i = 0; i<9; ++i){
            // why is it like this????
            this.addSlot(new Slot(playerInventory, i, 8+i*18, 209));
        }
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 22; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public Item getResultSlot(){
        return inventory.getStack(4).getItem();
    }






}
