package com.LukeTheDuke9801.progressivetechnicalities.entities;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.ModVillagerTrades;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class AbstractWanderer extends AbstractVillagerEntity {
    @Nullable
    private BlockPos wanderTarget;
    private BlockPos homePos;

    protected VillagerTrades.ITrade[] sells;
    protected VillagerTrades.ITrade[] buys;


    public AbstractWanderer(EntityType<? extends AbstractWanderer> type, World worldIn) {
        super(type, worldIn);
        this.forceSpawn = true;
        this.enablePersistence();
    }

    @Override
    protected void populateTradeData() {
        MerchantOffers merchantoffers = this.getOffers();
        this.addTrades(merchantoffers, this.sells, 5);  // idk what the 5 is for
        this.addTrades(merchantoffers, this.buys, 5);
    }

    public void setHome(BlockPos pos){
        this.homePos = pos;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, ZombieEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, EvokerEntity.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, VindicatorEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, VexEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PillagerEntity.class, 15.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, IllusionerEntity.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.25D));
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(2, new AbstractWanderer.MoveToGoal(this, 2.0D, 0.35D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.35D, false, Ingredient.fromItems(Items.EMERALD)));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
        this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
    }

    @Nullable
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }

    public boolean func_213705_dZ() {
        return false;
    }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        if (this.isAlive() && !this.hasCustomer() && !this.isChild()) {
            if (hand == Hand.MAIN_HAND) {
                player.addStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return super.processInteract(player, hand);
            } else {
                if (!this.world.isRemote) {
                    this.setCustomer(player);
                    this.openMerchantContainer(player, this.getDisplayName(), 1);
                }

                return true;
            }
        } else {
            return super.processInteract(player, hand);
        }
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.wanderTarget != null) {
            compound.put("WanderTarget", NBTUtil.writeBlockPos(this.wanderTarget));
        }

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);

        if (compound.contains("WanderTarget")) {
            this.wanderTarget = NBTUtil.readBlockPos(compound.getCompound("WanderTarget"));
        }

        this.setGrowingAge(Math.max(0, this.getGrowingAge()));
    }

    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    protected void onVillagerTrade(MerchantOffer offer) {
        if (offer.getDoesRewardExp()) {
            int i = 3 + this.rand.nextInt(4);
            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), i));
        }

    }

    protected SoundEvent getAmbientSound() {
        return this.hasCustomer() ? SoundEvents.ENTITY_WANDERING_TRADER_TRADE : SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WANDERING_TRADER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_DEATH;
    }

    protected SoundEvent getDrinkSound(ItemStack stack) {
        Item item = stack.getItem();
        return item == Items.MILK_BUCKET ? SoundEvents.ENTITY_WANDERING_TRADER_DRINK_MILK : SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION;
    }

    protected SoundEvent getVillagerYesNoSound(boolean getYesSound) {
        return getYesSound ? SoundEvents.ENTITY_WANDERING_TRADER_YES : SoundEvents.ENTITY_WANDERING_TRADER_NO;
    }

    public SoundEvent getYesSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_YES;
    }

    public void setWanderTarget(@Nullable BlockPos pos) {
        this.wanderTarget = pos;
    }

    @Nullable
    private BlockPos getWanderTarget() {
        if (this.homePos != null && this.world.isNightTime()) return this.homePos;

        return this.wanderTarget;
    }

    class MoveToGoal extends Goal {
        final AbstractWanderer traderEntity;
        final double maxDistance;
        final double speed;

        MoveToGoal(AbstractWanderer traderEntityIn, double distanceIn, double speedIn) {
            this.traderEntity = traderEntityIn;
            this.maxDistance = distanceIn;
            this.speed = speedIn;
            this.setMutexFlags(EnumSet.of(Flag.MOVE));
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.traderEntity.setWanderTarget((BlockPos) null);
            AbstractWanderer.this.navigator.clearPath();
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            BlockPos blockpos = this.traderEntity.getWanderTarget();

            if (this.traderEntity.homePos != null && this.traderEntity.world.isNightTime()) return true;
            return blockpos != null && this.isWithinDistance(blockpos, this.maxDistance);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            BlockPos blockpos = this.traderEntity.getWanderTarget();
            if (blockpos != null && AbstractWanderer.this.navigator.noPath()) {
                if (this.isWithinDistance(blockpos, 10.0D)) {
                    Vec3d vec3d = (new Vec3d((double) blockpos.getX() - this.traderEntity.getPosX(), (double) blockpos.getY() - this.traderEntity.getPosY(), (double) blockpos.getZ() - this.traderEntity.getPosZ())).normalize();
                    Vec3d vec3d1 = vec3d.scale(10.0D).add(this.traderEntity.getPosX(), this.traderEntity.getPosY(), this.traderEntity.getPosZ());
                    AbstractWanderer.this.navigator.tryMoveToXYZ(vec3d1.x, vec3d1.y, vec3d1.z, this.speed);
                } else {
                    AbstractWanderer.this.navigator.tryMoveToXYZ((double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(), this.speed);
                }
            }

        }

        private boolean isWithinDistance(BlockPos pos, double distance) {
            return !pos.withinDistance(this.traderEntity.getPositionVec(), distance);
        }
    }
}
