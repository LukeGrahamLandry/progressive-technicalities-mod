package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LightningRod extends Block{
	private static final VoxelShape SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 1, 12);
	
	public LightningRod(Properties propertises) {
		super(propertises);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		// for directional blocks: https://www.youtube.com/watch?v=taUfaVwDiyI&list=PLaevjqy3XufYmltqo0eQusnkKVN7MpTUe&index=15
		return SHAPE;
	}
	
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote()) {
			ServerWorld serverWorld = (ServerWorld)worldIn;
			LightningBoltEntity lightning = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
			serverWorld.addLightningBolt(lightning);
		}
		return ActionResultType.SUCCESS;
	}

}
