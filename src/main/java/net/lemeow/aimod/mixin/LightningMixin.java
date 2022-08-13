package net.lemeow.aimod.mixin;

import net.lemeow.aimod.AIMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public abstract class LightningMixin {
	@Inject(
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/entity/LightningEntity;spawnFire(I)V",
					shift = At.Shift.AFTER
			),
			method = "tick")
	private void aimodLightningAddGlass(CallbackInfo info) {
		AIMod.LOGGER.info("Injecting into LightningEntity");
		makeSand();
	}




	private void makeSand(){
		BlockPos positionLightning = ((LightningEntity)(Object)this).getBlockPos();
		BlockState glass = Blocks.GLASS.getDefaultState();

		int x, y, z, l;
		l = ((int) (Math.random()*3))+1;
		for(int i=0; i< l; ++i){
			x = ((int) (Math.random()*3))-1;
			y = ((int) (Math.random()*3))-2;
			z = ((int) (Math.random()*3))-1;

			if(((LightningEntity)(Object)this).world.getBlockState(positionLightning.add(x,y,z)).isOf(Blocks.SAND)){
				((LightningEntity)(Object)this).world.setBlockState(positionLightning.add(x,y,z), glass);
			}

		}

	}

}
