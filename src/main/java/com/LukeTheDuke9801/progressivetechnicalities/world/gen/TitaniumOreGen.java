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

public class TitaniumOreGen {
	public static final OreFeatureConfig.FillerBlockType DARKSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("DARKSTONE", "dark_stone", new BlockMatcher(BlockInit.DARK_STONE.get()));
	
	public static void generateOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == BiomeInit.BLOOD_SPIKES.get()) {
				//              commonness, min level, max offset, max level
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(50, 2, 0, 200));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig                               // max vain size
				(DARKSTONE_FILLER, BlockInit.TITANIUM_ORE.get().getDefaultState(), 3))
				.withPlacement(customConfig));
			} 
			
		}
	}
	
	
}
