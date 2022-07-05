package net.lemeow.aimod.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;


public class ModConfiguredFeatures {
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BigRedMushroom =
            ConfiguredFeatures.register
                    ("big_red_mushroom", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(Blocks.MUSHROOM_STEM),
                    new GiantTrunkPlacer(13, 2, 14),
                    BlockStateProvider.of(Blocks.RED_MUSHROOM_BLOCK),
                    new BlobFoliagePlacer(ConstantIntProvider.create(10), ConstantIntProvider.create(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build());
}
