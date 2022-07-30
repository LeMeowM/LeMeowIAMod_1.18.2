package net.lemeow.aimod.world.biome;

import net.lemeow.aimod.config.Configs;
import net.lemeow.aimod.world.biome.land.EndForestBiome;
import net.lemeow.aimod.world.gen.ModBiomeGeneration;
import net.lemeow.aimod.world.generator.EndGeneratorOptions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import ru.bclib.api.LifeCycleAPI;
import ru.bclib.api.biomes.BiomeAPI;
import ru.bclib.world.biomes.BCLBiome;
import ru.bclib.world.generator.BiomePicker;
import ru.bclib.world.generator.BiomeType;
import ru.bclib.world.generator.map.hex.HexBiomeMap;

public class ModEndBiomes {

    public static final BiomePicker CAVE_BIOMES = new BiomePicker();
    private static HexBiomeMap caveBiomeMap;
    private static long lastSeed;



    public static final ModBiomeGeneration END_FOREST = registerBiome(new EndForestBiome(), BiomeType.LAND);


    public static void register() {
        LifeCycleAPI.onLevelLoad(ModEndBiomes::onWorldLoad);
    }

    private static void onWorldLoad(ServerWorld level, long seed, Registry<Biome> registry) {
        CAVE_BIOMES.getBiomes().forEach(biome -> biome.updateActualBiomes(registry));
        CAVE_BIOMES.rebuild();
        if (caveBiomeMap == null || lastSeed != seed) {
            caveBiomeMap = new HexBiomeMap(seed, EndGeneratorOptions.getBiomeSizeCaves(), CAVE_BIOMES);
            lastSeed = seed;
        }
    }


    public static ModBiomeGeneration registerSubBiome(ModBiomeGeneration.Config biomeConfig, ModBiomeGeneration parent) {
        final ModBiomeGeneration biome = ModBiomeGeneration.create(biomeConfig);

        if (Configs.BIOME_CONFIG.getBoolean(biome.getID(), "enabled", true)) {
            BiomeAPI.registerSubBiome(parent, biome);
        }
        return biome;
    }


    public static ModBiomeGeneration registerBiome(ModBiomeGeneration.Config biomeConfig, BiomeType type) {
        final ModBiomeGeneration biome = ModBiomeGeneration.create(biomeConfig);
        if (Configs.BIOME_CONFIG.getBoolean(biome.getID(), "enabled", true)) {
            if (type == BiomeType.LAND) {
                BiomeAPI.registerEndLandBiome(biome);
            }
            else {
                BiomeAPI.registerEndVoidBiome(biome);
            }
        }
        return biome;
    }


    public static ModBiomeGeneration registerSubBiomeIntegration(ModBiomeGeneration.Config biomeConfig) {
        ModBiomeGeneration biome = ModBiomeGeneration.create(biomeConfig);
        if (Configs.BIOME_CONFIG.getBoolean(biome.getID(), "enabled", true)) {
            BiomeAPI.registerBiome(biome);
        }
        return biome;
    }


    public static void addSubBiomeIntegration(ModBiomeGeneration biome, Identifier parent) {
        if (Configs.BIOME_CONFIG.getBoolean(biome.getID(), "enabled", true)) {
            BCLBiome parentBiome = BiomeAPI.getBiome(parent);
            if (parentBiome != null && !parentBiome.containsSubBiome(biome)) {
                parentBiome.addSubBiome(biome);
            }
        }
    }


    // sneak peek!!
    /*
    public static EndCaveBiome registerCaveBiome(EndCaveBiome.Config biomeConfig) {
        final EndCaveBiome biome = EndCaveBiome.create(biomeConfig);
        if (Configs.BIOME_CONFIG.getBoolean(biome.getID(), "enabled", true)) {
            BiomeAPI.registerBiome(biome);
            CAVE_BIOMES.addBiome(biome);
        }
        return biome;
    }

    public static EndCaveBiome getCaveBiome(int x, int z) {
        return (EndCaveBiome) caveBiomeMap.getBiome(x, 5, z);
    }
     */

}
