package net.lemeow.iamod.mixin;


import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ItemEntity.class)
public class ItemDamageMixin {


    @Inject(
            method = "damage",
            at = @At(
                    "HEAD"
            ),
            cancellable = true
    )
    public void damageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if(!((ItemEntity)(Object)this).getStack().isEmpty() && Objects.equals(Objects.requireNonNull(((ItemEntity) (Object) this).getStack().getItem().getGroup()).getName(), "VOID_QUARTZ") && source.isExplosive()){
            cir.setReturnValue(false);
        }
    }


}
