package net.lemeow.iamod.mixin;


import net.lemeow.iamod.IAMod;
import net.lemeow.iamod.item.ModItems;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SheepEntity.class)
public class MixinSheepEntity {

    @Inject(
            at = @At("HEAD"),
            method = "interactMob",
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            cancellable = true
    )
    private void iamodMilkSheep(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        IAMod.LOGGER.info("Injected into SheepEntity");
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BUCKET) && !((SheepEntity)(Object)this).isBaby()) {
            player.playSound(SoundEvents.ENTITY_GOAT_MILK, 1.0F, 1.0F);
            ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, ModItems.SHEEP_MILK_BUCKET.getDefaultStack());
            player.setStackInHand(hand, itemStack2);
            cir.setReturnValue(ActionResult.success(((SheepEntity)(Object)this).world.isClient));
        }
    }



}
