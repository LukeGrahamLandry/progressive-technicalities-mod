package com.LukeTheDuke9801.progressivetechnicalities.util;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

public class DimensionHelper {
    public static final DimensionType OIL = DimensionType.byName(ProgressiveTechnicalities.OIL_DIM_TYPE);
    public static final DimensionType FEYWILD = DimensionType.byName(ProgressiveTechnicalities.FEY_DIM_TYPE);

    public static final DimensionType LUNA = DimensionType.byName(ProgressiveTechnicalities.LUNA_DIM_TYPE);
    public static final DimensionType ARRAKIS = DimensionType.byName(ProgressiveTechnicalities.ARRAKIS_DIM_TYPE);
    public static final DimensionType PANDORA = DimensionType.byName(ProgressiveTechnicalities.PANDORA_DIM_TYPE);

    public static boolean isPlanet(DimensionType dim){
        return dim == LUNA || dim == ARRAKIS || dim == PANDORA;
    }

    public static void changeDimension(Entity entityIn, DimensionType dim) {
        MinecraftServer minecraftserver = entityIn.getServer();
        if (minecraftserver == null) {
            return;
        }
        ServerWorld serverworld = minecraftserver.getWorld(dim);

        ((ServerPlayerEntity)entityIn).teleport(serverworld, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), entityIn.getYaw(0), entityIn.getPitch(0));
    }

}
