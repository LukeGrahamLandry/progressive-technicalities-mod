package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff;

import net.minecraft.util.math.RayTraceResult;

public interface ProjectileHitAction {
    void onHit(RayTraceResult result, SimpleProjectile projectile);

    String getName();
}