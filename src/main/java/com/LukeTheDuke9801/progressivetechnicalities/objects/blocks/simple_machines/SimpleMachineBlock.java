package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.XPContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SimpleMachineBlock extends Block{
	public SimpleMachineBlock(Block.Properties builder) {
		super(builder);
    }
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.SIMPLE_MACHINE.get().create();
	}
	
	public XPContainer getXPContainer(World world, BlockPos pos) {
		return XPContainer.getXPContainerAtPos(world, pos);
	}
	
	protected boolean expendExperience(World world, BlockPos pos, PlayerEntity player, int cost) {
		boolean usedStoredXP = getXPContainer(world, pos).removeXP(cost);
		if (usedStoredXP) return true;
		
		if (player.experienceTotal < cost) return false;
		player.giveExperiencePoints(-cost);
		return true;
	}
}
