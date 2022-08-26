package net.lemeow.aimod.block.custom;

import net.lemeow.aimod.block.entity.InfusionTableBlockEntity;
import net.lemeow.aimod.block.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class InfusionTableBlock extends BlockWithEntity implements BlockEntityProvider {
    /**
     * Direction this block is facing,
     * HORIZONTAL_FACING makes sure that the block does not go upside down and only turns around
     */
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public InfusionTableBlock(Settings settings) {
        super(settings);
    }

    /**
     * Usual Method to know how to place the block given which block was clicked to place it.
     * This one places it facing the opposite direction to the face of the block that the player is facing towards
     * @param ctx An ItemPlacementContext, telling us which block its placed against and if it can replace any blocks
     * @return Default block facing the opposite direction the player is facing
     */
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    /**
     * Turns the block around, debug stick method
     * @param state The Block that should be rotated
     * @param rotation The direction it should face
     * @return The BlockState facing the right direction
     */
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }


    /**
     * Mirror the block, debug stick method
     * @param state The Block that should be mirrored
     * @param mirror The side it mirrors (front back/ left right)
     * @return
     */
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    /**
     * I suspect this is to build a Blockstate from a Block, and it adds a property of which direction its facing.
     * End of the day I do not know, but its necessary for inheritance:
     * code does not compile without this.
     * @param builder A Block -> Blockstate builder
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // Block Entity


    /**
     * Gives the renderer the type of block this should render, hardcoded to ENTITYBLOCK_ANIMATED
     * because this block is supposed to be animated
     * @param state The Block that should be effected
     * @return BlockRenderType.ENTITYBLOCK_ANIMATED for rendering
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InfusionTableBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof InfusionTableBlockEntity){
                ItemScatterer.spawn(world, pos, (InfusionTableBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null){
                player.openHandledScreen(screenHandlerFactory);
            }

        }
        return ActionResult.SUCCESS;

    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.INFUSION_TABLE, InfusionTableBlockEntity::tick);
    }

    /**
     * Returns a comparator output depending on the inventory of the InfusionTableBlock.
     * The math goes floor(0.02734375* number of Items in fuel slots) + 8 * centre slot = power of comparator.
     * @param state The Block that should be effected
     * @param world The world that this block is in
     * @param pos The position of the block
     * @return A comparator output for this block
     */
    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        int count = 0, inv = 0;
        if (blockEntity instanceof InfusionTableBlockEntity) {
            DefaultedList<ItemStack> inventory = ((InfusionTableBlockEntity) blockEntity).getItems();
            for(int i = 0; i<4; i++){
                inv+= inventory.get(i).getCount();
            }
            count += inventory.get(4).getCount()*8 + (int)(inv*0.02734375);
        }
        return count;
    }

    /**
     * True if block can be read by comparators for efficiency's sake.
     * Shouldn't be used apart from the comparator block code.
     * @param state The Block that should be effected
     * @return True
     */
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }
}
