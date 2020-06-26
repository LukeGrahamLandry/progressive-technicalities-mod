package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockMultiTNT extends Block{
	
	public BlockMultiTNT(Properties propertises) {
		super(propertises);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.MULTI_TNT.get().create();
	}

}
