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

public class MelangeOreGen {
	public static final OreFeatureConfig.FillerBlockType SANDSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("SANDSTONE", "sand_stone", new BlockMatcher(Blocks.SANDSTONE));
	
	public static void generateOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == BiomeInit.SAND_DUNES.get()) {
				//              commonness, min level, max offset, max level
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 10, 0, 60));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig                               // max vain size
				(SANDSTONE_FILLER, BlockInit.MELANGE_DEPOSIT.get().getDefaultState(), 20))
				.withPlacement(customConfig)); 
				
				
				ConfiguredPlacement customConfig2 = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 2, 0, 70));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig                               // max vain size
				(SANDSTONE_FILLER, Blocks.SAND.getDefaultState(), 100))
				.withPlacement(customConfig2)); 
			} 
			
		}
	}
	
	
}
