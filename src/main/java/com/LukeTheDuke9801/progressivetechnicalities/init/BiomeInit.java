package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.ArrakisBiome;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.FeyBiome;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.OilBiome;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.PandoraBiome;

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
			() -> new FeyBiome());
	
	public static final RegistryObject<Biome> SAND_DUNES = BIOMES.register("sand_dunes",
			() -> new ArrakisBiome());
	
	public static final RegistryObject<Biome> JUNGLE_PLAINS = BIOMES.register("jungle_plains",
			() -> new PandoraBiome());
	
	public static void registerBiomes() {
		registerBiome(OIL_SPIKES.get(), Type.PLAINS, Type.OVERWORLD);
		registerBiome(FEY_PLAINS.get(), Type.PLAINS, Type.OVERWORLD);
		registerBiome(SAND_DUNES.get(), Type.HILLS, Type.OVERWORLD);
		registerBiome(JUNGLE_PLAINS.get(), Type.HILLS, Type.OVERWORLD);
	}
	
	private static void registerBiome(Biome biome, Type... types) {
		// the line below will make it spawn in the overworld
		// BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
	
	
}
