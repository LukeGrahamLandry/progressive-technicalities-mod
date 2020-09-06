package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.server.ServerWorld;

public class LightningBall implements ProjectileHitAction{
    public String getName(){
        return "lightning ball";
    }

    // force increases damage, fire time, and explosion power
    public void onHit(RayTraceResult result, SimpleProjectile projectile){
        ServerWorld serverWorld = (ServerWorld)projectile.world;
        LightningBoltEntity lightning = new LightningBoltEntity(projectile.world, projectile.getPosX(), projectile.getPosY(), projectile.getPosZ(), false);
        serverWorld.addLightningBolt(lightning);
    }
}
