package net.lemeow.aimod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.world.feature.ModPlacedFeatures;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres(){
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.END_VOID_ORE_PLACED.getKey().get());
    }

    public static void registerConfiguredFeatures(){
        System.out.println("Registering Ore Generation");
    }
}
