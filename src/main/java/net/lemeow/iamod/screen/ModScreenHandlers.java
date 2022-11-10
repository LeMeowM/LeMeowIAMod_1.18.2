package net.lemeow.iamod.screen;


import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.lemeow.iamod.IAMod;

import net.lemeow.iamod.screen.infusiontable.InfusionTableScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<InfusionTableScreenHandler> INFUSION_TABLE_SCREEN_HANDLER;
    public static ScreenHandlerType<MassCraftingScreenHandler> MASS_CRAFTING_TABLE_HANDLER;

    public static void registerAllScreenHandlers(){
        INFUSION_TABLE_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(IAMod.MOD_ID, "infusion_table"),
                        InfusionTableScreenHandler::new);

        MASS_CRAFTING_TABLE_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(IAMod.MOD_ID, "mass_crafting_table"),
                        MassCraftingScreenHandler::new);

    }

}
