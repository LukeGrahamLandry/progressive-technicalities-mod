package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.BloodBiome;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.FeyBiome;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Biome> BLOOD_SPIKES = BIOMES.register("blood_biome",
			() -> new BloodBiome(new Biome.Builder().waterColor(16711680).waterFogColor(0).surfaceBuilder(SurfaceBuilder.DEFAULT,
					new SurfaceBuilderConfig(Blocks.OBSIDIAN.getDefaultState(), Blocks.OBSIDIAN.getDefaultState(), BlockInit.CHROMIUM_ORE.get().getDefaultState()))
					.category(Category.PLAINS)
					.precipitation(RainType.SNOW)
					.downfall(0.8f)
					.depth(0.125f)
					.waterColor(0)
					.waterFogColor(0)
					.parent(null)
					.scale(2f)
					.temperature(0f)));
	
	public static final RegistryObject<Biome> FEY_PLAINS = BIOMES.register("fey_plains",
			() -> new FeyBiome(new Biome.Builder().waterColor(16711680).waterFogColor(0).surfaceBuilder(SurfaceBuilder.DEFAULT,
					new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState()))
					.category(Category.PLAINS)
					.precipitation(RainType.NONE)
					.downfall(0.0f)
					.depth(0.125f)
					.parent(null)
					.waterColor(16711935)
					.waterFogColor(16711935)
					.scale(0.05f)
					.temperature(0.8F)));
	
	public static void registerBiomes() {
		registerBiome(BLOOD_SPIKES.get(), Type.PLAINS, Type.OVERWORLD);
		registerBiome(FEY_PLAINS.get(), Type.PLAINS, Type.OVERWORLD);
	}
	
	private static void registerBiome(Biome biome, Type... types) {
		// the line below will make it spawn in the overworld
		// BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
	
	
}
