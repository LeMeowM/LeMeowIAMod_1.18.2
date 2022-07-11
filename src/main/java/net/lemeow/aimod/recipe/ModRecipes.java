package net.lemeow.aimod.recipe;


import net.lemeow.aimod.AIMod;
import net.minecraft.recipe.ModSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {


    public static void registerRecipes(){

        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(AIMod.MOD_ID, ModSerializer.ID),
                ModSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(AIMod.MOD_ID, InfusionTableRecipe.Type.ID),
                InfusionTableRecipe.Type.INSTANCE);
    }

}
