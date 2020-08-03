package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.entities.special.SpawnableSpecialMob;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SingleMobSpawner extends AirBlock {
    private final SpawnableSpecialMob spawnInterface;

    public SingleMobSpawner(SpawnableSpecialMob spawnInterfaceIn, Block.Properties properties) {
        super(properties);
        this.spawnInterface = spawnInterfaceIn;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        this.spawnInterface.create(worldIn, pos);
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
    }

    @Deprecated
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        this.spawnInterface.create(worldIn, pos);
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
    }

    @Override
    public int tickRate(IWorldReader worldIn) {
        return 1;
    }
}
	   
	
