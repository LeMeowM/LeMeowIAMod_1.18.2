package net.lemeow.aimod.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

import java.util.List;

public class ModResultSlot extends Slot {
    public ModResultSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }



    public boolean canInsert(ItemStack stack){
        return validInputSources.contains(stack.getItem());
    }


    @Override
    public int getMaxItemCount() {
        return 1;
    }

    public static List<Item> validInputSources = List.of(
            Items.NETHERITE_AXE,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_HOE,
            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_HELMET,
            Items.NETHERITE_BOOTS,
            Items.NETHERITE_INGOT,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_SHOVEL,
            Items.NETHERITE_SWORD
    );



}
