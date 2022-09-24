package net.lemeow.iamod.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.lemeow.iamod.item.ModItemGroup;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Item.class)
public class ModItemMixin implements ItemConvertible, FabricItem {



    @Inject(
            method = "damage",
            at = @At("HEAD"),
            cancellable = true
    )
    public void damage(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        Item thisItem = (Item)(Object)this;
        boolean isExplosiveProof = thisItem.getGroup() != null && Objects.equals(thisItem.getGroup().getName(), ModItemGroup.VOID_QUARTZ.getName());
        cir.setReturnValue(
                (!thisItem.isFireproof() || !source.isFire())&&(!isExplosiveProof||!source.isExplosive())
        );
    }


    @Override
    public Item asItem() {
        return (Item)(Object)this;
    }
}
