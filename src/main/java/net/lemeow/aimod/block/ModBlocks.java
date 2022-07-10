package net.lemeow.aimod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.block.custom.InfusionTableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    // Void Quartz Blocks Inputs

    public static final Block VOID_QUARTZ_ORE = registerBlock("void_quartz_ore",
            new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(50f, 1200f)),
            ItemGroup.BUILDING_BLOCKS);

    public static final Block VOID_QUARTZ_BLOCK = registerBlock("void_quartz_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(50f, 1200f).requiresTool()),
            ItemGroup.MISC);

    public static final Block INFUSION_TABLE = registerBlock("infusion_table",
            new InfusionTableBlock(FabricBlockSettings.of(Material.STONE).strength(50f,1200f).requiresTool()),
            ItemGroup.BUILDING_BLOCKS);

    public static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(AIMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(AIMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }


    public static void registerMudBlocks(){
        AIMod.LOGGER.info("Registering ModBlocks for" + AIMod.MOD_ID);
    }
}
