package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.DimensionHelper;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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

public class FeyPortalBlock extends Block {
	   
	public FeyPortalBlock(Block.Properties properties) {
	      super(properties);
	   }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Fall onto block to go to the feywild dimension"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
	   if (!worldIn.isRemote && entityIn instanceof PlayerEntity) {
		boolean isInFeywild = worldIn.getDimension().getType() == DimensionHelper.FEYWILD;
		if (isInFeywild) {
			DimensionHelper.changeDimension((PlayerEntity) entityIn, DimensionType.OVERWORLD);
		} else {
			DimensionHelper.changeDimension((PlayerEntity) entityIn, DimensionHelper.FEYWILD);
		}

		entityIn.setPosition(entityIn.getPosX(), 63, entityIn.getPosZ());

		// make you spawn on a new portal in the lowest air space above sea level
		 for (int i=0; i<128; i++) {
			 if (validSpawnLocation(entityIn.world, entityIn.getPosition())) {
				 break;
			 } else {
				 entityIn.teleportKeepLoaded(entityIn.getPosX(), entityIn.getPosY() + 1, entityIn.getPosZ());
			 }
		 }
	   }
	}

	private boolean validSpawnLocation(World world, BlockPos pos) {
		return !world.getBlockState(pos).isSolid() &&
				!world.getBlockState(pos.up()).isSolid() &&
				world.getBlockState(pos.down()).isSolid();
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.FEYWILD_PORTAL.get().create();
	}
}
	   
	
