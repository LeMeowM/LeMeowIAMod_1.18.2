package net.lemeow.iamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.lemeow.iamod.block.ModBlocks;
import net.lemeow.iamod.screen.InfusionTableScreen;
import net.lemeow.iamod.screen.ModScreenHandlers;
import net.minecraft.client.render.RenderLayer;

public class IAModClientMod implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

        ScreenRegistry.register(ModScreenHandlers.INFUSION_TABLE_SCREEN_HANDLER, InfusionTableScreen::new);


        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFUSION_TABLE, RenderLayer.getCutout());




    }
}
