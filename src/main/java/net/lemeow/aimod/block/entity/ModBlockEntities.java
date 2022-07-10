package net.lemeow.aimod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.lemeow.aimod.AIMod;
import net.lemeow.aimod.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<InfusionTableBlockEntity> INFUSION_TABLE;

    public static void registerAllBlockEntities(){
        INFUSION_TABLE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(AIMod.MOD_ID, "infusion_table"),
                FabricBlockEntityTypeBuilder.create(InfusionTableBlockEntity:: new,
                        ModBlocks.INFUSION_TABLE).build(null));



    }

}
