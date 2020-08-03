package com.LukeTheDuke9801.progressivetechnicalities.entities.special;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface SpawnableSpecialMob {
    public void create(World world, BlockPos pos);
}
