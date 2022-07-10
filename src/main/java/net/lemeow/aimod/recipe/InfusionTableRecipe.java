package net.lemeow.aimod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class InfusionTableRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public InfusionTableRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    /*
    Design of the crafting grid will be something like


            X
            |
        X---A---X   Where X are Void Quartz Shards and A is the Netherite ingot being changed into a Void Quartz Ingot
            |       Perhaps we then do it again where A is any netherite tool/armor
            X       and X are the ingots to infuse it with?



     */
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()) return false;
        for(int i = 0; i < 6; i++ ){
            if(!recipeItems.get(i).test(inventory.getStack(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
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


        /* also for server, again carried by the fabric discord help channel, no idea why this is working
        i think buf is a tool for the player to communicate with the server and its built in this way but i cant seem to
        understand the documentation?
         */
        @Override
        public void write(PacketByteBuf buf, InfusionTableRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing: recipe.getIngredients()){
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
        }
    }

}
