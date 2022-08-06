package net.lemeow.aimod.entity.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;


public class AbstractCapybaraEntity extends HorseBaseEntity implements InventoryChangedListener {
    private static final TrackedData<Boolean> CHEST;

    static{
        CHEST = DataTracker.registerData(AbstractCapybaraEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }


    protected AbstractCapybaraEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super((EntityType<? extends HorseBaseEntity>) entityType, world);
        this.playExtraHorseSounds = false;

    }


    public boolean hasChest() {
        return this.dataTracker.get(CHEST);
    }

    public void setHasChest(boolean hasChest) {
        this.dataTracker.set(CHEST, hasChest);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(CHEST, false);
    }

    @Override
    protected boolean receiveFood(PlayerEntity player, ItemStack item) {
        boolean bl = false;
        float f = 0.0F;
        int i = 0;
        int j = 0;
        if (item.isOf(Items.MELON_SLICE)) {
            f = 2.0F;
            i = 20;
            j = 3;
        } else if (item.isOf(Items.SUGAR)) {
            f = 1.0F;
            i = 30;
            j = 3;
        } else if (item.isOf(Blocks.MELON.asItem())) {
            f = 20.0F;
            i = 180;
        } else if (item.isOf(Items.GOLDEN_APPLE) || item.isOf(Items.ENCHANTED_GOLDEN_APPLE)) {
            f = 10.0F;
            i = 240;
            j = 10;
            if (!this.world.isClient && this.getBreedingAge() == 0 && !this.isInLove()) {
                bl = true;
                this.lovePlayer(player);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);
            bl = true;
        }

        if (this.isBaby() && i > 0) {
            this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), 0.0D, 0.0D, 0.0D);
            if (!this.world.isClient) {
                this.growUp(i);
            }

            bl = true;
        }


        if (bl) {
            this.emitGameEvent(GameEvent.EAT, this.getCameraBlockPos());
        }

        return bl;
    }

    @Override
    protected int getInventorySize() {
        return 1;
    }

    public double getMountedHeightOffset() {
        return super.getMountedHeightOffset() - 0.5D;
    }

    protected void dropInventory() {
        super.dropInventory();
        if (this.hasChest()) {
            if (!this.world.isClient) {
                this.dropItem(Blocks.CHEST);
            }
            this.setHasChest(false);
        }

    }




}
