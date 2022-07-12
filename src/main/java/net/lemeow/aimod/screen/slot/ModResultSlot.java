package net.lemeow.aimod.screen.slot;

import net.lemeow.aimod.block.entity.InfusionTableBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;



public class ModResultSlot extends Slot {
    public ModResultSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }



    public boolean canInsert(ItemStack stack){
        return InfusionTableBlockEntity.validInputSources.contains(stack.getItem())||stack.isItemEqual(new ItemStack(Items.NETHERITE_INGOT));
    }


    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
