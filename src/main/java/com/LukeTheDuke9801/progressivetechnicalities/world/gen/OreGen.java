package com.LukeTheDuke9801.progressivetechnicalities.world.gen;

import com.LukeTheDuke9801.progressivetechnicalities.init.BiomeInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGen {
	private static final OreFeatureConfig.FillerBlockType STONE_FILLER = OreFeatureConfig.FillerBlockType.NATURAL_STONE;
	private static final OreFeatureConfig.FillerBlockType NETHERRACK_FILLER = OreFeatureConfig.FillerBlockType.NETHERRACK;
	private static final OreFeatureConfig.FillerBlockType DARKSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("progressivetechnicalities:DARKSTONE", "progressivetechnicalities:dark_stone", new BlockMatcher(BlockInit.DARK_STONE.get()));
	private static final OreFeatureConfig.FillerBlockType FEYSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("progressivetechnicalities:FEYSTONE", "progressivetechnicalities:fey_stone", new BlockMatcher(BlockInit.FEY_STONE.get()));
	private static final OreFeatureConfig.FillerBlockType SANDSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("progressivetechnicalities:SANDSTONE", "progressivetechnicalities:sand_stone", new BlockMatcher(Blocks.SANDSTONE));
	
	
	public static void generateAllOres() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			generate(biome, STONE_FILLER, BlockInit.CHROMIUM_ORE.get(), 20, 40, 60, 17);
			generate(biome, STONE_FILLER, BlockInit.SKY_GEM_ORE.get(), 4, 63, 120, 8);
			generate(biome, STONE_FILLER, BlockInit.EARTH_GEM_ORE.get(), 4, 0, 16, 8);
			
			if (biome.getCategory() == Biome.Category.OCEAN) {
				generate(biome, STONE_FILLER, BlockInit.WATER_GEM_ORE.get(), 4, 0, 16, 8);
			}
			
			if (biome == Biomes.NETHER) {
				generate(biome, NETHERRACK_FILLER, BlockInit.FIRE_GEM_ORE.get(), 4, 0, 256, 8);
			}
			
			if (biome == BiomeInit.OIL_SPIKES.get()) {
				generate(biome, DARKSTONE_FILLER, BlockInit.TITANIUM_ORE.get(), 100, 0, 200, 4);
			}
			
			if (biome == BiomeInit.FEY_PLAINS.get()) {
				generate(biome, FEYSTONE_FILLER, BlockInit.FEYSTEEL_ORE.get(), 50, 0, 70, 4);
			}
			
			if (biome == BiomeInit.SAND_DUNES.get()) {
				generate(biome, SANDSTONE_FILLER, BlockInit.MELANGE_DEPOSIT.get(), 10, 0, 70, 20);
				generate(biome, SANDSTONE_FILLER, Blocks.SAND, 20, 0, 70, 100);
			} 
			
			if (biome == BiomeInit.SPACE_JUNGLE.get()) {
				generate(biome, STONE_FILLER, BlockInit.UNOBTANIUM_ORE.get(), 10, 0, 16, 6);
				generate(biome, STONE_FILLER, Blocks.INFESTED_STONE, 25, 0, 70, 50);
			}
		}
	}
	
	private static void generate(Biome biome, OreFeatureConfig.FillerBlockType filler, Block oreBlock, int commoness, int minLevel, int maxLevel, int size) {
		// CountRangeConfig(commoness, min_level, max_offset, max_level) // getDefaultState(), max_size))
		ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(commoness, minLevel, 0, maxLevel));
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig(filler, oreBlock.getDefaultState(), size))
				.withPlacement(customConfig));
	}
}
