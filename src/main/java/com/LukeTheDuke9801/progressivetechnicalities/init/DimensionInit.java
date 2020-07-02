package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.world.dimension.BloodModDimension;
import com.LukeTheDuke9801.progressivetechnicalities.world.dimension.FeyModDimension;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {
	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<ModDimension> BLOOD_DIM = MOD_DIMENSIONS.register("blood_dim",
			() -> new BloodModDimension());
	
	public static final RegistryObject<ModDimension> FEYWILD_DIM = MOD_DIMENSIONS.register("feywild_dim",
			() -> new FeyModDimension());
}
