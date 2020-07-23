package com.LukeTheDuke9801.progressivetechnicalities.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class WitherArrowEntity extends ArrowEntity{
	public WitherArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn) {
	      super(type, worldIn);
    }

    public WitherArrowEntity(World worldIn, double x, double y, double z) {
       super(worldIn, x, y, z);
    }

    public WitherArrowEntity(World worldIn, LivingEntity shooter) {
       super(worldIn, shooter);
    }
    
    protected void onEntityHit(EntityRayTraceResult ray) {
    	super.onEntityHit(ray);
    	LivingEntity entity = (LivingEntity) ray.getEntity();
    	entity.addPotionEffect(new EffectInstance(Effects.WITHER, 100, 2));
    }
}
