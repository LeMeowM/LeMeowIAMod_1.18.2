package net.lemeow.aimod.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;

public class ModSurfaceConfigs {
    public static final MaterialRules.MaterialRule END_STONE_GRASS =
            MaterialRules.block(Blocks.END_STONE.getDefaultState()); // change this to endstone grass as soon as possible

}
