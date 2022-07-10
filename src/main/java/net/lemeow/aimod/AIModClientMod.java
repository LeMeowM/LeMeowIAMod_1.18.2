package net.lemeow.aimod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.lemeow.aimod.screen.InfusionTableScreen;
import net.lemeow.aimod.screen.ModScreenHandlers;

public class AIModClientMod implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

        ScreenRegistry.register(ModScreenHandlers.INFUSION_TABLE_SCREEN_HANDLER, InfusionTableScreen::new);


    }
}
