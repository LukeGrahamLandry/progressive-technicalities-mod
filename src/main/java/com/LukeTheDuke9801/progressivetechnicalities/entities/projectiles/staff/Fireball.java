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

    public void onHit(RayTraceResult result, SimpleProjectile projectile){
        if (result.getType() == RayTraceResult.Type.ENTITY){
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.IN_FIRE, 6.0F);
            entity.setFire(5);
        }

        float explosionPower = 1.0F;
        projectile.world.createExplosion((Entity)null, projectile.getPosX(), projectile.getPosY(), projectile.getPosZ(), explosionPower, true, Explosion.Mode.NONE);
    }
}
