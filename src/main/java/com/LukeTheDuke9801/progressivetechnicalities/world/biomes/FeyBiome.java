package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FeatureInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class FeyBiome extends Biome{
	public FeyBiome() {
		super(new Biome.Builder().waterColor(16711680).waterFogColor(0).surfaceBuilder(SurfaceBuilder.DEFAULT,
				new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState()))
				.category(Category.PLAINS)
				.precipitation(RainType.NONE)
				.downfall(0.0f)
				.depth(0.125f)
				.parent(null)
				.waterColor(16711935)
				.waterFogColor(16711935)
				.scale(0.05f)
				.temperature(0.8F));

		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntityTypes.FAIRY, 25, 1, 1));

	    this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.DOLPHIN, 20, 1, 2));

	    DefaultBiomeFeatures.addMushrooms(this);
	    DefaultBiomeFeatures.addHugeMushrooms(this);
	    DefaultBiomeFeatures.addGrass(this);
	    DefaultBiomeFeatures.addDoubleFlowers(this);
	    DefaultBiomeFeatures.addExtraDefaultFlowers(this);
	    DefaultBiomeFeatures.addBerryBushes(this);
	    
	    this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.MELON.getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).replaceable().func_227317_b_().build()).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
	    this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(FluidInit.NYMPHARIUM_FLUID_BLOCK.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(3))));

		OreGen.generate(this, OreGen.FEYSTONE_FILLER, BlockInit.FEYSTEEL_ORE.get(), 25, 0, 70, 4);
		FeatureInit.generate(this, FeatureInit.FEY_HENGE);
	}
	
	@Override
	public int getSkyColor() {
		return 16711935;
	}
	
	@Override
	public int getGrassColor(double posX, double posZ) {
		return 16711904;
	}
}
