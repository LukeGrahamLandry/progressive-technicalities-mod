package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.world.dimension.ArrakisModDimension;
import com.LukeTheDuke9801.progressivetechnicalities.world.dimension.FeyModDimension;
import com.LukeTheDuke9801.progressivetechnicalities.world.dimension.LunaModDimension;
import com.LukeTheDuke9801.progressivetechnicalities.world.dimension.OilModDimension;
import com.LukeTheDuke9801.progressivetechnicalities.world.dimension.PandoraModDimension;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {
	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<ModDimension> OIL_DIM = MOD_DIMENSIONS.register("oil_dim",
			() -> new OilModDimension());
	
	public static final RegistryObject<ModDimension> FEYWILD_DIM = MOD_DIMENSIONS.register("feywild_dim",
			() -> new FeyModDimension());
	
	// Planets
	
	public static final RegistryObject<ModDimension> ARRAKIS_DIM = MOD_DIMENSIONS.register("arrakis_dim",
			() -> new ArrakisModDimension());
	
	public static final RegistryObject<ModDimension> PANDORA_DIM = MOD_DIMENSIONS.register("pandora_dim",
			() -> new PandoraModDimension());
	
	public static final RegistryObject<ModDimension> LUNA_DIM = MOD_DIMENSIONS.register("luna_dim",
			() -> new LunaModDimension());
}
