package net.lemeow.aimod.recipe;



import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;


public class InfusionTableRecipe implements Recipe<SimpleInventory> {

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


    // write the matches thing based on the simpleinventory system
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

    public static class Type implements RecipeType<InfusionTableRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "infusion_table";
    }


    public static class Serializer implements  RecipeSerializer<InfusionTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        // this is the name used in the json file: i.e. "aimod:infusion_table"
        public static final String ID = "infusion_table";


        // for in person, only one necessary apparently but I want server possibility
        @Override
        public InfusionTableRecipe read(Identifier id, JsonObject json) {
            // using sharedrecipe's outputFromJson method bc I don't want to write my own
            // nevemind wrote my own
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

            Ingredient ingredients = Ingredient.fromJson(JsonHelper.getObject(json, "input"));

            Ingredient fuel = Ingredient.fromJson(JsonHelper.getObject(json, "fuel"));

            return new InfusionTableRecipe(id, ingredients, fuel, output);
        }

        // FIGURED IT OUT, THESE 2 HAVE TO MATCH UP
        @Override
        public InfusionTableRecipe read(Identifier id, PacketByteBuf buf) {

            //reading input
            Ingredient input = Ingredient.fromPacket(buf);

            // reading fuel

            Ingredient fuel = Ingredient.fromPacket(buf);

            // reading the output
            ItemStack output = buf.readItemStack();

            // returing the recipe we use
            return new InfusionTableRecipe(id, input, fuel, output);


        }



        @Override
        public void write(PacketByteBuf buf, InfusionTableRecipe recipe) {
            recipe.input.write(buf);
            recipe.fuel.write(buf);
            buf.writeItemStack(recipe.getOutput());
        }
    }
}

