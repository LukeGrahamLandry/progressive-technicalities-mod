package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;

public class Fireball implements ProjectileHitAction{
    public String getName(){
        return "fire ball";
    }

    // force increases damage, fire time, and explosion power
    public void onHit(RayTraceResult result, SimpleProjectile projectile){
        float force = projectile.force;

        if (result.getType() == RayTraceResult.Type.ENTITY){
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.IN_FIRE, 3 + force);
            entity.setFire(projectile.force + 1);
        }

        float explosionPower = 1 + (force / 5);
        projectile.world.createExplosion(projectile.getThrower(), projectile.getPosX(), projectile.getPosY(), projectile.getPosZ(), explosionPower, true, Explosion.Mode.NONE);
    }
}
