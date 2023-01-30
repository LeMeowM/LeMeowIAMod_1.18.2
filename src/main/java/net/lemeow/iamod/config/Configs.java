package net.lemeow.iamod.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lemeow.iamod.IAMod;
import ru.bclib.BCLib;
import ru.bclib.config.EntryConfig;
import ru.bclib.config.IdConfig;
import ru.bclib.config.PathConfig;

/**
 * I have to rewrite this, mainly because I don't have enough configs in right now
 */
public class Configs {
    public static final IdConfig BIOME_CONFIG = new EntryConfig(IAMod.MOD_ID, "biomes");
    public static final PathConfig GENERATOR_CONFIG = new PathConfig(IAMod.MOD_ID, "generator", false);

    @Environment(value = EnvType.CLIENT)
    public static final PathConfig CLIENT_CONFIG = new PathConfig(IAMod.MOD_ID, "client", false);

    public static void saveConfigs(){
        BIOME_CONFIG.saveChanges();
        GENERATOR_CONFIG.saveChanges();

        if(BCLib.isClient()) {
            CLIENT_CONFIG.saveChanges();
        }
    }


}
