package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.rocket;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class RocketBlock extends Block{
	public RocketBlock(Block.Properties builder) {
	      super(builder);
	   }
	
		@Override
		public boolean hasTileEntity(BlockState state) {
			return true;
		}

	   public TileEntity createNewTileEntity(IBlockReader worldIn) {
	      return new RocketTileEntity();
	   }
	   
	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
			if (!worldIn.isRemote()) {
				((RocketTileEntity)worldIn.getTileEntity(pos)).active = true;
			}
			return ActionResultType.SUCCESS;
		}
	   

	   /**
	    * Interface for handling interaction with blocks that impliment AbstractFurnaceBlock. Called in onBlockActivated
	    * inside AbstractFurnaceBlock.
	   
	   protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
	      TileEntity tileentity = worldIn.getTileEntity(pos);
	      if (tileentity instanceof RocketTileEntity) {
	         player.openContainer((INamedContainerProvider)tileentity);
	         player.addStat(Stats.INTERACT_WITH_FURNACE);
	      }

	   }
	    */
}