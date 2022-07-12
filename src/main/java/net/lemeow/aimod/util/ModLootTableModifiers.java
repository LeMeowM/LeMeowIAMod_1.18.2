package net.lemeow.aimod.util;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.lemeow.aimod.block.entity.InfusionTableBlockEntity;
import net.lemeow.aimod.item.ModItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ModLootTableModifiers {
    private static final Identifier SIMPLE_DUNGEON_CHEST_ID
            = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier DUNGEON_CORRIDOR_CHEST_ID
            = new Identifier("minecraft", "chests/stronghold_corridor");
    private static final Identifier DUNGEON_CROSSING_CHEST_ID
            = new Identifier("minecraft", "chests/stronghold_crossing");
    private static final Identifier DESERT_TEMPLE_CHEST_ID
            = new Identifier("minecraft", "chests/desert_pyramid");
    private static final Identifier END_CITY_CHEST_ID
            = new Identifier("minecraft", "chests/end_city_treasure");
    private static final Identifier JUNGLE_TEMPLE_CHEST_ID
            = new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier FORTRESS_CHEST_ID
            = new Identifier("minecraft", "chests/nether_bridge");
    private static final Identifier WEAPONSMITH_CHEST_ID
            = new Identifier("minecraft", "chests/village/village_weaponsmith");

    static List<Identifier> identifiers = List.of(
            SIMPLE_DUNGEON_CHEST_ID,
            DUNGEON_CORRIDOR_CHEST_ID,
            DUNGEON_CROSSING_CHEST_ID,
            DESERT_TEMPLE_CHEST_ID,
            END_CITY_CHEST_ID,
            JUNGLE_TEMPLE_CHEST_ID,
            FORTRESS_CHEST_ID,
            WEAPONSMITH_CHEST_ID
    );

    static List<Float> chances = List.of(
            0.215F,
            0.025F,
            0.025F,
            0.18F,
            0.046F,
            0.19F,
            0.25F,
            5.7F
    );



    public static void modifyLootTables(){
        LootTableLoadingCallback.EVENT.register((((resourceManager, manager, id, supplier, setter) -> {

            for(int i = 0; i < identifiers.size(); i++){
                if(identifiers.get(i).equals(id)){
                    FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(chances.get(i)))
                            .with(ItemEntry.builder(ModItems.COPPER_HORSE_ARMOR))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))
                                    .build());
                    supplier.withPool(poolBuilder.build());

                }
            }
        })));
    }
}
