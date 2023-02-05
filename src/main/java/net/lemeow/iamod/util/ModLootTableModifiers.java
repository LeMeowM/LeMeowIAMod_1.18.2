package net.lemeow.iamod.util;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.lemeow.iamod.item.ModItems;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModLootTableModifiers {

    /**
     * This is a little stupid but these Identifiers are all private in the game, so I have to remake them.
     */
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

    /**
     * Using a Hashmap to go through the association between the Identifier and the chance it has to show up. The
     * static part is unfortunate but it works and I personally don't mind it too much as they're right next to each other.
     */
    static Map<Identifier, Float> IdChances = new HashMap<>();
    static{
        IdChances.put(SIMPLE_DUNGEON_CHEST_ID, 0.215F);
        IdChances.put(DUNGEON_CORRIDOR_CHEST_ID, 0.025F);
        IdChances.put(DUNGEON_CROSSING_CHEST_ID, 0.025F);
        IdChances.put(DESERT_TEMPLE_CHEST_ID, 0.18F);
        IdChances.put(END_CITY_CHEST_ID, 0.046F);
        IdChances.put(JUNGLE_TEMPLE_CHEST_ID, 0.19F);
        IdChances.put(FORTRESS_CHEST_ID, 0.25F);
        IdChances.put(WEAPONSMITH_CHEST_ID, 5.7F);
    }


    /**
     * Iterates through the chances that are added and use the FabricLootPoolBuilder to add an entry in the LootRegistry
     * The method I'm using to iterate uses Map Entries which is kind of scuffed, but it works.
     */
    public static void modifyLootTables(){
        LootTableLoadingCallback.EVENT.register((((resourceManager, manager, id, supplier, setter) -> {

            for(Map.Entry<Identifier, Float> entry : IdChances.entrySet()){
                if(entry.getKey().equals(id)){
                    FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(entry.getValue()))
                            .with(ItemEntry.builder(ModItems.COPPER_HORSE_ARMOR))
                            .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))
                                    .build());
                    supplier.withPool(poolBuilder.build());
                }
            }
        })));
    }
}
