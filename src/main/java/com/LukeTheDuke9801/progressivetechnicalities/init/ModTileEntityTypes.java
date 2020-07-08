package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.electric_furnace.ElectricFurnaceTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.oil_generator.OilGeneratorTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.rocket.RocketTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.tileentity.FeywildPortalTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.tileentity.LargeChestTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.tileentity.MultiTNTTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.tileentity.QuarryTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, ProgressiveTechnicalities.MOD_ID);

	public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPES.register("quarry", 
			() -> TileEntityType.Builder.create(QuarryTileEntity::new, BlockInit.QUARRY.get()).build(null));
	
	public static final RegistryObject<TileEntityType<MultiTNTTileEntity>> MULTI_TNT = TILE_ENTITY_TYPES.register("multi_tnt", 
			() -> TileEntityType.Builder.create(MultiTNTTileEntity::new, BlockInit.MULTI_TNT.get()).build(null));
	
	public static final RegistryObject<TileEntityType<LargeChestTileEntity>> LARGE_CHEST = TILE_ENTITY_TYPES.register("large_chest", 
			() -> TileEntityType.Builder.create(LargeChestTileEntity::new, BlockInit.LARGE_CHEST.get()).build(null));

	public static final RegistryObject<TileEntityType<FeywildPortalTileEntity>> FEYWILD_PORTAL = TILE_ENTITY_TYPES.register("feywild_portal", 
			() -> TileEntityType.Builder.create(FeywildPortalTileEntity::new, BlockInit.FEYWILD_PORTAL.get()).build(null));
	
	public static final RegistryObject<TileEntityType<ElectricFurnaceTileEntity>> ELECTRIC_FURNACE = TILE_ENTITY_TYPES.register("electric_furnace", 
			() -> TileEntityType.Builder.create(ElectricFurnaceTileEntity::new, BlockInit.ELECTRIC_FURNACE.get()).build(null));

	public static final RegistryObject<TileEntityType<OilGeneratorTileEntity>> OIL_GENERATOR = TILE_ENTITY_TYPES.register("oil_generator", 
			() -> TileEntityType.Builder.create(OilGeneratorTileEntity::new, BlockInit.OIL_GENERATOR.get()).build(null));

	public static final RegistryObject<TileEntityType<RocketTileEntity>> ROCKET = TILE_ENTITY_TYPES.register("rocket", 
			() -> TileEntityType.Builder.create(RocketTileEntity::new, BlockInit.ROCKET.get()).build(null));

} 