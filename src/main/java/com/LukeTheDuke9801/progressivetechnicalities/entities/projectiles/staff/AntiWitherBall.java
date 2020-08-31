package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;

import java.util.List;

public class AntiWitherBall implements ProjectileHitAction{
    public String getName(){
        return "anti-wither ball";
    }

    // force increases damage to blazes and slow duration
    public void onHit(RayTraceResult result, SimpleProjectile projectile){
        LivingEntity wizard = projectile.getThrower();
        List<Entity> entities = projectile.world.getEntitiesWithinAABBExcludingEntity(wizard, getAABB(projectile));
        for (Entity e : entities) {
            if (e instanceof WitherEntity) {
                e.attackEntityFrom(DamageSource.MAGIC, 50.0F);
            }
        }

        projectile.world.createExplosion(projectile.getThrower(), projectile.getPosX(), projectile.getPosY(), projectile.getPosZ(), 3, false, Explosion.Mode.DESTROY);
    }

    private AxisAlignedBB getAABB(Entity projectile) {
        int range = 3;
        double x = projectile.getPosX();
        double y = projectile.getPosY();
        double z = projectile.getPosZ();
        return new AxisAlignedBB(x-range,y-range,z-range,x+range,y+range,z+range);
    }
}
