package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.BloodBiome;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Biome> BLOOD_BIOME = BIOMES.register("blood_biome",
			() -> new BloodBiome(new Biome.Builder().waterColor(5505211).surfaceBuilder(SurfaceBuilder.DEFAULT,
					new SurfaceBuilderConfig(Blocks.OBSIDIAN.getDefaultState(), BlockInit.CHROMIUM_ORE.get().getDefaultState(), Blocks.CLAY.getDefaultState()))
					.category(Category.PLAINS)
					.precipitation(RainType.SNOW)
					.downfall(0.8f)
					.depth(0.120f)
					.parent(null)));
}
