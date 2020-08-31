package com.LukeTheDuke9801.progressivetechnicalities.entities;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.*;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WizardEntity extends EvokerEntity {
    public AttackType attackType;
    public WizardEntity(EntityType<? extends EvokerEntity> type, World worldIn) {
        super(type, worldIn);
        super.func_213644_t(false); // canJoinRaid = false
    }

    @Override
    public void livingTick() {
        super.livingTick();
        
        // doing this in the constructer only sets it on the client
        if (!this.world.isRemote && this.attackType == null){
            List<AttackType> types = new ArrayList<AttackType>(AttackType.TYPES_BY_NAME.values());
            int i = rand.nextInt(types.size());
            this.attackType = types.get(i);
            this.setHeldItem(Hand.MAIN_HAND, this.attackType.getHeldItem());
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new WizardEntity.CastingSpellGoal());
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6D, 1.0D));
        this.goalSelector.addGoal(3, new WizardEntity.AttackSpellGoal());
        this.goalSelector.addGoal(4, new WizardEntity.RemoveEffectsSpellGoal());
        this.goalSelector.addGoal(2, new WizardEntity.AntiWitherSpellGoal());
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 32.0F, 1.0F));
        this.goalSelector.addGoal(9, new LookAtGoal(this, AbstractVillagerEntity.class, 32.0F, 1.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, WitherEntity.class, 32.0F, 1.0F));
        this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, WitherEntity.class, true)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true)).setUnseenMemoryTicks(300));
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getTrueSource() instanceof WizardEntity)  return false;  // immune to damage from wizards
        return super.attackEntityFrom(source, amount);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public AbstractIllagerEntity.ArmPose getArmPose() {
        if (this.isSpellcasting()) {
            return AbstractIllagerEntity.ArmPose.SPELLCASTING;
        } else {
            return ArmPose.ATTACKING;
        }
    }

    class AttackSpellGoal extends SpellcastingIllagerEntity.UseSpellGoal {
        private AttackSpellGoal() {
        }

        // ticks it takes to cast
        protected int getCastingTime() {
            return 10;
        }

        // ticks between casts
        protected int getCastingInterval() {
            return WizardEntity.this.attackType.getCooldown();
        }

        protected void castSpell() {
            WizardEntity wizard = WizardEntity.this;
            LivingEntity target = wizard.getAttackTarget();

            ProjectileHitAction hitaction = wizard.attackType.getHitAction();
            SimpleProjectile projectile = new SimpleProjectile(world, wizard, hitaction, 5);
            projectile.shootTowardEntity(target);
            world.addEntity(projectile);
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_ATTACK;
        }

        // what type of particles
        protected SpellcastingIllagerEntity.SpellType getSpellType() {
            return SpellcastingIllagerEntity.SpellType.FANGS;
        }
    }

    class AntiWitherSpellGoal extends SpellcastingIllagerEntity.UseSpellGoal {
        private AntiWitherSpellGoal() {
        }

        @Override
        public boolean shouldExecute() {
            WizardEntity wizard = WizardEntity.this;
            LivingEntity target = wizard.getAttackTarget();

            if (target instanceof WitherEntity && target.isAlive()) {
                if (wizard.isSpellcasting()) {
                    return false;
                } else {
                    return wizard.ticksExisted >= this.spellCooldown;
                }
            } else {
                return false;
            }
        }

        // ticks it takes to cast
        protected int getCastingTime() {
            return 20;
        }

        // ticks between casts
        protected int getCastingInterval() {
            return 60;
        }

        protected void castSpell() {
            WizardEntity wizard = WizardEntity.this;
            LivingEntity target = wizard.getAttackTarget();

            wizard.heal(10);

            SimpleProjectile projectile = new SimpleProjectile(world, wizard, new AntiWitherBall(), 0);
            projectile.shootTowardEntity(target);
            projectile.addVelocity(projectile.getMotion().x, projectile.getMotion().y, projectile.getMotion().z);  // make it faster
            world.addEntity(projectile);
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_ATTACK;
        }

        // what type of particles
        protected SpellcastingIllagerEntity.SpellType getSpellType() {
            return SpellcastingIllagerEntity.SpellType.FANGS;
        }
    }

    class RemoveEffectsSpellGoal extends SpellcastingIllagerEntity.UseSpellGoal {
        private RemoveEffectsSpellGoal() {
        }

        @Override
        public boolean shouldExecute() {
            WizardEntity wizard = WizardEntity.this;

            return (wizard.getFireTimer() > 0 || wizard.getActivePotionEffects().size() > 0) &&
                    wizard.ticksExisted >= this.spellCooldown;
        }

        public boolean shouldContinueExecuting() {
            return this.spellWarmup > 0;
        }

        // ticks it takes to cast
        protected int getCastingTime() {
            return 20;
        }

        // ticks between casts
        protected int getCastingInterval() {
            return 60;
        }

        protected void castSpell() {
            WizardEntity wizard = WizardEntity.this;
            wizard.clearActivePotions();
            wizard.extinguish();
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_WOLOLO;
        }

        // what type of particles
        protected SpellcastingIllagerEntity.SpellType getSpellType() {
            return SpellcastingIllagerEntity.SpellType.WOLOLO;
        }
    }

    class CastingSpellGoal extends SpellcastingIllagerEntity.CastingASpellGoal {
        private CastingSpellGoal() {
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (WizardEntity.this.getAttackTarget() != null) {
                WizardEntity.this.getLookController().setLookPositionWithEntity(WizardEntity.this.getAttackTarget(), 100.0F,100.0F);
            }
        }
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.attackType = AttackType.getByName(compound.getString("wizardType"));
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("wizardType", this.attackType.getName());
    }

    public static enum AttackType {
        FIRE("fire", Fireball::new, 60, ItemInit.FIREBALL_STAFF.get()),
        SLOW("slow", Slowball::new, 1, ItemInit.SLOWBALL_STAFF.get()),
        DRAGON("dragon", DragonBreathBall::new, 100, ItemInit.DRAGONBREATH_STAFF.get()),
        TNT("tnt", TNTBall::new, 100, ItemInit.TNT_STAFF.get());

        private final String name;
        private final Supplier<ProjectileHitAction> hitAction;
        private final int cooldown;
        private final ItemStack held;
        private static final Map<String, AttackType> TYPES_BY_NAME =  Arrays.stream(values()).collect(Collectors.toMap(AttackType::getName, (p_221081_0_) -> {
            return p_221081_0_;
        }));
        private AttackType(String nameIn, Supplier<ProjectileHitAction> hitActionIn, int cooldownIn, Item staffItem){
            this.name = nameIn;
            this.hitAction = hitActionIn;
            this.cooldown = cooldownIn;

            this.held = new ItemStack(staffItem);
        }

        public String getName(){
            return this.name;
        }

        public ProjectileHitAction getHitAction(){
            return this.hitAction.get();
        }

        public int getCooldown(){
            return this.cooldown;
        }

        public ItemStack getHeldItem(){
            return this.held;
        }



        public static AttackType getByName(String name){
            return TYPES_BY_NAME.get(name);
        }
    }

    @Nullable
    @Override  // make it not be part of raids
    public Raid getRaid() {
        return null;
    }
}
