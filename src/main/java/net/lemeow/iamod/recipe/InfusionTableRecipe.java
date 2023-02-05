package net.lemeow.iamod.recipe;



import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

/**
 * All behaviours of the recipes are stored here. On compile time {@link ModRecipes} will Register Type and Serializer
 * for both client side and serverside.
 */
public class InfusionTableRecipe implements Recipe<SimpleInventory> {
    // The important information we want to match to with recipes
    private final Identifier id;
    private final Ingredient input;
    private final Ingredient fuel;
    private final ItemStack output;

    public InfusionTableRecipe(Identifier id, Ingredient input, Ingredient fuel, ItemStack output) {
        this.id = id;
        this.input = input;
        this.fuel = fuel;
        this.output = output;
    }
    public Ingredient getFuel(){
        return fuel;
    }

    public Ingredient getInput(){
        return input;
    }

    public static boolean isFueledByItem(SimpleInventory inventory, Ingredient item){
        for(int i = 0; i<4; i++){
            if(!item.test(inventory.getStack(i))) return false;
        }
        return true;
    }


    // this is mostly based on the SimpleInventory one, but it has some changes.
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(isFueledByItem(inventory, fuel)){
            return input.test(inventory.getStack(4));
        }
        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output.copy();
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

    /**
     * A way to recognise which Serializer to use, if Minecraft finds this Type.ID, the Serializer for this class will
     * be called.
     * This is a class because sometimes multiple Types can call the same Serializer.
     */
    public static class Type implements RecipeType<InfusionTableRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "infusion_table";
    }

    /**
     *The Serializer called for all SerDes calls for recipes of this Type.ID.
     */
    public static class Serializer implements  RecipeSerializer<InfusionTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        // this is the name used in the json file: i.e. "aimod:infusion_table"
        public static final String ID = Type.ID;

        /**
         * Read calls for reading off of JSON, done by both the server and the client in singleplayer so that they have
         * a correct RecipeRegistry.
         * @param id Will always be Type.ID: "infusion_table", this is only so that if something goes catastrophically
         *           wrong, we will have a decent stacktrace.
         * @param json The text version of the JSON file with a lot of methods to help out with the querying of all the
         *             instance variables.
         * @return A InfusionTableRecipe for the registry.
         */
        @Override
        public InfusionTableRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            Ingredient ingredients = Ingredient.fromJson(JsonHelper.getObject(json, "input"));
            Ingredient fuel = Ingredient.fromJson(JsonHelper.getObject(json, "fuel"));

            return new InfusionTableRecipe(id, ingredients, fuel, output);
        }

        /**
         * Executed exclusively by the client who is joining the server.
         * @param id Will always be Type.ID: "infusion_table", this is only so that if something goes catastrophically
         *           wrong, we will have a decent stacktrace.
         * @param buf The buffer of information that has been relayed from the server to the client.
         * @return A InfusionTableRecipe for the registry.
         */
        @Override
        public InfusionTableRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            Ingredient fuel = Ingredient.fromPacket(buf);
            ItemStack output = buf.readItemStack();

            return new InfusionTableRecipe(id, input, fuel, output);

        }

        /**
         * This is actually usually not necessary due to the fact that this mod is REQUIRED clientside for textures and
         * some client side behaviours (GUIS), however, the client complains if it doesn't receive this to confirm that
         * it has the right recipes.
         * @param buf This is called before the server can send the information, so the buffer is so that whenever the
         *            client joins, this entire buffer will be to them. This buffer is FINAL, hence it can only be edited
         *            using inbuilt methods such as .write().
         * @param recipe A recipe to write down, will go through all recipes found in {@link net.minecraft.util.registry.Registry}
         */
        @Override
        public void write(PacketByteBuf buf, InfusionTableRecipe recipe) {
            recipe.input.write(buf);
            recipe.fuel.write(buf);
            buf.writeItemStack(recipe.getOutput());
        }
    }
}

