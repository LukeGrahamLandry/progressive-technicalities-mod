package com.LukeTheDuke9801.progressivetechnicalities.world.gen;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class FireGemOreGen {
	public static void generateOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == Biomes.NETHER) {
				//              commonness, min level, max offset, max level
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 2, 0, 256));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig                             // max vain size
				(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockInit.FIRE_GEM_ORE.get().getDefaultState(), 8))
				.withPlacement(customConfig));
			}
		}
	}
}
