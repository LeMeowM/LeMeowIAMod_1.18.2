package net.lemeow.aimod.screen.slot;

import net.lemeow.aimod.item.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ModFuelSlot extends Slot {
    public ModFuelSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isOf(ModItems.VOID_QUARTZ_SHARD)||stack.isOf(ModItems.VOID_QUARTZ_INGOT);
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return 64;
    }
}
