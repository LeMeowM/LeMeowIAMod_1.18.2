package net.lemeow.iamod.item.custom;

import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

// this is absurdly stupid, i dont know how i didnt think of this

/**
 * This works as an ARMOR AND an ELYTRA at the same time, elytra is an interface so nothing is wrong.
 * I cannot actually get the renderer to work because the renderer was hardcoded, so the behaviour is fixed in
 * {@link net.lemeow.iamod.mixin.PlatedElytraRendererMixin} where I add all items who are of this to the list to be rendered
 */
public class PlatedElytra extends ArmorItem implements FabricElytraItem {

    public PlatedElytra(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

}