package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

public class OilPortalBlock extends Block {
	   
	   public OilPortalBlock(Block.Properties properties) {
	      super(properties);
	   }
	   
	   @Override
	   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		   if (!worldIn.isRemote && entityIn instanceof PlayerEntity) {
		   
		    DimensionType feyDimType = DimensionType.byName(ProgressiveTechnicalities.OIL_DIM_TYPE);
			boolean isInFeywild = worldIn.getDimension().getType() == feyDimType;
			
			DimensionType destination = null;
			double movementFactor;
			if (isInFeywild) {
				destination = DimensionType.OVERWORLD;
				movementFactor = 0.01;
			} else {
				destination = feyDimType;
				movementFactor = 100;
			}
			MinecraftServer minecraftserver = entityIn.getServer();
			ServerWorld serverworld = minecraftserver.getWorld(destination);

			double x = entityIn.getPosX() * movementFactor;
			double z = entityIn.getPosZ() * movementFactor;
			((ServerPlayerEntity)entityIn).teleport(serverworld, x, 0, z, entityIn.getYaw(0), entityIn.getPitch(0));
		
			// make you spawn on a portal in the lowest air space
	         for (int i=0; i<256; i++) {
	        	 if (validPortalSpawnLocation(entityIn.world, entityIn.getPosition())) {
	        		 genPortal(entityIn.world, entityIn.getPosition());
	        		 break;
            	 } else {
            		 entityIn.teleportKeepLoaded(x, entityIn.getPosY() + 1, z);
            	 }
             }

		   }
	   }
	   
	private boolean validPortalSpawnLocation(World world, BlockPos pos) {
		boolean valid = ( world.getBlockState(pos.down()).isAir(null, null) || world.getBlockState(pos.down()).getBlock() instanceof OilPortalBlock || world.getBlockState(pos.down()).getBlock() == Blocks.MYCELIUM);
		valid = valid && ( world.getBlockState(pos.down().north()).isAir(null, null) ||  world.getBlockState(pos.down().north()).getBlock() == Blocks.OBSIDIAN);
		valid = valid && ( world.getBlockState(pos.down().south()).isAir(null, null) ||  world.getBlockState(pos.down().south()).getBlock() == Blocks.OBSIDIAN);
		valid = valid && ( world.getBlockState(pos.down().east()).isAir(null, null) ||  world.getBlockState(pos.down().east()).getBlock() == Blocks.OBSIDIAN);
		valid = valid && ( world.getBlockState(pos.down().west()).isAir(null, null) ||  world.getBlockState(pos.down().west()).getBlock() == Blocks.OBSIDIAN);
		valid = valid && ( world.getBlockState(pos.north()).isAir(null, null) ||  world.getBlockState(pos.north()).getBlock() == Blocks.TORCH);
		valid = valid && ( world.getBlockState(pos.south()).isAir(null, null) ||  world.getBlockState(pos.south()).getBlock() == Blocks.TORCH);
		valid = valid && ( world.getBlockState(pos.east()).isAir(null, null) ||  world.getBlockState(pos.east()).getBlock() == Blocks.TORCH);
		valid = valid && ( world.getBlockState(pos.west()).isAir(null, null) ||  world.getBlockState(pos.west()).getBlock() == Blocks.TORCH);
				
		return valid;
	}
	
	private void genPortal(World world, BlockPos pos) {
		world.setBlockState(pos.down(), this.getDefaultState());
		world.setBlockState(pos.down().north(), Blocks.OBSIDIAN.getDefaultState());
		world.setBlockState(pos.down().south(), Blocks.OBSIDIAN.getDefaultState());
		world.setBlockState(pos.down().east(), Blocks.OBSIDIAN.getDefaultState());
		world.setBlockState(pos.down().west(), Blocks.OBSIDIAN.getDefaultState());
		world.setBlockState(pos.north(), Blocks.TORCH.getDefaultState());
		world.setBlockState(pos.south(), Blocks.TORCH.getDefaultState());
		world.setBlockState(pos.east(), Blocks.TORCH.getDefaultState());
		world.setBlockState(pos.west(), Blocks.TORCH.getDefaultState());
	}
}
	   
	
