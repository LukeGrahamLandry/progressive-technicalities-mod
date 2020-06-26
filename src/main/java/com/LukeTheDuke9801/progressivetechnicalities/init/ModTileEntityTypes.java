package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
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
} 