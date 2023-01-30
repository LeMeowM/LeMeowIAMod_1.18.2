package net.lemeow.iamod.block.custom.cheesemold;



import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;


// todo: fix this
public class AbstractCheeseMoldBlock extends Block {
    public static final int MAX_LEVEL = 8;
    protected static final VoxelShape OUTLINE_SHAPE;
    private static final VoxelShape RAYCAST_SHAPE;

    static {

        RAYCAST_SHAPE = createCuboidShape(2.0, 4.0, 2.0, 14.0, 16.0, 14.0);
        OUTLINE_SHAPE = VoxelShapes.combineAndSimplify(createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
                VoxelShapes.union(
                    createCuboidShape(0.0, 0.0, 4.0, 16.0, 3.0, 12.0),
                    createCuboidShape(4.0, 0.0, 0.0, 12.0, 3.0, 16.0),
                    createCuboidShape(2.0, 0.0, 2.0, 14.0, 3.0, 14.0), RAYCAST_SHAPE),
                BooleanBiFunction.ONLY_FIRST);
    }
        // register it all in an array
    public static void registerCheesableItems (Item item){

    }


    public AbstractCheeseMoldBlock(Settings settings) {
            super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        return ActionResult.success(player.handSwinging);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }


}
