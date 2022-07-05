package net.lemeow.aimod.block;

import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Supplier;

public class BigRedMushroom extends MushroomPlantBlock {
    public BigRedMushroom(Settings settings, Supplier<RegistryEntry<? extends ConfiguredFeature<?, ?>>> feature) {
        super(settings, feature);
    }
}
