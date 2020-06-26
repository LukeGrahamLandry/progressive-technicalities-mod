package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockQuarry extends Block{
	
	public BlockQuarry(Properties propertises) {
		super(propertises);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.QUARRY.get().create();
	}

}
