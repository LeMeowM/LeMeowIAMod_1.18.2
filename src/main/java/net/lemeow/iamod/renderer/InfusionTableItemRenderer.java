package net.lemeow.iamod.renderer;

import net.lemeow.iamod.block.entity.InfusionTableBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3f;


public class InfusionTableItemRenderer implements BlockEntityRenderer<InfusionTableBlockEntity> {



    public InfusionTableItemRenderer(BlockEntityRendererFactory.Context ctx){

    }

    @Override
    public void render(InfusionTableBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        DefaultedList<ItemStack> fuel;
        ItemStack centre;
        fuel = entity.getFuel();
        centre = entity.getStack(4);
        double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;





        int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
        matrices.translate(0.25,0.9+offset/2, 0.91);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((entity.getWorld().getTime() + tickDelta) * 4));
        MinecraftClient.getInstance().getItemRenderer().renderItem(fuel.get(0), ModelTransformation.Mode.GUI, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);


        matrices.translate(-0.25,0, 0);
        MinecraftClient.getInstance().getItemRenderer().renderItem(centre, ModelTransformation.Mode.GUI, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);


        matrices.pop();


    }
}
