package net.lemeow.iamod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.lemeow.iamod.IAMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup VOID_QUARTZ = FabricItemGroupBuilder.build(new Identifier(IAMod.MOD_ID,
            "void_quartz"), ()-> new ItemStack(ModItems.VOID_QUARTZ_INGOT));
}
