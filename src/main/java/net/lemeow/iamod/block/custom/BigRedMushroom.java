package net.lemeow.iamod.block.custom;

import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Supplier;

/**
 * Doesn't work don't worry
 */
public class BigRedMushroom extends MushroomPlantBlock {
    public BigRedMushroom(Settings settings, Supplier<RegistryEntry<? extends ConfiguredFeature<?, ?>>> feature) {
        super(settings, feature);
    }
}
