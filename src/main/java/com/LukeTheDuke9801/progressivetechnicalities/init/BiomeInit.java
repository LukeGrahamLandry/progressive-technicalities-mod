package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.*;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Biome> OIL_SPIKES = BIOMES.register("blood_biome",
			() -> new OilBiome());
	
	public static final RegistryObject<Biome> FEY_PLAINS = BIOMES.register("fey_plains",
			() -> new FeyPlainsBiome());
	public static final RegistryObject<Biome> MUSHROOM_FOREST = BIOMES.register("mushroom_forest",
			() -> new MushroomForestBiome());
	public static final RegistryObject<Biome> FRUIT_FOREST = BIOMES.register("fruit_forest",
			() -> new FruitForestBiome());
	
	public static final RegistryObject<Biome> SAND_DUNES = BIOMES.register("sand_dunes",
			() -> new ArrakisBiome());
	
	public static final RegistryObject<Biome> SPACE_JUNGLE = BIOMES.register("jungle_hills",
			() -> new PandoraBiome());
	
	public static final RegistryObject<Biome> LUNA_PLAINS = BIOMES.register("luna_plains",
			() -> new LunaBiome());
	
	public static void registerBiomes() {
		registerBiome(OIL_SPIKES.get(), Type.PLAINS);
		
		registerBiome(FEY_PLAINS.get(), Type.PLAINS);
		registerBiome(MUSHROOM_FOREST.get(), Type.FOREST);
		registerBiome(FRUIT_FOREST.get(), Type.HILLS);
		
		registerBiome(SAND_DUNES.get(), Type.HILLS);
		registerBiome(SPACE_JUNGLE.get(), Type.HILLS);
		registerBiome(LUNA_PLAINS.get(), Type.PLAINS);
	}
	
	private static void registerBiome(Biome biome, Type... types) {
		// the line below will make it spawn in the overworld
		// BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
	
	
}
