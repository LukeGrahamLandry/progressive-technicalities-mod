package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface RitualCatalyst {
    void doRitual(World world, BlockPos pos, PlayerEntity player);
}
