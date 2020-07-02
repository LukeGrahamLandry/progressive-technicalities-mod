package com.LukeTheDuke9801.progressivetechnicalities.world.gen;

import com.LukeTheDuke9801.progressivetechnicalities.init.BiomeInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;

import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class FeysteelOreGen {
	public static final OreFeatureConfig.FillerBlockType FEY_FILLER = OreFeatureConfig.FillerBlockType.create("FEYSTONE", "fey_stone", new BlockMatcher(BlockInit.FEY_STONE.get()));
	
	public static void generateOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == BiomeInit.FEY_PLAINS.get()) {
				//              commonness, min level, max offset, max level
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(50, 4, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig                               // max vain size
				(FEY_FILLER, BlockInit.FEYSTEEL_ORE.get().getDefaultState(), 3))
				.withPlacement(customConfig));
			} 
			
		}
	}
	
	
}
