package net.lemeow.iamod.recipe;


import net.lemeow.iamod.IAMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {


    public static void registerRecipes(){


        Registry.register(Registry.RECIPE_TYPE, new Identifier(IAMod.MOD_ID, InfusionTableRecipe.Type.ID),
                InfusionTableRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(IAMod.MOD_ID, InfusionTableRecipe.Serializer.ID),
                InfusionTableRecipe.Serializer.INSTANCE);



    }

}
