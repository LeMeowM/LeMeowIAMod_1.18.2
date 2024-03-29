package net.lemeow.iamod.mixin;

import net.lemeow.iamod.item.custom.PlatedElytra;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Lord Almighty may you have mercy on me, this has got to be the most scuffed render shim ive ever done...
 */
@Mixin(ElytraFeatureRenderer.class)
public class PlatedElytraRendererMixin {
    @Redirect(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            )
    )
    private boolean acceptPlatedElytra(ItemStack itemStack, Item item){
        return itemStack.isOf(item)||itemStack.getItem() instanceof PlatedElytra;
    }

}
