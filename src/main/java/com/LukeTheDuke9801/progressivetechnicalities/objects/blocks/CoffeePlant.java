package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CocoaBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class CoffeePlant extends CocoaBlock {
    public CoffeePlant(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.offset(state.get(HORIZONTAL_FACING))).getBlock();
        return block == BlockInit.FEY_LOG.get();
    }
}
