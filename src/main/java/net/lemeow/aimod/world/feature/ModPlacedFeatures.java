package net.lemeow.aimod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;

public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> END_VOID_ORE_PLACED = PlacedFeatures.register("void_ore_placed",
            ModConfiguredFeatures.VOID_QUARTZ_ORE, ModOreFeatures.modifiersWithCount(1,
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))

    );
}
