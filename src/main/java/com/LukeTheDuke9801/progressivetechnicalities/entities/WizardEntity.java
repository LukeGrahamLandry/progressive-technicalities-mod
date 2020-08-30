package com.LukeTheDuke9801.progressivetechnicalities.entities;

import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.Fireball;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.SimpleProjectile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.EvokerFangsEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class WizardEntity extends EvokerEntity {
    public WizardEntity(EntityType<? extends EvokerEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new WizardEntity.CastingSpellGoal());
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6D, 1.0D));
        this.goalSelector.addGoal(4, new WizardEntity.AttackSpellGoal());
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 10.0F, 1.0F));
        this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true)).setUnseenMemoryTicks(300));
    }

    class AttackSpellGoal extends SpellcastingIllagerEntity.UseSpellGoal {
        private AttackSpellGoal() {
        }

        // ticks it takes to cast
        protected int getCastingTime() {
            return 20;
        }

        // ticks between casts
        protected int getCastingInterval() {
            return 40;
        }

        protected void castSpell() {
            LivingEntity target = WizardEntity.this.getAttackTarget();
            WizardEntity wizard = WizardEntity.this;

            SimpleProjectile projectile = new SimpleProjectile(world, wizard, new Fireball(), 5);
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
}
