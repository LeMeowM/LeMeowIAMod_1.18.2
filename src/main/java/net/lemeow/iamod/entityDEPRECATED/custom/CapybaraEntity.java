package net.lemeow.iamod.entityDEPRECATED.custom;

import net.lemeow.iamod.entityDEPRECATED.ai.WaterChillGoal;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CapybaraEntity extends AbstractCapybaraEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(16, ItemStack.EMPTY);

    public CapybaraEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }



    protected int getInventorySize() {
        return this.hasChest() ? 17 : super.getInventorySize();
    }

    protected void dropInventory() {
        super.dropInventory();
        if (this.hasChest()) {
            if (!this.world.isClient) {
                for(ItemStack itemStack: inventory){
                    this.dropStack(itemStack);
                }
                this.dropItem(Blocks.CHEST);
            }
            this.setHasChest(false);
        }

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("ChestedHorse", this.hasChest());
        if (this.hasChest()) {
            NbtList nbtList = new NbtList();

            for(int i = 2; i < this.items.size(); ++i) {
                ItemStack itemStack = this.items.getStack(i);
                if (!itemStack.isEmpty()) {
                    NbtCompound nbtCompound = new NbtCompound();
                    nbtCompound.putByte("Slot", (byte)i);
                    itemStack.writeNbt(nbtCompound);
                    nbtList.add(nbtCompound);
                }
            }

            nbt.put("Items", nbtList);
        }

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasChest(nbt.getBoolean("ChestedHorse"));
        this.onChestedStatusChanged();
        if (this.hasChest()) {
            NbtList nbtList = nbt.getList("Items", 10);

            for(int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                int j = nbtCompound.getByte("Slot") & 255;
                if (j >= 2 && j < this.items.size()) {
                    this.items.setStack(j, ItemStack.fromNbt(nbtCompound));
                }
            }
        }

        this.updateSaddle();
    }



    public static DefaultAttributeContainer.Builder setAttributes(){
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5)
                .add(EntityAttributes.GENERIC_ARMOR, 5);
    }

    @Override
    protected void initGoals(){
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.75));
        this.goalSelector.add(2, new AnimalMateGoal(this, 0.6));
        this.goalSelector.add(3, new TemptGoal(this, 0.6, Ingredient.ofItems(Items.MELON_SLICE), false));
        this.goalSelector.add(3, new TemptGoal(this, 0.6, Ingredient.ofItems(Items.MELON), false));
        this.goalSelector.add(3, new TemptGoal(this, 0.6, Ingredient.ofItems(Items.SUGAR), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 0.7));
        // this 100% needs fixing and will not be ok for future reference, but it works for now
        this.goalSelector.add(5, new WaterChillGoal(this));
        this.goalSelector.add(6, new WanderAroundPointOfInterestGoal(this, 0.5, false));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.5));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(9, new LookAtEntityGoal(this, WolfEntity.class, 6.0f));
        this.goalSelector.add(9, new LookAtEntityGoal(this, CatEntity.class, 6.0f));
        this.goalSelector.add(10, new LookAroundGoal(this));
    }


    // need to make the 2 animations pronto
    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event){
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.idle", true));
        return PlayState.CONTINUE;
    }



    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return null;
    }



    // oh god make sure this is not final lmfaooooo
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_DONKEY_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PANDA_STEP, 0.15f, 1.0f);
    }
}
