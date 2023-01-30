package net.lemeow.iamod.item.custom;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

/**
 * A custom tool, currently not used but could be in the future for edits, mostly for tooltips.
 */
public class ModSword extends SwordItem {
    public ModSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
