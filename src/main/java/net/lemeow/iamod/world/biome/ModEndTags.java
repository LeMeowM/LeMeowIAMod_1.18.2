package net.lemeow.iamod.world.biome;

import net.lemeow.iamod.world.gen.ModBiomeGeneration;
import net.minecraft.block.Block;
import ru.bclib.api.tag.NamedCommonBlockTags;
import ru.bclib.api.tag.TagAPI;

public class ModEndTags {


    public static void registerModEndTags(){
        // addEndGround all the ore blocks

        // for later use on adding endblock behavior like vines, leaves and trees


    }





    public static void addEndGround(Block bl){
        TagAPI.addBlockTag(NamedCommonBlockTags.END_STONES, bl);
    }

    public static void addBiomeSurfaceToEndGroup(ModBiomeGeneration b){
        addEndGround(b.getTopMaterial().getBlock());
        addEndGround(b.getAltTopMaterial().getBlock());
        addEndGround(b.getUnderMaterial().getBlock());
    }


}
