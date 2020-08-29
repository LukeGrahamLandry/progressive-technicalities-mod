package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

import java.util.List;

public class DragonBreathBall implements ProjectileHitAction{
    public String getName(){
        return "ball of dragon breath";
    }

    public void onHit(RayTraceResult result, SimpleProjectile projectile){
        if (result.getType() != RayTraceResult.Type.ENTITY || !((EntityRayTraceResult)result).getEntity().isEntityEqual(projectile.getThrower())) {
            List<LivingEntity> list = projectile.world.getEntitiesWithinAABB(LivingEntity.class, projectile.getBoundingBox().grow(4.0D, 2.0D, 4.0D));
            AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(projectile.world, projectile.getPosX(), projectile.getPosY(), projectile.getPosZ());
            areaeffectcloudentity.setOwner(projectile.getThrower());
            areaeffectcloudentity.setParticleData(ParticleTypes.DRAGON_BREATH);
            areaeffectcloudentity.setRadius(3.0F);
            areaeffectcloudentity.setDuration(600);
            areaeffectcloudentity.setRadiusPerTick((7.0F - areaeffectcloudentity.getRadius()) / (float)areaeffectcloudentity.getDuration());
            areaeffectcloudentity.addEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 1));
            if (!list.isEmpty()) {
                for(LivingEntity livingentity : list) {
                    double d0 = projectile.getDistanceSq(livingentity);
                    if (d0 < 16.0D) {
                        areaeffectcloudentity.setPosition(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
                        break;
                    }
                }
            }

            projectile.world.playEvent(2006, new BlockPos(projectile), 0);
            projectile.world.addEntity(areaeffectcloudentity);

        }


    }
}
