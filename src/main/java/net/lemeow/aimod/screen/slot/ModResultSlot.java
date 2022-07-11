package net.lemeow.aimod.screen.slot;

import net.lemeow.aimod.block.entity.InfusionTableBlockEntity;
import net.lemeow.aimod.item.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ModResultSlot extends Slot {
    public ModResultSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }



    public boolean canInsert(ItemStack stack){
        return InfusionTableBlockEntity.validInputSources.contains(stack.getItem());
    }


    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
