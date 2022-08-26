package net.lemeow.iamod.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.lemeow.iamod.IAMod;
import net.lemeow.iamod.item.ModItems;

public class ModRegistries {
    public static void registerModStuff(){
        registerFuels();


    }

    private static void registerFuels(){
        IAMod.LOGGER.info("Registering Fuels for "+ IAMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.VOID_QUARTZ_SHARD, 6000);
        registry.add(ModItems.VOID_QUARTZ_INGOT, 9000);

    }


}
