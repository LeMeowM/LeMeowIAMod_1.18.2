package net.lemeow.aimod.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lemeow.aimod.AIMod;
import ru.bclib.BCLib;
import ru.bclib.config.EntryConfig;
import ru.bclib.config.IdConfig;
import ru.bclib.config.PathConfig;

public class Configs {
    public static final IdConfig BIOME_CONFIG = new EntryConfig(AIMod.MOD_ID, "biomes");
    public static final PathConfig GENERATOR_CONFIG = new PathConfig(AIMod.MOD_ID, "generator", false);

    @Environment(value = EnvType.CLIENT)
    public static final PathConfig CLIENT_CONFIG = new PathConfig(AIMod.MOD_ID, "client", false);

    public static void saveConfigs(){
        BIOME_CONFIG.saveChanges();
        GENERATOR_CONFIG.saveChanges();

        if(BCLib.isClient()) {
            CLIENT_CONFIG.saveChanges();
        }
    }


}
