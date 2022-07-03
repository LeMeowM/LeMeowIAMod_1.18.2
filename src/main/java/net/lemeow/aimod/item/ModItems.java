package net.lemeow.aimod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.item.custom.ModCopperAxe;
import net.lemeow.aimod.item.custom.ModCopperHoe;
import net.lemeow.aimod.item.custom.ModCopperPickaxe;
import net.lemeow.aimod.item.custom.ModCopperShovel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

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

    public static final Item COPPER_NUGGET = registerItem("copper_nugget",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item COPPER_HELMET = registerItem("copper_helmet",
            new ArmorItem(ModArmorMaterials.Copper, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate",
            new ArmorItem(ModArmorMaterials.Copper, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_LEGGINGS = registerItem("copper_LEGGINGS",
            new ArmorItem(ModArmorMaterials.Copper, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item COPPER_BOOTS = registerItem("copper_BOOTS",
            new ArmorItem(ModArmorMaterials.Copper, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ItemGroup.COMBAT)));


    private static Item registerItem(String name, Item item){
         return Registry.register(Registry.ITEM, new Identifier(AIMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        AIMod.LOGGER.info("Registering Mod Items for "+ AIMod.MOD_ID);
    }
}
