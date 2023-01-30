package net.lemeow.iamod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.lemeow.iamod.IAMod;
import net.lemeow.iamod.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Registers all BlockEntities which I have made: currently it is only the Infusion Table but there will be more.
 */
public class ModBlockEntities {
    public static BlockEntityType<InfusionTableBlockEntity> INFUSION_TABLE;

    public static void registerAllBlockEntities(){
        INFUSION_TABLE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(IAMod.MOD_ID, "infusion_table"),
                FabricBlockEntityTypeBuilder.create(InfusionTableBlockEntity:: new,
                        ModBlocks.INFUSION_TABLE).build(null));
    }

}
