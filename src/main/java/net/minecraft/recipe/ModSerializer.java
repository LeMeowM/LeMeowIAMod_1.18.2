// lying to the code because this is most definitely not in minecraft, however, it works to solve the issue that there
// is no .getInput() class in AbstractCookingRecipe
// removing this class for now, dont need it currently
/*
package net.minecraft.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.lemeow.aimod.recipe.InfusionTableRecipe;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

public class ModSerializer<InfusionTableRecipe extends AbstractCookingRecipe & Recipe<SimpleInventory>> implements RecipeSerializer<InfusionTableRecipe> {
    // this is the name used in the json file: i.e. "modid:infusion_table"
    public static final String ID = "infusion_table";
    public final int cookingTime=6000;
    private final ModSerializer.RecipeFactory<InfusionTableRecipe> recipeFactory;

    public static final ModSerializer INSTANCE = new ModSerializer(net.lemeow.aimod.recipe.InfusionTableRecipe::new);

    public ModSerializer(ModSerializer.RecipeFactory<InfusionTableRecipe> recipeFactory) {
        this.recipeFactory = recipeFactory;
    }


    @Override
    public InfusionTableRecipe read(Identifier id, JsonObject jsonObject) {
        JsonElement jsonElement = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
        Ingredient ingredient = Ingredient.fromJson((JsonElement)jsonElement);
        String string2 = JsonHelper.getString(jsonObject, "result");
        Identifier identifier2 = new Identifier(string2);
        ItemStack itemStack = new ItemStack((ItemConvertible) Registry.ITEM.getOrEmpty(identifier2).orElseThrow(() -> {
            return new IllegalStateException("Item: " + string2 + " does not exist");
        }));
        float f = JsonHelper.getFloat(jsonObject, "experience", 0.0F);
        int i = JsonHelper.getInt(jsonObject, "cookingtime", this.cookingTime);
        return this.recipeFactory.create(id, ingredient, itemStack, f, i);
    }

    @Override
    public InfusionTableRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient ingredient = Ingredient.fromPacket(buf);
        ItemStack itemStack = buf.readItemStack();
        float f = buf.readFloat();
        int i = buf.readVarInt();
        return this.recipeFactory.create(id, ingredient, itemStack, f, i);
    }

    @Override
    public void write(PacketByteBuf buf, InfusionTableRecipe recipe) {
        buf.writeString(recipe.getGroup());
        recipe.input.write(buf);
        buf.writeItemStack(recipe.getOutput());
        buf.writeFloat(recipe.getExperience());
        buf.writeVarInt(recipe.getCookTime());
    }

    interface RecipeFactory<InfusionTableRecipe extends Recipe<SimpleInventory>> {
        InfusionTableRecipe create(Identifier id, Ingredient input, ItemStack output, float experience, int cookTime);
    }
}
*/