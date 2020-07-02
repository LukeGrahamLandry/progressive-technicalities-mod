package com.LukeTheDuke9801.progressivetechnicalities.world.gen;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ChromiumOreGen {
	public static void generateOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			// if (biome == Biomes.PLAINS) {} only generate in certain biomes
			//                                                            commonness, min level, max offset, max level
			ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 20, 0, 60));
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
					Feature.ORE.withConfiguration(new OreFeatureConfig                               // max vain size
							(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.CHROMIUM_ORE.get().getDefaultState(), 17))
					.withPlacement(customConfig));
		}
	}
}
