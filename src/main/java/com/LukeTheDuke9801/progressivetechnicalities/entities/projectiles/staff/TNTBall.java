package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;

public class TNTBall implements ProjectileHitAction{
    public String getName(){
        return "ball that spawns a lit tnt where it lands";
    }

    public void onHit(RayTraceResult result, SimpleProjectile projectile) {
        TNTEntity tnt = new TNTEntity(projectile.world, projectile.getPosX(), projectile.getPosY(), projectile.getPosZ(), projectile.getThrower());

        int fuse = 80 - (projectile.force * 10); // force 0 -> 4 seconds, force V -> 1.5 seconds
        tnt.setFuse(fuse);

        projectile.world.addEntity(tnt);
    }
}
