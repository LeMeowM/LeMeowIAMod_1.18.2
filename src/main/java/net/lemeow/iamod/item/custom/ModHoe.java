package net.lemeow.iamod.item.custom;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

/**
 * A custom tool, currently not used but could be in the future for edits, mostly for tooltips.
 */
public class ModHoe extends HoeItem {
    public ModHoe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
