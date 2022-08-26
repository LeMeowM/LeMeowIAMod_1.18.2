package net.lemeow.iamod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.lemeow.iamod.screen.InfusionTableScreen;
import net.lemeow.iamod.screen.ModScreenHandlers;

public class IAModClientMod implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

        ScreenRegistry.register(ModScreenHandlers.INFUSION_TABLE_SCREEN_HANDLER, InfusionTableScreen::new);


    }
}
