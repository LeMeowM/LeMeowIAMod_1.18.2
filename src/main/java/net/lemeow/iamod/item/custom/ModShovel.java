package net.lemeow.iamod.item.custom;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

/**
 * A custom tool, currently not used but could be in the future for edits, mostly for tooltips.
 */
public class ModShovel extends ShovelItem{
    public ModShovel(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
