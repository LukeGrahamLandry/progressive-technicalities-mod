package com.LukeTheDuke9801.progressivetechnicalities.world.gen;

import com.LukeTheDuke9801.progressivetechnicalities.init.BiomeInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class UnobtaniumOreGen {
	public static final OreFeatureConfig.FillerBlockType INFESTEDSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("INFESTEDSTONE", "infested_stone", new BlockMatcher(Blocks.INFESTED_STONE));
	
	public static void generateOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == BiomeInit.JUNGLE_PLAINS.get()) {
				//              commonness, min level, max offset, max level
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig                // max vain size
				(INFESTEDSTONE_FILLER, BlockInit.UNOBTANIUM_ORE.get().getDefaultState(), 6))
				.withPlacement(customConfig));
			} 
			
		}
	}
	
	
}
