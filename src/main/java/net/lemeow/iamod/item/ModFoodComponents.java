package net.lemeow.iamod.item;

import net.lemeow.iamod.IAMod;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent COW_CHEESE = new FoodComponent.Builder().hunger(8).saturationModifier(0.5f)
            .build();
    public static final FoodComponent SHEEP_CHEESE = new FoodComponent.Builder().hunger(4).saturationModifier(1.0f)
            .build();
    public static final FoodComponent GOAT_CHEESE = new FoodComponent.Builder().hunger(6).saturationModifier(0.75f)
            .build();



    public static void registerModFoodComponents(){
        IAMod.LOGGER.info("Registering food items for "+ IAMod.MOD_ID);
    }
}
