package net.lemeow.iamod.world.feature;

import net.lemeow.iamod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;


public class ModConfiguredFeatures {
    /**
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BigRedMushroom =
            ConfiguredFeatures.register
                    ("big_red_mushroom", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(Blocks.MUSHROOM_STEM),
                    new GiantTrunkPlacer(13, 2, 14),
                    BlockStateProvider.of(Blocks.RED_MUSHROOM_BLOCK),
                    new BlobFoliagePlacer(ConstantIntProvider.create(10), ConstantIntProvider.create(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build());
    **/
    public static final List<OreFeatureConfig.Target> END_VOID_ORES = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.VOID_QUARTZ_ORE.getDefaultState())
    );

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> VOID_QUARTZ_ORE =
            ConfiguredFeatures.register("void_quartz_ore", Feature.ORE,
                    new OreFeatureConfig(END_VOID_ORES, 7));
}
