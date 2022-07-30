package net.lemeow.aimod.entity.ai;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

import java.util.Iterator;

public class WaterChillGoal extends Goal {
    private final PathAwareEntity mob;

    public WaterChillGoal(PathAwareEntity mob) {
        this.mob = mob;
    }


    // im 90% sure this isSwimming is talking about swimming like a player but im actually not sure?? -> have to test this when i get a model
    @Override
    public boolean canStart() {
        return this.mob.isOnGround() && !this.mob.world.getFluidState(this.mob.getBlockPos()).isIn(FluidTags.WATER);
    }

    public void start(){
        BlockPos blockPos = null;
        Iterable<BlockPos> iterable = BlockPos.iterate(MathHelper.floor(this.mob.getX() - 2.0D), MathHelper.floor(this.mob.getY() - 2.0D), MathHelper.floor(this.mob.getZ() - 2.0D), MathHelper.floor(this.mob.getX() + 2.0D), this.mob.getBlockY(), MathHelper.floor(this.mob.getZ() + 2.0D));
        Iterator var3 = iterable.iterator();

        while(var3.hasNext()) {
            BlockPos blockPos2 = (BlockPos)var3.next();
            if ((this.mob.world.getFluidState(blockPos2).isIn(FluidTags.WATER) &&
                    this.mob.world.getBlockState(blockPos2.down()).isSolidSurface(this.mob.world, blockPos2,
                            this.mob, Direction.UP))
                    || ((this.mob.world.getBlockState(blockPos2).getBlock() instanceof LeveledCauldronBlock) &&
                    ((LeveledCauldronBlock) (this.mob.world.getBlockState(blockPos2).getBlock())).getComparatorOutput(
                            this.mob.world.getBlockState(blockPos2), this.mob.world, blockPos2
                    ) > 0))
            {
                blockPos = blockPos2;
                break;
            }
        }

        if (blockPos != null) {
            this.mob.getMoveControl().moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0D);
        }


    }
}
