package net.lemeow.iamod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.lemeow.iamod.IAMod;
import net.lemeow.iamod.block.custom.MassCraftingTable;
import net.lemeow.iamod.block.custom.cheesemold.AbstractCheeseMoldBlock;
import net.lemeow.iamod.block.custom.InfusionTableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

/**
 * Registers all blocks, this is called by {@link IAMod} to be running at compile time.
 */
public class ModBlocks {

    // Void Quartz Blocks Inputs

    public static final Block VOID_QUARTZ_ORE = registerVoidBlock("void_quartz_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(50f, 1200f)),
            ItemGroup.BUILDING_BLOCKS,
            UniformIntProvider.create(5, 10));


    public static final Block VOID_QUARTZ_INGOT_BLOCK = registerVoidBlock("void_quartz_ingot_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(50f, 1200f).requiresTool()),
            ItemGroup.MISC, UniformIntProvider.create(5, 10));

    public static final Block VOID_QUARTZ_BLOCK = registerVoidBlock("void_quartz_block",
            new Block(FabricBlockSettings.of(Material.GLASS).strength(5f, 50f).requiresTool()),
            ItemGroup.MISC);

    public static final Block INFUSION_TABLE = registerVoidBlock("infusion_table",
            new InfusionTableBlock(FabricBlockSettings.of(Material.STONE).strength(50f,1200f).requiresTool()),
            ItemGroup.BUILDING_BLOCKS);

    public static final Block MASS_CRAFTING_TABLE = registerBlock("mass_crafting_table",
            new MassCraftingTable(FabricBlockSettings.of(Material.METAL).strength(.5f)),
            ItemGroup.MISC
            );

    // Foods

    public static final Block EMPTY_CHEESE_MOLD_BLOCK = registerBlock("cheese_mold_block",
            new AbstractCheeseMoldBlock(FabricBlockSettings.of(Material.WOOD).strength(5f)),
            ItemGroup.MISC);


    public static final Block SHEEP_CHEESE_MOLD_BLOCK = registerBlock("sheep_cheese_mold_block",
            new AbstractCheeseMoldBlock(FabricBlockSettings.of(Material.WOOD).strength(5f)),
            ItemGroup.MISC);

    public static final Block GOAT_CHEESE_MOLD_BLOCK = registerBlock("goat_cheese_mold_block",
            new AbstractCheeseMoldBlock(FabricBlockSettings.of(Material.WOOD).strength(5f)),
            ItemGroup.MISC);

    public static final Block COW_CHEESE_MOLD_BLOCK = registerBlock("cow_cheese_mold_block",
            new AbstractCheeseMoldBlock(FabricBlockSettings.of(Material.WOOD).strength(5f)),
            ItemGroup.MISC);





    // End Forest Blocks?


    public static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(IAMod.MOD_ID, name), block);
    }


    public static Block registerBlock(String name, Block block, ItemGroup group, UniformIntProvider uniformIntProvider){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(IAMod.MOD_ID, name), block);
    }

    public static Block registerVoidBlock(String name, Block block, ItemGroup group){
        registerVoidItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(IAMod.MOD_ID, name), block);
    }

    public static Block registerVoidBlock(String name, Block block, ItemGroup group, UniformIntProvider intProvider){
        registerVoidItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(IAMod.MOD_ID, name), block);
    }

    // items
    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(IAMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    private static Item registerVoidItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(IAMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group).fireproof().rarity(Rarity.EPIC)));
    }


    public static void registerMudBlocks(){
        IAMod.LOGGER.info("Registering ModBlocks for " + IAMod.MOD_ID);
    }
}
