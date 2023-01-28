package net.lemeow.iamod.item.custom;

import net.lemeow.iamod.IAMod;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
// todo: change it so its not kill and just do more damage
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
        float dYaw;
        if(!target.isPlayer()) {
            dYaw = (Math.abs(target.bodyYaw-attacker.getYaw()));
            IAMod.LOGGER.warn(dYaw+"");
        }
        else{
            dYaw = (Math.abs(target.getYaw()-attacker.getYaw()));
            IAMod.LOGGER.warn(dYaw+"");
        }

        if (dYaw < 90 || dYaw > 270) {
            target.damage(DamageSource.mob(attacker),
                    super.getAttackDamage());
            return true;
        }
        else if(dYaw > 90 && dYaw < 270){
            return true;
        }
        else {
            IAMod.LOGGER.error("Knife stabbed something that is neither a player nor not a player, you are not supposed to be here");
        }
        return false;
    }
}
