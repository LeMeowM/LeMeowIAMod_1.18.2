package net.lemeow.aimod.world.biome.land;

import net.lemeow.aimod.world.gen.ModBiomeGeneration;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.BiomeTags;
import ru.bclib.api.biomes.BCLBiomeBuilder;

public class EndForestBiome extends ModBiomeGeneration.Config {


    public EndForestBiome() {
        super("end_forest");
    }

    @Override
    protected void addCustomBuildData(BCLBiomeBuilder builder) {
        builder
                .fogColor(189, 82, 70)
                .fogDensity(1.1F)
                .waterAndFogColor(171, 234, 226)
                .plantsColor(254, 85, 57)
                .structure(BiomeTags.END_CITY_HAS_STRUCTURE)
                .spawn(EntityType.ENDERMAN, 50, 1, 2);
    }


}
