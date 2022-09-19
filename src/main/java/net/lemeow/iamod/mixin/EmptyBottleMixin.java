package net.lemeow.iamod.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public class EmptyBottleMixin {
    @Inject(
            method = "use",
            at = @At(

                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true)
    public void airBottleFunctionality(World world, PlayerEntity user, Hand hand,
                                       CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        ItemStack itemStack = user.getStackInHand(hand);
        if(user.isSubmergedInWater()) {
            user.setAir(Math.min(user.getMaxAir(), user.getAir() + 40));
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_DRINK,
                    SoundCategory.NEUTRAL, 1.0F, 1.0F);
            if (itemStack.getCount() < 2) {
                cir.setReturnValue(TypedActionResult.success(ItemUsage.exchangeStack(itemStack, user,
                        PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER))));
            } else {
                itemStack.decrement(1);
                if(!user.getInventory().insertStack(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER))){
                    user.dropItem(PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER), false);
                }

                cir.setReturnValue(TypedActionResult.success(itemStack));
            }
        }
    }


}
