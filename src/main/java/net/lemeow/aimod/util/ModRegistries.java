package net.lemeow.aimod.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.item.ModItems;

public class ModRegistries {
    public static void registerModStuff(){
        registerFuels();


    }

    private static void registerFuels(){
        AIMod.LOGGER.info("Registering Fuels for "+ AIMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.VOID_QUARTZ_SHARD, 6000);
        registry.add(ModItems.VOID_QUARTZ_INGOT, 9000);

    }


}
