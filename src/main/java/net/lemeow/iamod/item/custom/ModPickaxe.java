package net.lemeow.iamod.item.custom;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

/**
 * A custom tool, currently not used but could be in the future for edits, mostly for tooltips.
 */
public class ModPickaxe extends PickaxeItem {
    public ModPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }


}
