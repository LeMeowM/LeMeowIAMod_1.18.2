package net.lemeow.aimod.screen.slot;

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

    public static List<Item> validOutputSources = List.of(
            ModItems.VOID_QUARTZ_AXE,
            ModItems.VOID_QUARTZ_CHESTPLATE,
            ModItems.VOID_QUARTZ_HOE,
            ModItems.VOID_QUARTZ_PICKAXE,
            ModItems.VOID_QUARTZ_HELMET,
            ModItems.VOID_QUARTZ_BOOTS,
            ModItems.VOID_QUARTZ_INGOT,
            ModItems.VOID_QUARTZ_LEGGINGS,
            ModItems.VOID_QUARTZ_SHOVEL,
            ModItems.VOID_QUARTZ_SWORD
    );

    public static Dictionary<Item, Item> IODictionary = (Dictionary<Item, Item>)
            createDictionary(validInputSources, validOutputSources);

    public static Dictionary<?,?> createDictionary(List<?> list1, List<?> list2){
        Dictionary<Object, Object> output = new Hashtable<>();
        if(list1.size()!=list2.size()) return output;
        for(int i = 0; i<list1.size(); i++){
            output.put(list1.get(i), list2.get(i));
        }
        return output;
    }




}
