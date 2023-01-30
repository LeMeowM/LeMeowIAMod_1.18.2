package net.lemeow.iamod.block.custom;

import net.lemeow.iamod.block.entity.InfusionTableBlockEntity;
import net.lemeow.iamod.block.entity.ModBlockEntities;
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
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

/**
 * This class describes how the physical block behaves and is composed of a BlockEntity in {@link InfusionTableBlockEntity}.
 * Opens a {@link net.lemeow.iamod.screen.infusiontable.InfusionTableScreen} when right-clicked.
 */
public class InfusionTableBlock extends BlockWithEntity implements BlockEntityProvider {

    /**
     * Direction this block is facing,
     * HORIZONTAL_FACING makes sure that the block does not go upside down and only turns around
     */
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    /**
     * Block's hitbox, named SHAPE_N because it's supposed to be rotated but the block has a rotational
     * symmetry of order 4, so it doesn't matter whatsoever.
     * @Author BlockBench <33
     */
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(6, 10, 0, 10, 13, 4),
            Block.createCuboidShape(1, 0, 1, 15, 2, 15),
            Block.createCuboidShape(2, 2, 2, 14, 7, 14),
            Block.createCuboidShape(1, 6, 13, 15, 11, 15),
            Block.createCuboidShape(1, 6, 1, 3, 11, 15),
            Block.createCuboidShape(13, 6, 1, 15, 11, 15),
            Block.createCuboidShape(1, 6, 1, 15, 11, 3),
            Block.createCuboidShape(0, 10, 6, 4, 13, 10),
            Block.createCuboidShape(12, 10, 6, 16, 13, 10),
            Block.createCuboidShape(6, 10, 12, 10, 13, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    /**
     * Constructor
     * @param settings block's settings for {@link BlockWithEntity}
     */
    public InfusionTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_N;
    }

    /**
     * Usual Method to know how to place the block given which block was clicked to place it.
     * This one places it facing the opposite direction to the face of the block that the player is facing towards
     * @param ctx An {@link ItemPlacementContext}, telling us which block its placed against and if it can replace any blocks
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



    /**
     * Gives the renderer the type of block this should render, hardcoded to ENTITYBLOCK_ANIMATED
     * because this block is supposed to be animated
     * @param state The Block that should be effected
     * @return BlockRenderType.ENTITYBLOCK_ANIMATED for rendering
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * Creates an InfusionTableBlockEntity to be used at the pos and state of the block.
     * @param pos Position to place the new BlockEntity
     * @param state The BlockState (read: texture) that this block entity should have
     * @return A new InfusionTableBlockEntity placed at pos with BlockState state
     */
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InfusionTableBlockEntity(pos, state);
    }

    /**
     * Accesses the world to do stuff when the block is destroyed or displaced.
     * Will check if BlockEntity is the correct BlockEntity, and then drop all the items from inside it's inventory
     * onto the world.
     * @param state The block that is getting replaced
     * @param world The world that this is happening in
     * @param pos The position of the block
     * @param newState The state (new block) after the entity gets replaced, usually air but could be something else
     * @param moved Checking if it was pushed by a piston instead, not useful for us but is an artifact from this being
     *              used by non-blockentities too
     */
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

    /**
     * What the game does when right-clicking this block.
     * Opens a GUI when the player clicks on the Block, also discerns if there is something else that could happen although
     * the logic isn't coded such as maybe flint and steel would do something else etc...
     * @param state The BlockState that is creating the GUI/action
     * @param world The world that this is happening in
     * @param pos The position of the affected block
     * @param player The player that did that action
     * @param hand The hand that is holding whatever thing that is right-clicking the block, not used here but could be
     * @param hit
     * @return
     */
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

    /**
     * A ticker to check everything that needs to be done for this block every tick
     * @param world The world this is happening in
     * @param state The specific block that is calling this
     * @param type Type of BlockEntity, in this case, its InfusionTable and only that
     * @return Either the ticker, if this entity exists or null if it doesn't, the ticker runs {@link InfusionTableBlockEntity#tick} every tick
     * @param <T> Doesn't matter, the return just needs to be a class that extends BlockEntity
     */
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
