package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;

public class Slowball implements ProjectileHitAction{
    public String getName(){
        return "snow ball";
    }

    public void onHit(RayTraceResult result, SimpleProjectile projectile){
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();

            float damage = entity instanceof BlazeEntity ? 3 : 0;
            entity.attackEntityFrom(DamageSource.causeThrownDamage(projectile, projectile.getThrower()), damage);

            if (entity instanceof LivingEntity){
                ((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 0));
            }
        }
    }
}
