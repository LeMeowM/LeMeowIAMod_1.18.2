package net.lemeow.iamod.entityDEPRECATED;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.lemeow.iamod.IAMod;
import net.lemeow.iamod.entityDEPRECATED.custom.CapybaraEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntity {
    public static final EntityType<CapybaraEntity> CAPYBARA = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(IAMod.MOD_ID, "capybara"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CapybaraEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.4f)).build()
    );
}
