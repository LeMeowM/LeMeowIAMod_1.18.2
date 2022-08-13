package net.lemeow.aimod.mixin;

import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GoatEntity.class)
public class MixinGoatEntity  {

    private Hand capturedHand;




    @Inject(
            at = @At(
                target = "Lnet/minecraft/entity/player/PlayerEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V",
                    value = "INVOKE"
            ),
            method = "interactMob",
            locals = LocalCapture.CAPTURE_FAILEXCEPTION

    )
    private void aimodSetCapturedHand(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        capturedHand = hand;
    }


    @Redirect(
            method = "interactMob",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"
            )
    )
    private ItemStack aimodMilkGoat(ItemStack inputStack, PlayerEntity player, ItemStack outputStack){
        AIMod.LOGGER.info("Injected into GoatEntity");
        return ItemUsage.exchangeStack(player.getStackInHand(capturedHand), player, ModItems.GOAT_MILK_BUCKET.getDefaultStack());
    };


}
