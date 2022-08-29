package net.lemeow.iamod.item.custom;

import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

// this is absurdly stupid, i dont know how i didnt think of this
public class PlatedElytra extends ArmorItem implements FabricElytraItem {

    public PlatedElytra(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

}