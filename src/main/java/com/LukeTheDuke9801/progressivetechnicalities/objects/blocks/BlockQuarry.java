package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockQuarry extends Block{
	private static final VoxelShape SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 1, 12);
	
	public BlockQuarry(Properties propertises) {
		super(propertises);
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		ModTileEntityTypes.QUARRY.get.create();
		return super.createTileEntity(state, world);
	}

}
