package net.lemeow.aimod.item;

import net.lemeow.aimod.AIMod;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent COW_CHEESE = new FoodComponent.Builder().hunger(8).saturationModifier(0.5f)
            .build();
    public static final FoodComponent SHEEP_CHEESE = new FoodComponent.Builder().hunger(4).saturationModifier(1.0f)
            .build();
    public static final FoodComponent GOAT_CHEESE = new FoodComponent.Builder().hunger(6).saturationModifier(0.75f)
            .build();



    public static void registerModFoodComponents(){
        AIMod.LOGGER.info("Registering food items for "+ AIMod.MOD_ID);
    }
}
