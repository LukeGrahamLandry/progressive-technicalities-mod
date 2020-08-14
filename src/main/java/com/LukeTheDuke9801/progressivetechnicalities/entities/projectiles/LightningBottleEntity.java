package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LightningBottleEntity extends PotionEntity {
   public LightningBottleEntity(EntityType<? extends LightningBottleEntity> p_i50159_1_, World p_i50159_2_) {
      super(p_i50159_1_, p_i50159_2_);
   }

   public LightningBottleEntity(World worldIn, LivingEntity throwerIn) {
     super(worldIn, throwerIn);
   }

   /**
    * Called when this EntityThrowable hits a block or entity.
    */
   protected void onImpact(RayTraceResult result) {
      if (!world.isRemote()) {
         ServerWorld serverWorld = (ServerWorld)world;
         LightningBoltEntity lightning = new LightningBoltEntity(world, this.getPosX(), this.getPosY(), this.getPosZ(), false);
         serverWorld.addLightningBolt(lightning);
         this.remove();
      }
   }
}