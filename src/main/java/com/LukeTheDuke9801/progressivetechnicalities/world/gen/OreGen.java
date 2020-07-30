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
	public static final OreFeatureConfig.FillerBlockType STONE_FILLER = OreFeatureConfig.FillerBlockType.NATURAL_STONE;
	public static final OreFeatureConfig.FillerBlockType NETHERRACK_FILLER = OreFeatureConfig.FillerBlockType.NETHERRACK;
	public static final OreFeatureConfig.FillerBlockType DARKSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("progressivetechnicalities:DARKSTONE", "progressivetechnicalities:dark_stone", new BlockMatcher(BlockInit.DARK_STONE.get()));
	public static final OreFeatureConfig.FillerBlockType FEYSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("progressivetechnicalities:FEYSTONE", "progressivetechnicalities:fey_stone", new BlockMatcher(BlockInit.FEY_STONE.get()));
	public static final OreFeatureConfig.FillerBlockType SANDSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("progressivetechnicalities:SANDSTONE", "progressivetechnicalities:sand_stone", new BlockMatcher(Blocks.SANDSTONE));
	public static final OreFeatureConfig.FillerBlockType LUNASTONE_FILLER = OreFeatureConfig.FillerBlockType.create("progressivetechnicalities:LUNASTONE", "progressivetechnicalities:luna_stone", new BlockMatcher(BlockInit.LUNA_STONE.get()));
	public static final OreFeatureConfig.FillerBlockType ENDSTONE_FILLER = OreFeatureConfig.FillerBlockType.create("progressivetechnicalities:ENDSTONE", "progressivetechnicalities:end_stone", new BlockMatcher(Blocks.END_STONE));


	public static void AddOresToVanillaBiomes() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (isVanillaOverworldBiome(biome)){
				generate(biome, STONE_FILLER, BlockInit.CHROMIUM_ORE.get(), 20, 40, 60, 17);
				generate(biome, STONE_FILLER, BlockInit.AIR_GEM_ORE.get(), 4, 63, 120, 8);
				generate(biome, STONE_FILLER, BlockInit.EARTH_GEM_ORE.get(), 4, 0, 16, 8);
			}

			if (biome.getCategory() == Biome.Category.OCEAN) {
				generate(biome, STONE_FILLER, BlockInit.WATER_GEM_ORE.get(), 4, 0, 16, 8);
			}
			
			if (biome.getCategory() == Biome.Category.NETHER) {
				generate(biome, NETHERRACK_FILLER, BlockInit.FIRE_GEM_ORE.get(), 8, 0, 64, 8);
			}

			if (biome.getCategory() == Biome.Category.THEEND) {
				generate(biome, ENDSTONE_FILLER, BlockInit.END_AIR_GEM_ORE.get(), 4, 0, 256, 8);
			}
		}
	}

	public static boolean isVanillaOverworldBiome(Biome biome) {
		String namespace = biome.getRegistryName().getNamespace();
		return namespace == "minecraft" && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND;
	}

	public static void generate(Biome biome, OreFeatureConfig.FillerBlockType filler, Block oreBlock, int commoness, int minLevel, int maxLevel, int size) {
		// CountRangeConfig(commoness, min_level, max_offset, max_level) // getDefaultState(), max_size))
		ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(commoness, minLevel, 0, maxLevel));
		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
				Feature.ORE.withConfiguration(new OreFeatureConfig(filler, oreBlock.getDefaultState(), size))
				.withPlacement(customConfig));
	}
}
