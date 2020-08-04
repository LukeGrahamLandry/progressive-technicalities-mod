package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.entities.special.SpawnableSpecialMob;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import net.minecraft.block.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SingleMobSpawner extends Block {
    private final SpawnableSpecialMob spawnInterface;

    public SingleMobSpawner(SpawnableSpecialMob spawnInterfaceIn, Block.Properties properties) {
        super(properties);
        this.spawnInterface = spawnInterfaceIn;
    }

    public void spawn(World worldIn, BlockPos pos){
        this.spawnInterface.create(worldIn, pos);
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        spawn(worldIn, pos);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }


}
	   
	
