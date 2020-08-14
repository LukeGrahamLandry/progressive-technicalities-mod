package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class MultiArrowEntity extends ArrowEntity{
	public MultiArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn) {
	      super(type, worldIn);
    }

    public MultiArrowEntity(World worldIn, double x, double y, double z) {
       super(worldIn, x, y, z);
    }

    public MultiArrowEntity(World worldIn, LivingEntity shooter) {
       super(worldIn, shooter);
    }
    
    protected void onEntityHit(EntityRayTraceResult ray) {
        LivingEntity entity = (LivingEntity) ray.getEntity();
        entity.hurtResistantTime = 0;  // let something be hit by multiple arrows
    	super.onEntityHit(ray);
    }
}
