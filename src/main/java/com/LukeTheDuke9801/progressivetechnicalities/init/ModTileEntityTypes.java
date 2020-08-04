package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.coal.CoalGeneratorTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.lava.LavaGeneratorTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.oil.OilGeneratorTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.mob_slayer.MobSlayerTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.mob_spawner.MobSpawnerTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.xp_tank.XPTankTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines.SimpleMachineTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.tileentity.*;

import net.minecraft.block.Blocks;
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
	
	public static final RegistryObject<TileEntityType<OilGeneratorTileEntity>> OIL_GENERATOR = TILE_ENTITY_TYPES.register("oil_generator", 
			() -> TileEntityType.Builder.create(OilGeneratorTileEntity::new, BlockInit.OIL_GENERATOR.get()).build(null));
	
	public static final RegistryObject<TileEntityType<LavaGeneratorTileEntity>> LAVA_GENERATOR = TILE_ENTITY_TYPES.register("lava_generator", 
			() -> TileEntityType.Builder.create(LavaGeneratorTileEntity::new, BlockInit.LAVA_GENERATOR.get()).build(null));
	
	public static final RegistryObject<TileEntityType<CoalGeneratorTileEntity>> COAL_GENERATOR = TILE_ENTITY_TYPES.register("coal_generator", 
			() -> TileEntityType.Builder.create(CoalGeneratorTileEntity::new, BlockInit.COAL_GENERATOR.get()).build(null));
	
	public static final RegistryObject<TileEntityType<MobSlayerTileEntity>> MOB_SLAYER = TILE_ENTITY_TYPES.register("mob_slayer", 
			() -> TileEntityType.Builder.create(MobSlayerTileEntity::new, BlockInit.MOB_SLAYER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<MobSpawnerTileEntity>> MOB_SPAWNER = TILE_ENTITY_TYPES.register("auto_spawner", 
			() -> TileEntityType.Builder.create(MobSpawnerTileEntity::new, BlockInit.MOB_SPAWNER.get()).build(null));
	
	public static final RegistryObject<TileEntityType<XPTankTileEntity>> XP_TANK = TILE_ENTITY_TYPES.register("xp_tank", 
			() -> TileEntityType.Builder.create(XPTankTileEntity::new, BlockInit.XP_TANK.get()).build(null));
	
	public static final RegistryObject<TileEntityType<SimpleMachineTileEntity>> SIMPLE_MACHINE = TILE_ENTITY_TYPES.register("simple_machine", 
			() -> TileEntityType.Builder.create(SimpleMachineTileEntity::new, BlockInit.SIMPLE_MACHINE.get()).build(null));
}