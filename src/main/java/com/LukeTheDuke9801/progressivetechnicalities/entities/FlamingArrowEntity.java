package com.LukeTheDuke9801.progressivetechnicalities.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class FlamingArrowEntity extends ArrowEntity{
	public FlamingArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn) {
	      super(type, worldIn);
    }

    public FlamingArrowEntity(World worldIn, double x, double y, double z) {
       super(worldIn, x, y, z);
    }

    public FlamingArrowEntity(World worldIn, LivingEntity shooter) {
       super(worldIn, shooter);
    }
    
    protected void onEntityHit(EntityRayTraceResult ray) {
    	super.onEntityHit(ray);
    	Entity entity = ray.getEntity();
    	entity.setFire(10);
    }
}
