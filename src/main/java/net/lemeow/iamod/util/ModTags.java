package net.lemeow.iamod.util;

import net.lemeow.iamod.IAMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModTags {
    public static class Blocks{

        private static TagKey<Block> createTag(String name){
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(IAMod.MOD_ID, name));
        }

        private static TagKey<Block> createCommonTag(String name){
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }


    }

    public static class Items {
        public static final TagKey<Item> NETHERITES = createTag("netherites");


        private static TagKey<Item> createTag(String name){
            return TagKey.of(Registry.ITEM_KEY, new Identifier(IAMod.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name){
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }

    }
}
