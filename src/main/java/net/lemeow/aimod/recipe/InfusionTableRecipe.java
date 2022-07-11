package net.lemeow.aimod.recipe;



import net.minecraft.item.ItemStack;

import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;



public class InfusionTableRecipe extends AbstractCookingRecipe{

    public static final RecipeType<InfusionTableRecipe> INFUSING = RecipeType.register("infusing");

    public InfusionTableRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super( INFUSING , id, group, input, output,  0.0F, 6000);
    }



    public Ingredient getInput() {
        return super.input;
    }



    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<InfusionTableRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "infusion_table";
    }



    /*
    public static class Serializer implements  RecipeSerializer<InfusionTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        // this is the name used in the json file: i.e. "aimod:infusion_table"
        public static final String ID = "infusion_table";


        // for in person, only one necessary apparently but i want server possibility
        @Override
        public InfusionTableRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(25, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new InfusionTableRecipe(id, output, inputs);
        }

        // for server, wtf is PacketByteBuf?? why does this work???
        @Override
        public InfusionTableRecipe read(Identifier id, PacketByteBuf buf) {

            // I have no idea how this works but the Fabric help discord said apparently I have to do it this way???
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new InfusionTableRecipe(id, output, inputs);


        }


        // also for server, again carried by the fabric discord help channel, no idea why this is working
        // i think buf is a tool for the player to communicate with the server and its built in this way but i cant seem to
        // understand the documentation?

        @Override
        public void write(PacketByteBuf buf, InfusionTableRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing: recipe.getIngredients()){
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
        }

        */
}

