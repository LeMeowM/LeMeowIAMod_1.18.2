package net.lemeow.aimod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.item.custom.*;
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
            new Item(new FabricItemSettings().group(ItemGroup.MISC).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_INGOT = registerItem("void_quartz_ingot",
            new Item(new FabricItemSettings().group(ItemGroup.MISC).fireproof().rarity(Rarity.EPIC)));


    // Void Quartz Armor

    public static final Item VOID_QUARTZ_HELMET = registerItem("void_quartz_helmet",
            new ArmorItem(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ItemGroup.COMBAT).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_CHESTPLATE = registerItem("void_quartz_chestplate",
            new ArmorItem(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ItemGroup.COMBAT).fireproof().rarity(Rarity.EPIC)));


    /*
    public static final Item VOID_QUARTZ_CHESTPLATE_WINGS = registerItem("void_quartz_chestplate_wings",
            new ModCustomChestplate(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ItemGroup.TRANSPORTATION).fireproof().rarity(Rarity.EPIC)));
    */
    public static final Item VOID_QUARTZ_LEGGINGS = registerItem("void_quartz_leggings",
            new ArmorItem(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ItemGroup.COMBAT).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_BOOTS = registerItem("void_quartz_boots",
            new ArmorItem(ModArmorMaterials.VOID_QUARTZ, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ItemGroup.COMBAT).fireproof().rarity(Rarity.EPIC)));


    // Void Quartz Tools

    public static final Item VOID_QUARTZ_SHOVEL = registerItem("void_quartz_shovel",
            new ModCopperShovel(ModToolMaterials.VOID_QUARTZ, 2, -3.0F,
                    new FabricItemSettings().group(ItemGroup.TOOLS).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_PICKAXE = registerItem("void_quartz_pickaxe",
            new ModCopperPickaxe(ModToolMaterials.VOID_QUARTZ, 1, -2.8F,
                    new FabricItemSettings().group(ItemGroup.TOOLS).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_AXE = registerItem("void_quartz_axe",
            new ModCopperAxe(ModToolMaterials.VOID_QUARTZ, 7.5F, -3F,
                    new FabricItemSettings().group(ItemGroup.TOOLS).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_SWORD = registerItem("void_quartz_sword",
            new SwordItem(ModToolMaterials.VOID_QUARTZ, 4, -2.4F,
                    new FabricItemSettings().group(ItemGroup.COMBAT).fireproof().rarity(Rarity.EPIC)));

    public static final Item VOID_QUARTZ_HOE = registerItem("void_quartz_hoe",
            new ModCopperHoe(ModToolMaterials.VOID_QUARTZ, -5, 0F,
                    new FabricItemSettings().group(ItemGroup.TOOLS).fireproof().rarity(Rarity.EPIC)));








    private static Item registerItem(String name, Item item){
         return Registry.register(Registry.ITEM, new Identifier(AIMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        AIMod.LOGGER.info("Registering Mod Items for "+ AIMod.MOD_ID);
    }
}
