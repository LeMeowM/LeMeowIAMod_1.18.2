/**

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

public interface CheeseMoldBehavior {
    Map<Item, CheeseMoldBehavior> EMPTY_CHEESE_MOLD = createMap();
    Map<Item, CheeseMoldBehavior> COW_CHEESE_MOLD = createMap();
    Map<Item, CheeseMoldBehavior> SHEEP_CHEESE_MOLD = createMap();
    Map<Item, CheeseMoldBehavior> GOAT_CHEESE_MOLD = createMap();
    // create blocks for milk molds??
    CheeseMoldBehavior ADD_COW_MILK = (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) ->{
        return CheeseMoldBehavior.fillMold( world, pos, player, hand, stack, ModBlocks.COW_CHEESE_MOLD_BLOCK.getDefaultState().with(LeveledCheeseMoldBlock.LEVEL, 1), SoundEvents.ITEM_BUCKET_EMPTY);
    };
    CheeseMoldBehavior ADD_GOAT_MILK = (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) ->{
        return fillMold( world, pos, player, hand, stack, ModBlocks.GOAT_CHEESE_MOLD_BLOCK.getDefaultState().with(LeveledCheeseMoldBlock.LEVEL, 1), SoundEvents.ITEM_BUCKET_EMPTY);
    };
    CheeseMoldBehavior ADD_SHEEP_MILK = (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) ->{
        return fillMold( world, pos, player, hand, stack, ModBlocks.SHEEP_CHEESE_MOLD_BLOCK.getDefaultState().with(LeveledCheeseMoldBlock.LEVEL, 1), SoundEvents.ITEM_BUCKET_EMPTY);
    };
    CheeseMoldBehavior DROP_CHEESE = (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) ->{

    };





    static Object2ObjectOpenHashMap<Item, CheeseMoldBehavior> createMap(){
        return Util.make(new Object2ObjectOpenHashMap(), AbstractObject2ObjectFunction::defaultReturnValue);
    }


    static void registerBucketBehavior(Map<Item, CheeseMoldBehavior> behavior) {
        behavior.put(Items.MILK_BUCKET, ADD_COW_MILK);
        behavior.put(ModItems.SHEEP_MILK_BUCKET, ADD_SHEEP_MILK);
        behavior.put(ModItems.GOAT_MILK_BUCKET, ADD_GOAT_MILK);
    }

    static void registerBehavior(){
        // adds cow, sheep and goat milk adding behavior to an empty cheese mold
        registerBucketBehavior(EMPTY_CHEESE_MOLD);


        //
        registerBucketBehavior(COW_CHEESE_MOLD);
        COW_CHEESE_MOLD.put(Items.MILK_BUCKET, (BlockState state, World world, BlockPos pos, PlayerEntity player,
                                                Hand hand, ItemStack stack )-> {
            return emptyMold(state, world, pos, player, hand, stack, new ItemStack(Items.BUCKET), (BlockState statex) ->{
                return (Integer)statex.get(LeveledCheeseMoldBlock.LEVEL)==AbstractCheeseMoldBlock.MAX_LEVEL;
                }, SoundEvents.ENTITY_COW_MILK);
        });



    }

    static ActionResult dropCheese(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack){
        Block block = world.getBlockState(pos).getBlock();
        emptyMold(state, world, pos, player, hand, stack,  )
    }



    static ActionResult emptyMold(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, ItemStack output, Predicate<BlockState> predicate, SoundEvent soundEvent) {
        if(!predicate.test(state)){
            return ActionResult.PASS;
        }
        else{
            if (!world.isClient){
                Item item = stack.getItem();
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, output));
                // add stats

                // needs to drop the item that is matching the block
                Block.dropStack(world, pos, output);

                world.setBlockState(pos, ModBlocks.EMPTY_CHEESE_MOLD_BLOCK.getDefaultState());
                world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }
            return ActionResult.success(world.isClient);
        }
    }

    static ActionResult fillMold(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, BlockState state, SoundEvent sound){
        if(!world.isClient){
            Item item = stack.getItem();
            player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.BUCKET)));
            // maybe add custom stat??

            world.setBlockState(pos, state);
            world.playSound((PlayerEntity)null, pos, sound, SoundCategory.BLOCKS, 1.0F, 0.5F);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
        return ActionResult.success(world.isClient);
    }


}

 */