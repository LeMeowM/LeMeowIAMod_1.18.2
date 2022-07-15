

package net.lemeow.aimod.world.gen;

// A lot of the code here has been borrowed from the BCLib mod
// nvm as much as its based on it, i had to translate the MOJMAP mappings into yarn which was a pain in the ass

import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.world.biome.ModEndFeatures;
import net.lemeow.aimod.world.biome.ModEndTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import ru.bclib.api.biomes.BCLBiomeBuilder;
import ru.bclib.api.biomes.BiomeAPI;
import ru.bclib.api.surface.SurfaceRuleBuilder;
import ru.bclib.interfaces.SurfaceMaterialProvider;
import ru.bclib.mixin.common.StructureFeatureAccessor;
import ru.bclib.world.biomes.BCLBiome;
import ru.bclib.world.biomes.BCLBiomeSettings;



public class ModBiomeGeneration extends BCLBiome implements SurfaceMaterialProvider {
    public static final RegistryKey<Biome> END_FOREST = RegistryKey.of(Registry.BIOME_KEY, new Identifier(AIMod.MOD_ID, "end_forest"));



    public static class DefaultSurfaceMaterialProvider implements SurfaceMaterialProvider {
        public static final BlockState END_STONE = Blocks.END_STONE.getDefaultState();

        @Override
        public BlockState getTopMaterial() {
            return getUnderMaterial();
        }

        @Override
        public BlockState getUnderMaterial() {
            return END_STONE;
        }

        @Override
        public BlockState getAltTopMaterial() {
            return getTopMaterial();
        }

        @Override
        public boolean generateFloorRule() {
            return true;
        }

        @Override
        public SurfaceRuleBuilder surface() {
            SurfaceRuleBuilder builder = SurfaceRuleBuilder.start();

            //need to add a sort of noise system here so that the surface isnt all just the end grass? maybe 1 in 10 is just endstone?

            if (generateFloorRule() && getTopMaterial()!=getUnderMaterial()){
                if (getTopMaterial()!=getAltTopMaterial()){
                    builder.floor(getTopMaterial());
                } else {
                    builder.chancedFloor(getTopMaterial(), getAltTopMaterial());
                }
            }

            builder.floor(getTopMaterial());

            return builder.filler(getUnderMaterial());
        }

    }


    public static abstract class Config{
        public static final SurfaceMaterialProvider DEFAULT = new DefaultSurfaceMaterialProvider();

        protected static final StructureFeatureAccessor VANILLA_FEATURES = (StructureFeatureAccessor) new ConfiguredStructureFeatures();
        // add grass to modblocks
        protected static final MaterialRules.MaterialRule END_STONE_GRASS = MaterialRules.block(Blocks.END_STONE.getDefaultState());

        public final Identifier ID;

        protected Config(String name) {
            this.ID = new Identifier(AIMod.MOD_ID, name);
        }

        protected abstract void addCustomBuildData(BCLBiomeBuilder builder);

        public BCLBiomeBuilder.BiomeSupplier<ModBiomeGeneration> getSupplier(){
            return ModBiomeGeneration::new;
        }

        protected boolean hasCaves(){
            return true;
        }

        protected SurfaceMaterialProvider surfaceMaterial() {
            return DEFAULT;
        }



    }

    public ModBiomeGeneration(RegistryKey<Biome> biomeKey) {
        super(biomeKey);
    }

    public ModBiomeGeneration(Identifier biomeID, Biome biome, BCLBiomeSettings settings) {
        super(biomeID, biome, settings);
    }

    public static ModBiomeGeneration create(Config biomeConfig){
        BCLBiomeBuilder builder = BCLBiomeBuilder
                .start(biomeConfig.ID)
                .category(Biome.Category.THEEND)
                .music(SoundEvents.MUSIC_END)
                .waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(0xA080A0)
                .skyColor(0)
                .temperature(0.5f)
                .wetness(0.5f)
                .precipitation(Biome.Precipitation.NONE)
                .surface(biomeConfig.surfaceMaterial().surface().build());

        biomeConfig.addCustomBuildData(builder);
        ModEndFeatures.addDefaultFeatures(builder, biomeConfig.hasCaves());


        ModBiomeGeneration biome = builder.build(biomeConfig.getSupplier());
        biome.addCustomData("has_caves", biomeConfig.hasCaves());
        biome.setSurfaceMaterial(biomeConfig.surfaceMaterial());

        ModEndTags.addBiomeSurfaceToEndGroup(biome);
        return biome;
    }


    private SurfaceMaterialProvider surfMatProv = Config.DEFAULT;
    private void setSurfaceMaterial(SurfaceMaterialProvider prov) {
        surfMatProv = prov;
    }

    @Override
    public BlockState getTopMaterial() {
        return surfMatProv.getTopMaterial();
    }

    @Override
    public BlockState getUnderMaterial() {
        return surfMatProv.getUnderMaterial();
    }

    @Override
    public BlockState getAltTopMaterial() {
        return surfMatProv.getAltTopMaterial();
    }

    @Override
    public boolean generateFloorRule() { return surfMatProv.generateFloorRule(); }

    @Override
    public SurfaceRuleBuilder surface() { return surfMatProv.surface(); }

    public static BlockState findTopMaterial(BCLBiome biome){
        return BiomeAPI.findTopMaterial(biome).orElse(ModBiomeGeneration.Config.DEFAULT.getTopMaterial());
    }

    public static BlockState findTopMaterial(Biome biome){
        return findTopMaterial(BiomeAPI.getBiome(biome));
    }

    public static BlockState findTopMaterial(StructureWorldAccess world, BlockPos pos){
        return findTopMaterial(BiomeAPI.getBiome(world.getBiome(pos)));
    }

    public static BlockState findUnderMaterial(BCLBiome biome){
        return BiomeAPI.findUnderMaterial(biome).orElse(ModBiomeGeneration.Config.DEFAULT.getUnderMaterial());
    }

    public static BlockState findUnderMaterial(StructureWorldAccess world, BlockPos pos){
        return findUnderMaterial(BiomeAPI.getBiome(world.getBiome(pos)));
    }
}

