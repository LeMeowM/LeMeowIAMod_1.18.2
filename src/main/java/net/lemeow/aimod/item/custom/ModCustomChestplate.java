package net.lemeow.aimod.item.custom;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;


public class ModCustomChestplate extends ArmorItem implements EntityElytraEvents.Allow {



    public ModCustomChestplate(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }


    @Override
    public boolean allowElytraFlight(LivingEntity entity) {
        return true;
    }
}
