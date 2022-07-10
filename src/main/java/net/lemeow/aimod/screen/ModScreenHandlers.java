package net.lemeow.aimod.screen;


import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.lemeow.aimod.AIMod;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<InfusionTableScreenHandler> INFUSION_TABLE_SCREEN_HANDLER;

    public static void registerAllScreenHandlers(){
        INFUSION_TABLE_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(AIMod.MOD_ID, "infusion_table"),
                        InfusionTableScreenHandler::new);
    }

}
