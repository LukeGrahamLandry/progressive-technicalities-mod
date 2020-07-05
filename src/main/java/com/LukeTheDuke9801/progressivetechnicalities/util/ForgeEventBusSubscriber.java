package com.LukeTheDuke9801.progressivetechnicalities.util;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.DimensionInit;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid=ProgressiveTechnicalities.MOD_ID, bus=Bus.FORGE)
public class ForgeEventBusSubscriber {
	
	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if (DimensionType.byName(ProgressiveTechnicalities.OIL_DIM_TYPE) == null) {
			DimensionManager.registerDimension(ProgressiveTechnicalities.OIL_DIM_TYPE, DimensionInit.OIL_DIM.get(), null, false);
		}
		if (DimensionType.byName(ProgressiveTechnicalities.FEY_DIM_TYPE) == null) {
			DimensionManager.registerDimension(ProgressiveTechnicalities.FEY_DIM_TYPE, DimensionInit.FEYWILD_DIM.get(), null, false);
		}
		if (DimensionType.byName(ProgressiveTechnicalities.ARRAKIS_DIM_TYPE) == null) {
			DimensionManager.registerDimension(ProgressiveTechnicalities.ARRAKIS_DIM_TYPE, DimensionInit.ARRAKIS_DIM.get(), null, false);
		}
		ProgressiveTechnicalities.LOGGER.info("Dimensions Added");
	}
}
