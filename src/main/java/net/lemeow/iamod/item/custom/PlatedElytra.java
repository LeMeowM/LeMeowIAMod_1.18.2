package net.lemeow.iamod.item.custom;

import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class PlatedElytra extends ArmorItem implements FabricElytraItem {

    public PlatedElytra(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }


}