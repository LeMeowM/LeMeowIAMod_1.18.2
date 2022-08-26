package net.lemeow.iamod.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;



public class ModResultSlot extends Slot {
    public ModResultSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }



    // jesus kill me for this sin, but at least i dont need to care about it
    public boolean canInsert(ItemStack stack){
        return true;
    }


    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
