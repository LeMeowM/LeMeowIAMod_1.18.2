package net.lemeow.iamod.world.biome;

import ru.bclib.api.biomes.BCLBiomeBuilder;

public class ModEndFeatures {


    // default, will spawn EVERYWHERE
    public static BCLBiomeBuilder addDefaultFeatures(BCLBiomeBuilder builder, boolean hasCaves){
        // add building features including trees and maybe buildings
        // also might want to introduce the ores stuff into here too to make everything the same place?

        /*
        if(hasCaves){
            builder.feature(cave);
        }
        */

        return builder;

    }

    //


}
