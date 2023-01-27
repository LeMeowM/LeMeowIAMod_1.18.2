package net.lemeow.iamod.item.custom;

import net.lemeow.iamod.IAMod;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ModKnife extends SwordItem {
    public ModKnife(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

        if(target.getWorld() == null){
            return true;
        }
        float dYaw = (Math.abs(target.bodyYaw-attacker.getYaw()));
        IAMod.LOGGER.warn(dYaw+"");
        if(dYaw<90||dYaw>270){
            target.kill(); // CHANGE THIS TO DAMAGE SCALED ON TOOL MATERIAL
        }

        return true;
    }
}
