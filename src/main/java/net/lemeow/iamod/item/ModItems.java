package net.lemeow.iamod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lemeow.iamod.IAMod;
import net.lemeow.iamod.item.custom.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;


public class ModItems {



    // COPPER TOOLS
    public static final Item COPPER_SHOVEL = registerItem("copper_shovel",
            new ModCopperShovel(ModToolMaterials.COPPER, 2, 2f,
                    new FabricItemSettings().group(ItemGroup.TOOLS)));

    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe",
            new ModCopperPickaxe(ModToolMaterials.COPPER, 1, 2f,
                    new FabricItemSettings().group(ItemGroup.TOOLS)));

    public static final Item COPPER_AXE = registerItem("copper_axe",
            new ModCopperAxe(ModToolMaterials.COPPER, 7.5f, 2f,
                    new FabricItemSettings().group(ItemGroup.TOOLS)));

    public static final Item COPPER_SWORD = registerItem("copper_sword",
            new SwordItem(ModToolMaterials.COPPER, 4, 2f,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_HOE = registerItem("copper_hoe",
            new ModCopperHoe(ModToolMaterials.COPPER, 1, 2f,
                    new FabricItemSettings().group(ItemGroup.TOOLS)));

    // Copper Nugget, made by smelting tools/armor and crafting from an ingot
    public static final Item COPPER_NUGGET = registerItem("copper_nugget",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    //Copper Armor
    public static final Item COPPER_HELMET = registerItem("copper_helmet",
            new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate",
            new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings",
            new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_BOOTS = registerItem("copper_boots",
            new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_HORSE_ARMOR = registerItem("copper_horse_armor",
            new HorseArmorItem(4,"copper",
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    // BIG MUSHROOMS, what the fuck is a Supplier<registry>????

    /***
    public static final Item BIG_RED_MUSHROOM = registerItem("big_red_mushroom",
            new BigRedMushroom(FabricBlockSettings.copy(Blocks.RED_MUSHROOM), ));
    ***/

    // Void Quartz Items

    public static final Item VOID_QUARTZ_SHARD = registerItem("void_quartz_shard",
            new Item(new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_INGOT = registerItem("void_quartz_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));


    // Void Quartz Armor

    public static final Item VOID_QUARTZ_HELMET = registerItem("void_quartz_helmet",
            new ArmorItem(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_CHESTPLATE = registerItem("void_quartz_chestplate",
            new ArmorItem(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));



    public static final Item VOID_QUARTZ_CHESTPLATE_WINGS = registerItem("void_quartz_chestplate_wings",
            new PlatedElytra(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_LEGGINGS = registerItem("void_quartz_leggings",
            new ArmorItem(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_BOOTS = registerItem("void_quartz_boots",
            new ArmorItem(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));


    // Void Quartz Tools

    public static final Item VOID_QUARTZ_SHOVEL = registerItem("void_quartz_shovel",
            new ModCopperShovel(ModToolMaterials.VOID_QUARTZ, 2, -3.0F,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_PICKAXE = registerItem("void_quartz_pickaxe",
            new ModCopperPickaxe(ModToolMaterials.VOID_QUARTZ, 1, -2.8F,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_AXE = registerItem("void_quartz_axe",
            new ModCopperAxe(ModToolMaterials.VOID_QUARTZ, 7.5F, -3F,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_SWORD = registerItem("void_quartz_sword",
            new SwordItem(ModToolMaterials.VOID_QUARTZ, 4, -2.4F,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_HOE = registerItem("void_quartz_hoe",
            new ModCopperHoe(ModToolMaterials.VOID_QUARTZ, -5, 0F,
                    new FabricItemSettings().group(ModItemGroup.VOID_QUARTZ).fireproof().rarity(Rarity.EPIC)));


    // Knives

    public static final Item WOOD_KNIFE = registerItem("wood_knife",
            new ModKnife(ToolMaterials.STONE, 1, 0F,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item STONE_KNIFE = registerItem("stone_knife",
            new ModKnife(ToolMaterials.STONE, 1, 0F,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_KNIFE = registerItem("copper_knife",
            new ModKnife(ModToolMaterials.COPPER, 1, 0F,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item IRON_KNIFE = registerItem("iron_knife",
            new ModKnife(ToolMaterials.IRON, 1, 0F,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item GOLD_KNIFE = registerItem("gold_knife",
            new ModKnife(ToolMaterials.GOLD, 1, 0F,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item DIAMOND_KNIFE = registerItem("diamond_knife",
            new ModKnife(ToolMaterials.DIAMOND, 1, 0F,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item NETHERITE_KNIFE = registerItem("netherite_knife",
            new ModKnife(ToolMaterials.NETHERITE, 1, 0F,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item VOID_QUARTZ_KNIFE = registerItem("void_quartz_knife",
            new ModKnife(ModToolMaterials.VOID_QUARTZ, 1, 0F,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));



    // Bows

    public static final Item IRON_BOW = registerItem("iron_bow",
            new ModBowItem(ToolMaterials.IRON, 2, 3,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));



    // foods

    public static final Item COW_CHEESE = registerItem("cow_cheese", new Item(new FabricItemSettings()
            .group(ItemGroup.FOOD).food(ModFoodComponents.COW_CHEESE))
    );

    public static final Item SHEEP_CHEESE = registerItem("sheep_cheese", new Item(new FabricItemSettings()
            .group(ItemGroup.FOOD).food(ModFoodComponents.SHEEP_CHEESE))
    );

    public static final Item GOAT_CHEESE = registerItem("goat_cheese", new Item(new FabricItemSettings()
            .group(ItemGroup.FOOD).food(ModFoodComponents.GOAT_CHEESE))
    );

    public static final Item GOAT_MILK_BUCKET = registerItem("goat_milk_bucket",
            (new MilkBucketItem((new Item.Settings()).recipeRemainder(Items.BUCKET).maxCount(1)
                    .group(ItemGroup.MISC))));


    public static final Item SHEEP_MILK_BUCKET = registerItem("sheep_milk_bucket",
            (new MilkBucketItem((new Item.Settings()).recipeRemainder(Items.BUCKET).maxCount(1)
                    .group(ItemGroup.MISC))));








    private static Item registerItem(String name, Item item){
         return Registry.register(Registry.ITEM, new Identifier(IAMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        IAMod.LOGGER.info("Registering Mod Items for "+ IAMod.MOD_ID);
    }
}
