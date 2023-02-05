
package net.lemeow.iamod.block.custom.cheesemold;

import it.unimi.dsi.fastutil.objects.AbstractObject2ObjectFunction;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.lemeow.iamod.block.ModBlocks;
import net.lemeow.iamod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;
import java.util.function.Predicate;

// todo: fix this
@FunctionalInterface
public interface CheeseMoldBehavior {
    Map<Item, CheeseMoldBehavior> EMPTY_CHEESE_MOLD = createMap();
    Map<Item, CheeseMoldBehavior> COW_CHEESE_MOLD = createMap();
    Map<Item, CheeseMoldBehavior> SHEEP_CHEESE_MOLD = createMap();
    Map<Item, CheeseMoldBehavior> GOAT_CHEESE_MOLD = createMap();


    /**
     * The function the functional interface is based upon.
     * @param state BlockState of the block the interaction is happening on.
     * @param world The world this interaction is happening on, more useful for data querying but not very useful in
     *              this case.
     * @param pos Position of the block being interacted. Can also be used to get the BlockState.
     * @param player Player doing the interaction.
     * @param hand The hand of which is doing the interaction. This is for mainhand/offhand shenanegans that should be
     *             looked at later to patch exploits.
     * @param stack The stack in the hand doing the interaction. In this it should be a milk bucket of a sort.
     * @return ActionResult, tells the game if something succeeded or not.
     */
    ActionResult interact(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack);


    /**
     * Instantiation of the implementations of the function "interact".
     */
    CheeseMoldBehavior ADD_COW_MILK = (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
                                       ItemStack stack) ->{
        return fillMold( world, pos, player, hand, stack, ModBlocks.COW_CHEESE_MOLD_BLOCK.getDefaultState()
                .with(LeveledCheeseMoldBlock.LEVEL, 1), SoundEvents.ITEM_BUCKET_EMPTY);
    };
    CheeseMoldBehavior ADD_GOAT_MILK = (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
                                        ItemStack stack) ->{
        return fillMold( world, pos, player, hand, stack, ModBlocks.GOAT_CHEESE_MOLD_BLOCK.getDefaultState()
                .with(LeveledCheeseMoldBlock.LEVEL, 1), SoundEvents.ITEM_BUCKET_EMPTY);
    };
    CheeseMoldBehavior ADD_SHEEP_MILK = (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
                                         ItemStack stack) ->{
        return fillMold( world, pos, player, hand, stack, ModBlocks.SHEEP_CHEESE_MOLD_BLOCK.getDefaultState()
                .with(LeveledCheeseMoldBlock.LEVEL, 1), SoundEvents.ITEM_BUCKET_EMPTY);
    };
    CheeseMoldBehavior DROP_CHEESE = CheeseMoldBehavior::dropCheese;


    /**
     * Helper method to make creation of the behaviours more readable.
     * @return A new behaviour that is then edited in registerBucketBehaviours().
     */
    static Object2ObjectOpenHashMap<Item, CheeseMoldBehavior> createMap(){
        return Util.make(new Object2ObjectOpenHashMap(), AbstractObject2ObjectFunction::defaultReturnValue);
    }

    /**
     * Helps register each behaviour to compiler, in the registerBehavior() method.
     * @param behavior Maps of all possible interactions.
     */
    static void registerBucketBehavior(Map<Item, CheeseMoldBehavior> behavior) {
        behavior.put(Items.MILK_BUCKET, ADD_COW_MILK);
        behavior.put(ModItems.SHEEP_MILK_BUCKET, ADD_SHEEP_MILK);
        behavior.put(ModItems.GOAT_MILK_BUCKET, ADD_GOAT_MILK);
    }


    /**
     * Registers this to the compiler in {@link net.lemeow.iamod.IAMod}
     */
    static void registerBehavior(){
        // adds cow, sheep and goat milk adding behavior to an empty cheese mold
        registerBucketBehavior(EMPTY_CHEESE_MOLD);

        /* DO NOT make this into a loop
        * some inline issue caused a race condition (WHY IS THIS HAPPENING between this and another part of the code
        * making the behaviour
        * registry not work for god knows what reason.
        * I think it's a gradle problem.
         **/
        COW_CHEESE_MOLD.put(Items.MILK_BUCKET, ADD_COW_MILK);

        GOAT_CHEESE_MOLD.put(Items.MILK_BUCKET, ADD_GOAT_MILK);

        SHEEP_CHEESE_MOLD.put(Items.MILK_BUCKET, ADD_SHEEP_MILK);

    }

    /**
     * The actual implementation of the "interact" function
     * @param state BlockState of the block the interaction is happening on.
     * @param world The world this interaction is happening on, more useful for data querying but not very useful in
     *              this case.
     * @param pos Position of the block being interacted. Can also be used to get the BlockState.
     * @param player Player doing the interaction.
     * @param hand The hand of which is doing the interaction. This is for mainhand/offhand shenanegans that should be
     *             looked at later to patch exploits.
     * @param stack The stack in the hand doing the interaction. In this it should be a milk bucket of a sort.
     * @return ActionResult, tells the game if something succeeded or not.
     */
    static ActionResult dropCheese(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
                                   ItemStack stack){
        Block block = world.getBlockState(pos).getBlock();
        emptyMold(state, world, pos, player, hand, stack, new ItemStack(Items.AIR), (BlockState blockState) -> {
            return (Integer)blockState.get(LeveledCheeseMoldBlock.LEVEL)==AbstractCheeseMoldBlock.MAX_LEVEL;
        },SoundEvents.BLOCK_PISTON_EXTEND );

        world.getBlockState(pos).get(LeveledCheeseMoldBlock.LEVEL);


        return ActionResult.success(world.isClient);
    }

    /**
     * @param state BlockState of the block the interaction is happening on.
     * @param world The world this interaction is happening on, more useful for data querying but not very useful in
     *              this case.
     * @param pos Position of the block being interacted. Can also be used to get the BlockState.
     * @param player Player doing the interaction.
     * @param hand The hand of which is doing the interaction. This is for mainhand/offhand shenanegans that should be
     *             looked at later to patch exploits.
     * @param stack The stack in the hand doing the interaction. In this it should be a milk bucket of a sort.
     * @param output Should be a cheese block of some sort depending on the input.
     * @param predicate A function to determine when this should be allowed, this should be only when the mold is full.
     * @param soundEvent What sound this action should take.
     * @return ActionResult, tells the game if something succeeded or not.
     */
    static ActionResult emptyMold(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
                                  ItemStack stack, ItemStack output, Predicate<BlockState> predicate,
                                  SoundEvent soundEvent) {
        if(!predicate.test(state)){
            return ActionResult.PASS;
        }
        else{
            if (!world.isClient){
                Item item = stack.getItem();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
                // Need to add custom stats later.


                world.setBlockState(pos, ModBlocks.EMPTY_CHEESE_MOLD_BLOCK.getDefaultState());
                world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }
            return ActionResult.success(world.isClient);
        }
    }

    /**
     * @param state BlockState of the block the interaction is happening on.
     * @param world The world this interaction is happening on, more useful for data querying but not very useful in
     *              this case.
     * @param pos Position of the block being interacted. Can also be used to get the BlockState.
     * @param player Player doing the interaction.
     * @param hand The hand of which is doing the interaction. This is for mainhand/offhand shenanegans that should be
     *             looked at later to patch exploits.
     * @param stack The stack in the hand doing the interaction. In this it should be a milk bucket of a sort.
     * @param sound What sound this action should take.
     * @return ActionResult, tells the game if something succeeded or not.
     */
    static ActionResult fillMold(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack,
                                 BlockState state, SoundEvent sound){
        if(!world.isClient){
            Item item = stack.getItem();
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
            // add custom stats later

            world.setBlockState(pos, state);
            world.playSound((PlayerEntity)null, pos, sound, SoundCategory.BLOCKS, 1.0F, 0.5F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

            //check if cheese mold is full and then drop the item
            if(world.getBlockState(pos).get(LeveledCheeseMoldBlock.LEVEL).equals(AbstractCheeseMoldBlock.MAX_LEVEL)){
                dropCheese(state, world, pos, player, hand, stack);
            }

        }
        return ActionResult.success(world.isClient);
    }


}


