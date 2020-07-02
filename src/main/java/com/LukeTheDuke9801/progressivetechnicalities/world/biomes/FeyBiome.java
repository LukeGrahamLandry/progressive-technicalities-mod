package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
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

public class FeyBiome extends Biome{
	public FeyBiome(Builder biomeBuilder) {
		super(biomeBuilder);
		
		
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.VEX, 10, 1, 3));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.PHANTOM, 10, 1, 3));
	    this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.MOOSHROOM, 20, 3, 6));
	    this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.DOLPHIN, 20, 1, 5));
	    
	    DefaultBiomeFeatures.addMushrooms(this);
	    DefaultBiomeFeatures.addHugeMushrooms(this);
	    DefaultBiomeFeatures.addGrass(this);
	    DefaultBiomeFeatures.addDoubleFlowers(this);
	    DefaultBiomeFeatures.addExtraDefaultFlowers(this);
	    DefaultBiomeFeatures.addBerryBushes(this);
	    
	    this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.MELON.getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).replaceable().func_227317_b_().build()).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
	    this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(FluidInit.SILVER_FLUID_BLOCK.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(3))));
	    
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
