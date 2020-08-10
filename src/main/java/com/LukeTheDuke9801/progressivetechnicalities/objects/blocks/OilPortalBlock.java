package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.DimensionHelper;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class OilPortalBlock extends Block {
	   
	   public OilPortalBlock(Block.Properties properties) {
	      super(properties);
	   }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Jump on block to go to the Oil Dimension"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	   
	   @Override
	   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		   if (!worldIn.isRemote && entityIn instanceof PlayerEntity) {

			DimensionType current = worldIn.getDimension().getType();
			if (current == DimensionHelper.OIL) {
				DimensionHelper.changeDimension((PlayerEntity) entityIn, DimensionType.OVERWORLD);
				entityIn.setPosition(entityIn.getPosX(), 63, entityIn.getPosZ());  // spawn above sea level
			} else if (current == DimensionType.OVERWORLD){
				DimensionHelper.changeDimension((PlayerEntity) entityIn, DimensionHelper.OIL);
			}

			// make you spawn on a portal in the lowest air space
	         for (int i=0; i<256; i++) {
	        	 if (validPortalSpawnLocation(entityIn.world, entityIn.getPosition())) {
	        		 genPortal(entityIn.world, entityIn.getPosition());
	        		 break;
            	 } else {
            		 entityIn.teleportKeepLoaded(entityIn.getPosX(), entityIn.getPosY() + 1, entityIn.getPosZ());
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
	   
	
