package com.LukeTheDuke9801.progressivetechnicalities.objects.fluids;

import java.util.Random;

import javax.annotation.Nullable;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class OilFluid extends FlowingFluid {
   public Fluid getFlowingFluid() {
      return FluidInit.OIL_FLOWING.get();
   }

   public Fluid getStillFluid() {
      return FluidInit.OIL_FLUID.get();
   }

   public Item getFilledBucket() {
      return FluidInit.OIL_BUCKET.get();
   }
   
   @Override
	protected FluidAttributes createAttributes() {
	   return net.minecraftforge.fluids.FluidAttributes.builder(
               FluidInit.OIL_STILL_RL,
               FluidInit.OIL_FLOWING_RL)
               .luminosity(0).density(3000).viscosity(6000).temperature(0).overlay(FluidInit.OIL_OVERLAY_RL).build(this);
	}
   
   public static void applyFluidPotionEffects(PlayerEntity player) {
	   player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 4));
	   if (player.areEyesInFluid(FluidTags.WATER)) {
		   player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 20, 0));
	   }
   }

   
   public static boolean isInFluid(PlayerEntity player) {
	      AxisAlignedBB axisalignedbb = player.getBoundingBox().shrink(0.001D);
	      int i = MathHelper.floor(axisalignedbb.minX);
	      int j = MathHelper.ceil(axisalignedbb.maxX);
	      int k = MathHelper.floor(axisalignedbb.minY);
	      int l = MathHelper.ceil(axisalignedbb.maxY);
	      int i1 = MathHelper.floor(axisalignedbb.minZ);
	      int j1 = MathHelper.ceil(axisalignedbb.maxZ);
	      if (!player.world.isAreaLoaded(i, k, i1, j, l, j1)) {
	         return false;
	      } else {
	         boolean flag1 = false;

	         try (BlockPos.PooledMutable blockpos$pooledmutable = BlockPos.PooledMutable.retain()) {
	            for(int l1 = i; l1 < j; ++l1) {
	               for(int i2 = k; i2 < l; ++i2) {
	                  for(int j2 = i1; j2 < j1; ++j2) {
	                     blockpos$pooledmutable.setPos(l1, i2, j2);
	                     IFluidState ifluidstate = player.world.getFluidState(blockpos$pooledmutable);
	                     boolean inOil = ifluidstate.getFluid() instanceof OilFluid;
	                     if (inOil) {
	                        double d1 = (double)((float)i2 + ifluidstate.getActualHeight(player.world, blockpos$pooledmutable));
	                        if (d1 >= axisalignedbb.minY) {
	                           flag1 = true;
	                        }
	                     }
	                  }
	               }
	            }
	         }

	         return flag1;
	      }
	   
   }
   
   public static void solidifyNearby(LivingEntity living, World worldIn, BlockPos pos, int level) {
	      if (living.onGround) {
	         BlockState blockstate = FluidInit.OIL_ICE.get().getDefaultState();
	         float f = (float)Math.min(16, 2 + level);
	         BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

	         for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f))) {
	            if (blockpos.withinDistance(living.getPositionVec(), (double)f)) {
	               blockpos$mutable.setPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
	               BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable);
	               if (blockstate1.isAir(worldIn, blockpos$mutable)) {
	                  BlockState blockstate2 = worldIn.getBlockState(blockpos);
	                  boolean isFull = blockstate2.getBlock() == FluidInit.OIL_BLOCK.get() && blockstate2.get(FlowingFluidBlock.LEVEL) == 0; 
	                  boolean isOil = blockstate2.getFluidState().getFluid().isEquivalentTo(FluidInit.OIL_FLUID.get());
	                  if (isOil && isFull && blockstate.isValidPosition(worldIn, blockpos) && worldIn.func_226663_a_(blockstate, blockpos, ISelectionContext.dummy()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(living, new net.minecraftforge.common.util.BlockSnapshot(worldIn, blockpos, blockstate2), net.minecraft.util.Direction.UP)) {
	                     worldIn.setBlockState(blockpos, blockstate);
	                     worldIn.getPendingBlockTicks().scheduleTick(blockpos,FluidInit.OIL_ICE.get(), MathHelper.nextInt(living.getRNG(), 60, 120));
	                  }
	               }
	            }
	         }

	      }
	   }
   
   @OnlyIn(Dist.CLIENT)
   public void animateTick(World worldIn, BlockPos pos, IFluidState state, Random random) {
      if (!state.isSource() && !state.get(FALLING)) {
         if (random.nextInt(64) == 0) {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
         }
      } else if (random.nextInt(10) == 0) {
         worldIn.addParticle(ParticleTypes.UNDERWATER, (double)pos.getX() + (double)random.nextFloat(), (double)pos.getY() + (double)random.nextFloat(), (double)pos.getZ() + (double)random.nextFloat(), 0.0D, 0.0D, 0.0D);
      }

   }

   @Nullable
   @OnlyIn(Dist.CLIENT)
   public IParticleData getDripParticleData() {
      return ParticleTypes.DRIPPING_WATER;
   }

   protected boolean canSourcesMultiply() {
      return false;
   }

   protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
      TileEntity tileentity = state.getBlock().hasTileEntity() ? worldIn.getTileEntity(pos) : null;
      Block.spawnDrops(state, worldIn.getWorld(), pos, tileentity);
   }

   public int getSlopeFindDistance(IWorldReader worldIn) {
      return 4;
   }

   public BlockState getBlockState(IFluidState state) {
      return FluidInit.OIL_BLOCK.get().getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
   }

   public boolean isEquivalentTo(Fluid fluidIn) {
      return fluidIn == getStillFluid() || fluidIn == getFlowingFluid();
   }

   public int getLevelDecreasePerBlock(IWorldReader worldIn) {
      return 1;
   }

   public int getTickRate(IWorldReader p_205569_1_) {
      return 5;
   }

   public boolean canDisplace(IFluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
      return p_215665_5_ == Direction.DOWN && !p_215665_4_.isIn(FluidTags.WATER);
   }

   protected float getExplosionResistance() {
      return 100.0F;
   }

   public static class Flowing extends OilFluid {
      protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
         super.fillStateContainer(builder);
         builder.add(LEVEL_1_8);
      }

      public int getLevel(IFluidState p_207192_1_) {
         return p_207192_1_.get(LEVEL_1_8);
      }

      public boolean isSource(IFluidState state) {
         return false;
      }
   }

   public static class Source extends OilFluid {
      public int getLevel(IFluidState p_207192_1_) {
         return 8;
      }

      public boolean isSource(IFluidState state) {
         return true;
      }
   }
}