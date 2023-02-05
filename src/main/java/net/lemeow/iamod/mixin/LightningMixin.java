package net.lemeow.iamod.mixin;

import net.lemeow.iamod.IAMod;
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
	/**
	 * The injection part of the Mixin, specifically, this will be called right after the LightningEntity starts spawning
	 * fire in its tick() method. All this method does is call the makeSand() method as well as a logging method for
	 * easier stack-tracking of the errors that come from Mixins.
	 * @param info This is taking the information that the spawnFire() method outputs and storing it; any change to it will change what spawnFire() outputs.
	 *                Although I am not currently using it the @At annotation requires its use.
	 */
	@Inject(
			at = @At(value = "INVOKE",
					target = "Lnet/minecraft/entity/LightningEntity;spawnFire(I)V",
					shift = At.Shift.AFTER
			),
			method = "tick")
	private void iamodLightningAddGlass(CallbackInfo info) {
		IAMod.LOGGER.info("Injecting into LightningEntity");
		makeSand();
	}


	/**
	 * As we are Mixing into the LightningEntity class, this is technically a child of that class. However, the compiler
	 * doesn't actually know that, and so you have to do the casting voodoo magic as seen in the first line, used to get
	 * the position of the entity. Then, this function replaces all sand it finds on "walks" going downward with glass,
	 * thereby making a "so hot it made glass in the sand" feeling.
	 * If I had more time, I would have made an in-between block which would have been minable and collectable but so far
	 * this is cool.
	 */
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
