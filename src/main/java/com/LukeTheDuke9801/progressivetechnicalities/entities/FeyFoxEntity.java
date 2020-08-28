package com.LukeTheDuke9801.progressivetechnicalities.entities;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.NymphariumFluidBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.FeyFood;
import com.google.common.collect.Lists;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class FeyFoxEntity extends AnimalEntity {
    private static final DataParameter<Integer> FOX_TYPE = EntityDataManager.createKey(FeyFoxEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Byte> FOX_FLAGS = EntityDataManager.createKey(FeyFoxEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Optional<UUID>> TRUSTED_UUID_SECONDARY = EntityDataManager.createKey(FeyFoxEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    private static final DataParameter<Optional<UUID>> TRUSTED_UUID_MAIN = EntityDataManager.createKey(FeyFoxEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    private static final Predicate<ItemEntity> TRUSTED_TARGET_SELECTOR = (p_213489_0_) -> {
        return !p_213489_0_.cannotPickup() && p_213489_0_.isAlive();
    };
    private static final Predicate<Entity> STALKABLE_PREY = (p_213470_0_) -> {
        if (!(p_213470_0_ instanceof LivingEntity)) {
            return false;
        } else {
            LivingEntity livingentity = (LivingEntity)p_213470_0_;
            return livingentity.getLastAttackedEntity() != null && livingentity.getLastAttackedEntityTime() < livingentity.ticksExisted + 600;
        }
    };
    private static final Predicate<Entity> SHOULD_AVOID = (p_213463_0_) -> {
        return !p_213463_0_.isDiscrete() && EntityPredicates.CAN_AI_TARGET.test(p_213463_0_);
    };
    private float interestedAngle;
    private float interestedAngleO;
    private float crouchAmount;
    private float crouchAmountO;

    public FeyFoxEntity(EntityType<? extends FeyFoxEntity> type, World worldIn) {
        super(type, worldIn);
        this.lookController = new FeyFoxEntity.LookHelperController();
        this.moveController = new FeyFoxEntity.MoveHelperController();
        this.setPathPriority(PathNodeType.DANGER_OTHER, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_OTHER, 0.0F);
        this.setCanPickUpLoot(true);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(TRUSTED_UUID_SECONDARY, Optional.empty());
        this.dataManager.register(TRUSTED_UUID_MAIN, Optional.empty());
        this.dataManager.register(FOX_TYPE, 0);
        this.dataManager.register(FOX_FLAGS, (byte)0);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FeyFoxEntity.SwimGoal());
        this.goalSelector.addGoal(1, new FeyFoxEntity.JumpGoal());
        this.goalSelector.addGoal(2, new FeyFoxEntity.PanicGoal(2.2D));
        this.goalSelector.addGoal(3, new FeyFoxEntity.MateGoal(1.0D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, PlayerEntity.class, 16.0F, 1.6D, 1.4D, (p_213497_1_) -> {
            return SHOULD_AVOID.test(p_213497_1_) && !this.isTrustedUUID(p_213497_1_.getUniqueID()) && !this.isFoxAggroed();
        }));
        this.goalSelector.addGoal(6, new FeyFoxEntity.PounceGoal());
        this.goalSelector.addGoal(6, new FeyFoxEntity.FindShelterGoal(1.25D));
        this.goalSelector.addGoal(7, new FeyFoxEntity.BiteGoal((double)1.2F, true));
        this.goalSelector.addGoal(7, new FeyFoxEntity.SleepGoal());
        this.goalSelector.addGoal(8, new FeyFoxEntity.FollowGoal(this, 1.25D));
        this.goalSelector.addGoal(9, new FeyFoxEntity.StrollGoal(32, 200));
        this.goalSelector.addGoal(10, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(11, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(12, new FeyFoxEntity.WatchGoal(this, PlayerEntity.class, 24.0F));
        this.goalSelector.addGoal(13, new FeyFoxEntity.SitAndLookGoal());
        this.targetSelector.addGoal(3, new FeyFoxEntity.RevengeGoal(LivingEntity.class, false, false, (p_213493_1_) -> {
            return STALKABLE_PREY.test(p_213493_1_) && !this.isTrustedUUID(p_213493_1_.getUniqueID());
        }));
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == NymphariumFluidBlock.DAMAGE_SOURCE) this.heal(amount);
        return super.attackEntityFrom(source, amount);
    }

    public boolean isInvulnerableTo(DamageSource source) {
        if (source == NymphariumFluidBlock.DAMAGE_SOURCE) return true;
        return super.isInvulnerableTo(source);
    }

    public SoundEvent getEatSound(ItemStack itemStackIn) {
        return SoundEvents.ENTITY_FOX_EAT;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void livingTick() {
        if (!this.world.isRemote && this.isAlive() && this.isServerWorld()) {
            LivingEntity livingentity = this.getAttackTarget();
            if (livingentity == null || !livingentity.isAlive()) {
                this.setCrouching(false);
                this.func_213502_u(false);
            }
        }

        if (this.isSleeping() || this.isMovementBlocked()) {
            this.isJumping = false;
            this.moveStrafing = 0.0F;
            this.moveForward = 0.0F;
        }

        super.livingTick();
        if (this.isFoxAggroed() && this.rand.nextFloat() < 0.05F) {
            this.playSound(SoundEvents.ENTITY_FOX_AGGRO, 1.0F, 1.0F);
        }

    }

    /**
     * Dead and sleeping entities cannot move
     */
    protected boolean isMovementBlocked() {
        return this.getHealth() <= 0.0F;
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 45) {
            ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!itemstack.isEmpty()) {
                for(int i = 0; i < 8; ++i) {
                    Vec3d vec3d = (new Vec3d(((double)this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).rotatePitch(-this.rotationPitch * ((float)Math.PI / 180F)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F));
                    this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, itemstack), this.getPosX() + this.getLookVec().x / 2.0D, this.getPosY(), this.getPosZ() + this.getLookVec().z / 2.0D, vec3d.x, vec3d.y + 0.05D, vec3d.z);
                }
            }
        } else {
            super.handleStatusUpdate(id);
        }

    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.3F);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    public FeyFoxEntity createChild(AgeableEntity ageable) {
        FeyFoxEntity baby = ModEntityTypes.FEY_FOX.create(this.world);
        return baby;
    }

    /**
     * Decreases ItemStack size by one
     */
    protected void consumeItemFromStack(PlayerEntity player, ItemStack stack) {
        if (this.isBreedingItem(stack)) {
            this.playSound(this.getEatSound(stack), 1.0F, 1.0F);
        }

        super.consumeItemFromStack(player, stack);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.85F : 0.4F;
    }

    private List<UUID> getTrustedUUIDs() {
        List<UUID> list = Lists.newArrayList();
        list.add(this.dataManager.get(TRUSTED_UUID_SECONDARY).orElse((UUID)null));
        list.add(this.dataManager.get(TRUSTED_UUID_MAIN).orElse((UUID)null));
        return list;
    }

    private void addTrustedUUID(@Nullable UUID uuidIn) {
        if (this.dataManager.get(TRUSTED_UUID_SECONDARY).isPresent()) {
            this.dataManager.set(TRUSTED_UUID_MAIN, Optional.ofNullable(uuidIn));
        } else {
            this.dataManager.set(TRUSTED_UUID_SECONDARY, Optional.ofNullable(uuidIn));
        }

    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        List<UUID> list = this.getTrustedUUIDs();
        ListNBT listnbt = new ListNBT();

        for(UUID uuid : list) {
            if (uuid != null) {
                listnbt.add(NBTUtil.writeUniqueId(uuid));
            }
        }

        compound.put("TrustedUUIDs", listnbt);
        compound.putBoolean("Sleeping", this.isSleeping());
        compound.putBoolean("Sitting", this.isSitting());
        compound.putBoolean("Crouching", this.isCrouching());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        ListNBT listnbt = compound.getList("TrustedUUIDs", 10);

        for(int i = 0; i < listnbt.size(); ++i) {
            this.addTrustedUUID(NBTUtil.readUniqueId(listnbt.getCompound(i)));
        }

        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setSitting(compound.getBoolean("Sitting"));
        this.setCrouching(compound.getBoolean("Crouching"));
    }

    public boolean isSitting() {
        return this.getFoxFlag(1);
    }

    public void setSitting(boolean p_213466_1_) {
        this.setFoxFlag(1, p_213466_1_);
    }

    public boolean isStuck() {
        return this.getFoxFlag(64);
    }

    private void setStuck(boolean p_213492_1_) {
        this.setFoxFlag(64, p_213492_1_);
    }

    private boolean isFoxAggroed() {
        return this.getFoxFlag(128);
    }

    private void setFoxAggroed(boolean p_213482_1_) {
        this.setFoxFlag(128, p_213482_1_);
    }

    /**
     * Returns whether player is sleeping or not
     */
    public boolean isSleeping() {
        return this.getFoxFlag(32);
    }

    private void setSleeping(boolean p_213485_1_) {
        this.setFoxFlag(32, p_213485_1_);
    }

    private void setFoxFlag(int p_213505_1_, boolean p_213505_2_) {
        if (p_213505_2_) {
            this.dataManager.set(FOX_FLAGS, (byte)(this.dataManager.get(FOX_FLAGS) | p_213505_1_));
        } else {
            this.dataManager.set(FOX_FLAGS, (byte)(this.dataManager.get(FOX_FLAGS) & ~p_213505_1_));
        }

    }

    private boolean getFoxFlag(int p_213507_1_) {
        return (this.dataManager.get(FOX_FLAGS) & p_213507_1_) != 0;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        if (this.isServerWorld()) {
            if (this.isInWater() || this.getAttackTarget() != null || this.world.isThundering()) {
                this.wake();
            }

            if (this.isInWater() || this.isSleeping()) {
                this.setSitting(false);
            }

            if (this.isStuck() && this.world.rand.nextFloat() < 0.2F) {
                BlockPos blockpos = new BlockPos(this);
                BlockState blockstate = this.world.getBlockState(blockpos);
                this.world.playEvent(2001, blockpos, Block.getStateId(blockstate));
            }
        }

        this.interestedAngleO = this.interestedAngle;
        if (this.func_213467_eg()) {
            this.interestedAngle += (1.0F - this.interestedAngle) * 0.4F;
        } else {
            this.interestedAngle += (0.0F - this.interestedAngle) * 0.4F;
        }

        this.crouchAmountO = this.crouchAmount;
        if (this.isCrouching()) {
            this.crouchAmount += 0.2F;
            if (this.crouchAmount > 3.0F) {
                this.crouchAmount = 3.0F;
            }
        } else {
            this.crouchAmount = 0.0F;
        }

    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack stack) {
        return FeyFood.isFeyFood(stack.getItem());
    }

    protected void onChildSpawnFromEgg(PlayerEntity playerIn, AgeableEntity child) {
        ((FeyFoxEntity)child).addTrustedUUID(playerIn.getUniqueID());
    }

    public boolean func_213480_dY() {
        return this.getFoxFlag(16);
    }

    public void func_213461_s(boolean p_213461_1_) {
        this.setFoxFlag(16, p_213461_1_);
    }

    public boolean func_213490_ee() {
        return this.crouchAmount == 3.0F;
    }

    public void setCrouching(boolean p_213451_1_) {
        this.setFoxFlag(4, p_213451_1_);
    }

    public boolean isCrouching() {
        return this.getFoxFlag(4);
    }

    public void func_213502_u(boolean p_213502_1_) {
        this.setFoxFlag(8, p_213502_1_);
    }

    public boolean func_213467_eg() {
        return this.getFoxFlag(8);
    }

    @OnlyIn(Dist.CLIENT)
    public float func_213475_v(float p_213475_1_) {
        return MathHelper.lerp(p_213475_1_, this.interestedAngleO, this.interestedAngle) * 0.11F * (float)Math.PI;
    }

    @OnlyIn(Dist.CLIENT)
    public float func_213503_w(float p_213503_1_) {
        return MathHelper.lerp(p_213503_1_, this.crouchAmountO, this.crouchAmount);
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        if (this.isFoxAggroed() && entitylivingbaseIn == null) {
            this.setFoxAggroed(false);
        }

        super.setAttackTarget(entitylivingbaseIn);
    }

    protected int calculateFallDamage(float p_225508_1_, float p_225508_2_) {
        return MathHelper.ceil((p_225508_1_ - 5.0F) * p_225508_2_);
    }

    private void wake() {
        this.setSleeping(false);
    }

    private void func_213499_en() {
        this.func_213502_u(false);
        this.setCrouching(false);
        this.setSitting(false);
        this.setSleeping(false);
        this.setFoxAggroed(false);
        this.setStuck(false);
    }

    private boolean func_213478_eo() {
        return !this.isSleeping() && !this.isSitting() && !this.isStuck();
    }

    /**
     * Plays living's sound at its position
     */
    public void playAmbientSound() {
        SoundEvent soundevent = this.getAmbientSound();
        if (soundevent == SoundEvents.ENTITY_FOX_SCREECH) {
            this.playSound(soundevent, 2.0F, this.getSoundPitch());
        } else {
            super.playAmbientSound();
        }

    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        if (this.isSleeping()) {
            return SoundEvents.ENTITY_FOX_SLEEP;
        } else {
            if (!this.world.isDaytime() && this.rand.nextFloat() < 0.1F) {
                List<PlayerEntity> list = this.world.getEntitiesWithinAABB(PlayerEntity.class, this.getBoundingBox().grow(16.0D, 16.0D, 16.0D), EntityPredicates.NOT_SPECTATING);
                if (list.isEmpty()) {
                    return SoundEvents.ENTITY_FOX_SCREECH;
                }
            }

            return SoundEvents.ENTITY_FOX_AMBIENT;
        }
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_FOX_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_FOX_DEATH;
    }

    private boolean isTrustedUUID(UUID p_213468_1_) {
        return this.getTrustedUUIDs().contains(p_213468_1_);
    }

    public static boolean func_213481_a(FeyFoxEntity p_213481_0_, LivingEntity p_213481_1_) {
        double d0 = p_213481_1_.getPosZ() - p_213481_0_.getPosZ();
        double d1 = p_213481_1_.getPosX() - p_213481_0_.getPosX();
        double d2 = d0 / d1;
        int i = 6;

        for(int j = 0; j < 6; ++j) {
            double d3 = d2 == 0.0D ? 0.0D : d0 * (double)((float)j / 6.0F);
            double d4 = d2 == 0.0D ? d1 * (double)((float)j / 6.0F) : d3 / d2;

            for(int k = 1; k < 4; ++k) {
                if (!p_213481_0_.world.getBlockState(new BlockPos(p_213481_0_.getPosX() + d4, p_213481_0_.getPosY() + (double)k, p_213481_0_.getPosZ() + d3)).getMaterial().isReplaceable()) {
                    return false;
                }
            }
        }

        return true;
    }

    public class AlertablePredicate implements Predicate<LivingEntity> {
        public boolean test(LivingEntity p_test_1_) {
            if (p_test_1_ instanceof FeyFoxEntity) {
                return false;
            } else if (!(p_test_1_ instanceof ChickenEntity) && !(p_test_1_ instanceof RabbitEntity) && !(p_test_1_ instanceof MonsterEntity)) {
                if (p_test_1_ instanceof TameableEntity) {
                    return !((TameableEntity)p_test_1_).isTamed();
                } else if (!(p_test_1_ instanceof PlayerEntity) || !p_test_1_.isSpectator() && !((PlayerEntity)p_test_1_).isCreative()) {
                    if (FeyFoxEntity.this.isTrustedUUID(p_test_1_.getUniqueID())) {
                        return false;
                    } else {
                        return !p_test_1_.isSleeping() && !p_test_1_.isDiscrete();
                    }
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    abstract class BaseGoal extends Goal {
        private final EntityPredicate field_220816_b = (new EntityPredicate()).setDistance(12.0D).setLineOfSiteRequired().setCustomPredicate(FeyFoxEntity.this.new AlertablePredicate());

        private BaseGoal() {
        }

        protected boolean func_220813_g() {
            BlockPos blockpos = new BlockPos(FeyFoxEntity.this);
            return !FeyFoxEntity.this.world.canSeeSky(blockpos) && FeyFoxEntity.this.getBlockPathWeight(blockpos) >= 0.0F;
        }

        protected boolean func_220814_h() {
            return !FeyFoxEntity.this.world.getTargettableEntitiesWithinAABB(LivingEntity.class, this.field_220816_b, FeyFoxEntity.this, FeyFoxEntity.this.getBoundingBox().grow(12.0D, 6.0D, 12.0D)).isEmpty();
        }
    }

    class BiteGoal extends MeleeAttackGoal {
        public BiteGoal(double p_i50731_2_, boolean p_i50731_4_) {
            super(FeyFoxEntity.this, p_i50731_2_, p_i50731_4_);
        }

        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double d0 = this.getAttackReachSqr(enemy);
            if (distToEnemySqr <= d0 && this.attackTick <= 0) {
                this.attackTick = 20;
                this.attacker.attackEntityAsMob(enemy);
                FeyFoxEntity.this.playSound(SoundEvents.ENTITY_FOX_BITE, 1.0F, 1.0F);
            }

        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            FeyFoxEntity.this.func_213502_u(false);
            super.startExecuting();
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return !FeyFoxEntity.this.isSitting() && !FeyFoxEntity.this.isSleeping() && !FeyFoxEntity.this.isCrouching() && !FeyFoxEntity.this.isStuck() && super.shouldExecute();
        }
    }

    class FindShelterGoal extends FleeSunGoal {
        private int cooldown = 100;

        public FindShelterGoal(double p_i50724_2_) {
            super(FeyFoxEntity.this, p_i50724_2_);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (!FeyFoxEntity.this.isSleeping() && this.creature.getAttackTarget() == null) {
                if (FeyFoxEntity.this.world.isThundering()) {
                    return true;
                } else if (this.cooldown > 0) {
                    --this.cooldown;
                    return false;
                } else {
                    this.cooldown = 100;
                    BlockPos blockpos = new BlockPos(this.creature);
                    return FeyFoxEntity.this.world.isDaytime() && FeyFoxEntity.this.world.canSeeSky(blockpos) && !((ServerWorld)FeyFoxEntity.this.world).isVillage(blockpos) && this.func_220702_g();
                }
            } else {
                return false;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            FeyFoxEntity.this.func_213499_en();
            super.startExecuting();
        }
    }

    class FollowGoal extends FollowParentGoal {
        private final FeyFoxEntity owner;

        public FollowGoal(FeyFoxEntity p_i50735_2_, double p_i50735_3_) {
            super(p_i50735_2_, p_i50735_3_);
            this.owner = p_i50735_2_;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return !this.owner.isFoxAggroed() && super.shouldExecute();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.owner.isFoxAggroed() && super.shouldContinueExecuting();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.owner.func_213499_en();
            super.startExecuting();
        }
    }

    class JumpGoal extends Goal {
        int delay;

        public JumpGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return FeyFoxEntity.this.isStuck();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.shouldExecute() && this.delay > 0;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.delay = 40;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            FeyFoxEntity.this.setStuck(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            --this.delay;
        }
    }

    public class LookHelperController extends LookController {
        public LookHelperController() {
            super(FeyFoxEntity.this);
        }

        /**
         * Updates look
         */
        public void tick() {
            if (!FeyFoxEntity.this.isSleeping()) {
                super.tick();
            }

        }

        protected boolean func_220680_b() {
            return !FeyFoxEntity.this.func_213480_dY() && !FeyFoxEntity.this.isCrouching() && !FeyFoxEntity.this.func_213467_eg() & !FeyFoxEntity.this.isStuck();
        }
    }

    class MateGoal extends BreedGoal {
        public MateGoal(double p_i50738_2_) {
            super(FeyFoxEntity.this, p_i50738_2_);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            ((FeyFoxEntity)this.animal).func_213499_en();
            ((FeyFoxEntity)this.targetMate).func_213499_en();
            super.startExecuting();
        }

        /**
         * Spawns a baby animal of the same type.
         */
        protected void spawnBaby() {
            FeyFoxEntity baby = (FeyFoxEntity)this.animal.createChild(this.targetMate);
            if (baby != null) {
                ServerPlayerEntity serverplayerentity = this.animal.getLoveCause();
                ServerPlayerEntity serverplayerentity1 = this.targetMate.getLoveCause();
                ServerPlayerEntity serverplayerentity2 = serverplayerentity;
                if (serverplayerentity != null) {
                    baby.addTrustedUUID(serverplayerentity.getUniqueID());
                } else {
                    serverplayerentity2 = serverplayerentity1;
                }

                if (serverplayerentity1 != null && serverplayerentity != serverplayerentity1) {
                    baby.addTrustedUUID(serverplayerentity1.getUniqueID());
                }

                if (serverplayerentity2 != null) {
                    serverplayerentity2.addStat(Stats.ANIMALS_BRED);
                    CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity2, this.animal, this.targetMate, baby);
                }

                int i = 6000;
                this.animal.setGrowingAge(6000);
                this.targetMate.setGrowingAge(6000);
                this.animal.resetInLove();
                this.targetMate.resetInLove();
                baby.setGrowingAge(-24000);
                baby.setLocationAndAngles(this.animal.getPosX(), this.animal.getPosY(), this.animal.getPosZ(), 0.0F, 0.0F);
                this.world.addEntity(baby);
                this.world.setEntityState(this.animal, (byte)18);
                if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
                    this.world.addEntity(new ExperienceOrbEntity(this.world, this.animal.getPosX(), this.animal.getPosY(), this.animal.getPosZ(), this.animal.getRNG().nextInt(7) + 1));
                }

            }
        }
    }

    class MoveHelperController extends MovementController {
        public MoveHelperController() {
            super(FeyFoxEntity.this);
        }

        public void tick() {
            if (FeyFoxEntity.this.func_213478_eo()) {
                super.tick();
            }

        }
    }

    class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
        public PanicGoal(double p_i50729_2_) {
            super(FeyFoxEntity.this, p_i50729_2_);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return !FeyFoxEntity.this.isFoxAggroed() && super.shouldExecute();
        }
    }

    public class PounceGoal extends net.minecraft.entity.ai.goal.JumpGoal {
        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (!FeyFoxEntity.this.func_213490_ee()) {
                return false;
            } else {
                LivingEntity livingentity = FeyFoxEntity.this.getAttackTarget();
                if (livingentity != null && livingentity.isAlive()) {
                    if (livingentity.getAdjustedHorizontalFacing() != livingentity.getHorizontalFacing()) {
                        return false;
                    } else {
                        boolean flag = FeyFoxEntity.func_213481_a(FeyFoxEntity.this, livingentity);
                        if (!flag) {
                            FeyFoxEntity.this.getNavigator().getPathToEntity(livingentity, 0);
                            FeyFoxEntity.this.setCrouching(false);
                            FeyFoxEntity.this.func_213502_u(false);
                        }

                        return flag;
                    }
                } else {
                    return false;
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            LivingEntity livingentity = FeyFoxEntity.this.getAttackTarget();
            if (livingentity != null && livingentity.isAlive()) {
                double d0 = FeyFoxEntity.this.getMotion().y;
                return (!(d0 * d0 < (double)0.05F) || !(Math.abs(FeyFoxEntity.this.rotationPitch) < 15.0F) || !FeyFoxEntity.this.onGround) && !FeyFoxEntity.this.isStuck();
            } else {
                return false;
            }
        }

        public boolean isPreemptible() {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            FeyFoxEntity.this.setJumping(true);
            FeyFoxEntity.this.func_213461_s(true);
            FeyFoxEntity.this.func_213502_u(false);
            LivingEntity livingentity = FeyFoxEntity.this.getAttackTarget();
            FeyFoxEntity.this.getLookController().setLookPositionWithEntity(livingentity, 60.0F, 30.0F);
            Vec3d vec3d = (new Vec3d(livingentity.getPosX() - FeyFoxEntity.this.getPosX(), livingentity.getPosY() - FeyFoxEntity.this.getPosY(), livingentity.getPosZ() - FeyFoxEntity.this.getPosZ())).normalize();
            FeyFoxEntity.this.setMotion(FeyFoxEntity.this.getMotion().add(vec3d.x * 0.8D, 0.9D, vec3d.z * 0.8D));
            FeyFoxEntity.this.getNavigator().clearPath();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            FeyFoxEntity.this.setCrouching(false);
            FeyFoxEntity.this.crouchAmount = 0.0F;
            FeyFoxEntity.this.crouchAmountO = 0.0F;
            FeyFoxEntity.this.func_213502_u(false);
            FeyFoxEntity.this.func_213461_s(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = FeyFoxEntity.this.getAttackTarget();
            if (livingentity != null) {
                FeyFoxEntity.this.getLookController().setLookPositionWithEntity(livingentity, 60.0F, 30.0F);
            }

            if (!FeyFoxEntity.this.isStuck()) {
                Vec3d vec3d = FeyFoxEntity.this.getMotion();
                if (vec3d.y * vec3d.y < (double)0.03F && FeyFoxEntity.this.rotationPitch != 0.0F) {
                    FeyFoxEntity.this.rotationPitch = MathHelper.rotLerp(FeyFoxEntity.this.rotationPitch, 0.0F, 0.2F);
                } else {
                    double d0 = Math.sqrt(Entity.horizontalMag(vec3d));
                    double d1 = Math.signum(-vec3d.y) * Math.acos(d0 / vec3d.length()) * (double)(180F / (float)Math.PI);
                    FeyFoxEntity.this.rotationPitch = (float)d1;
                }
            }

            if (livingentity != null && FeyFoxEntity.this.getDistance(livingentity) <= 2.0F) {
                FeyFoxEntity.this.attackEntityAsMob(livingentity);
            } else if (FeyFoxEntity.this.rotationPitch > 0.0F && FeyFoxEntity.this.onGround && (float)FeyFoxEntity.this.getMotion().y != 0.0F && FeyFoxEntity.this.world.getBlockState(new BlockPos(FeyFoxEntity.this)).getBlock() == Blocks.SNOW) {
                FeyFoxEntity.this.rotationPitch = 60.0F;
                FeyFoxEntity.this.setAttackTarget((LivingEntity)null);
                FeyFoxEntity.this.setStuck(true);
            }

        }
    }

    class RevengeGoal extends NearestAttackableTargetGoal<LivingEntity> {
        @Nullable
        private LivingEntity field_220786_j;
        private LivingEntity field_220787_k;
        private int field_220788_l;

        public RevengeGoal(Class<LivingEntity> p_i50743_2_, boolean p_i50743_3_, boolean p_i50743_4_, @Nullable Predicate<LivingEntity> p_i50743_5_) {
            super(FeyFoxEntity.this, p_i50743_2_, 10, p_i50743_3_, p_i50743_4_, p_i50743_5_);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (this.targetChance > 0 && this.goalOwner.getRNG().nextInt(this.targetChance) != 0) {
                return false;
            } else {
                for(UUID uuid : FeyFoxEntity.this.getTrustedUUIDs()) {
                    if (uuid != null && FeyFoxEntity.this.world instanceof ServerWorld) {
                        Entity entity = ((ServerWorld)FeyFoxEntity.this.world).getEntityByUuid(uuid);
                        if (entity instanceof LivingEntity) {
                            LivingEntity livingentity = (LivingEntity)entity;
                            this.field_220787_k = livingentity;
                            this.field_220786_j = livingentity.getRevengeTarget();
                            int i = livingentity.getRevengeTimer();
                            return i != this.field_220788_l && this.isSuitableTarget(this.field_220786_j, this.targetEntitySelector);
                        }
                    }
                }

                return false;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            FeyFoxEntity.this.setAttackTarget(this.field_220786_j);
            this.nearestTarget = this.field_220786_j;
            if (this.field_220787_k != null) {
                this.field_220788_l = this.field_220787_k.getRevengeTimer();
            }

            FeyFoxEntity.this.playSound(SoundEvents.ENTITY_FOX_AGGRO, 1.0F, 1.0F);
            FeyFoxEntity.this.setFoxAggroed(true);
            FeyFoxEntity.this.wake();
            super.startExecuting();
        }
    }

    class SitAndLookGoal extends FeyFoxEntity.BaseGoal {
        private double field_220819_c;
        private double field_220820_d;
        private int field_220821_e;
        private int field_220822_f;

        public SitAndLookGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return FeyFoxEntity.this.getRevengeTarget() == null && FeyFoxEntity.this.getRNG().nextFloat() < 0.02F && !FeyFoxEntity.this.isSleeping() && FeyFoxEntity.this.getAttackTarget() == null && FeyFoxEntity.this.getNavigator().noPath() && !this.func_220814_h() && !FeyFoxEntity.this.func_213480_dY() && !FeyFoxEntity.this.isCrouching();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.field_220822_f > 0;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.func_220817_j();
            this.field_220822_f = 2 + FeyFoxEntity.this.getRNG().nextInt(3);
            FeyFoxEntity.this.setSitting(true);
            FeyFoxEntity.this.getNavigator().clearPath();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            FeyFoxEntity.this.setSitting(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            --this.field_220821_e;
            if (this.field_220821_e <= 0) {
                --this.field_220822_f;
                this.func_220817_j();
            }

            FeyFoxEntity.this.getLookController().setLookPosition(FeyFoxEntity.this.getPosX() + this.field_220819_c, FeyFoxEntity.this.getPosYEye(), FeyFoxEntity.this.getPosZ() + this.field_220820_d, (float)FeyFoxEntity.this.getHorizontalFaceSpeed(), (float)FeyFoxEntity.this.getVerticalFaceSpeed());
        }

        private void func_220817_j() {
            double d0 = (Math.PI * 2D) * FeyFoxEntity.this.getRNG().nextDouble();
            this.field_220819_c = Math.cos(d0);
            this.field_220820_d = Math.sin(d0);
            this.field_220821_e = 80 + FeyFoxEntity.this.getRNG().nextInt(20);
        }
    }

    class SleepGoal extends FeyFoxEntity.BaseGoal {
        private int field_220825_c = FeyFoxEntity.this.rand.nextInt(140);

        public SleepGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (FeyFoxEntity.this.moveStrafing == 0.0F && FeyFoxEntity.this.moveVertical == 0.0F && FeyFoxEntity.this.moveForward == 0.0F) {
                return this.func_220823_j() || FeyFoxEntity.this.isSleeping();
            } else {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.func_220823_j();
        }

        private boolean func_220823_j() {
            if (this.field_220825_c > 0) {
                --this.field_220825_c;
                return false;
            } else {
                return FeyFoxEntity.this.world.isDaytime() && this.func_220813_g() && !this.func_220814_h();
            }
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.field_220825_c = FeyFoxEntity.this.rand.nextInt(140);
            FeyFoxEntity.this.func_213499_en();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            FeyFoxEntity.this.setSitting(false);
            FeyFoxEntity.this.setCrouching(false);
            FeyFoxEntity.this.func_213502_u(false);
            FeyFoxEntity.this.setJumping(false);
            FeyFoxEntity.this.setSleeping(true);
            FeyFoxEntity.this.getNavigator().clearPath();
            FeyFoxEntity.this.getMoveHelper().setMoveTo(FeyFoxEntity.this.getPosX(), FeyFoxEntity.this.getPosY(), FeyFoxEntity.this.getPosZ(), 0.0D);
        }
    }

    class StrollGoal extends MoveThroughVillageAtNightGoal {
        public StrollGoal(int p_i50726_2_, int p_i50726_3_) {
            super(FeyFoxEntity.this, p_i50726_3_);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            FeyFoxEntity.this.func_213499_en();
            super.startExecuting();
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return super.shouldExecute() && this.func_220759_g();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && this.func_220759_g();
        }

        private boolean func_220759_g() {
            return !FeyFoxEntity.this.isSleeping() && !FeyFoxEntity.this.isSitting() && !FeyFoxEntity.this.isFoxAggroed() && FeyFoxEntity.this.getAttackTarget() == null;
        }
    }

    class SwimGoal extends net.minecraft.entity.ai.goal.SwimGoal {
        public SwimGoal() {
            super(FeyFoxEntity.this);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            super.startExecuting();
            FeyFoxEntity.this.func_213499_en();
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return FeyFoxEntity.this.isInWater() && FeyFoxEntity.this.getSubmergedHeight() > 0.25D || FeyFoxEntity.this.isInLava();
        }
    }

    class WatchGoal extends LookAtGoal {
        public WatchGoal(MobEntity p_i50733_2_, Class<? extends LivingEntity> p_i50733_3_, float p_i50733_4_) {
            super(p_i50733_2_, p_i50733_3_, p_i50733_4_);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return super.shouldExecute() && !FeyFoxEntity.this.isStuck() && !FeyFoxEntity.this.func_213467_eg();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && !FeyFoxEntity.this.isStuck() && !FeyFoxEntity.this.func_213467_eg();
        }
    }
}