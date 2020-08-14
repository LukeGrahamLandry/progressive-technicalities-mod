package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FeatureInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
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

public class LunaBiome extends Biome{
	public LunaBiome() {
		super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT,
				new SurfaceBuilderConfig(BlockInit.LUNA_DUST.get().getDefaultState(), BlockInit.LUNA_DUST.get().getDefaultState(), BlockInit.LUNA_DUST.get().getDefaultState()))
				.category(Category.PLAINS)
				.precipitation(RainType.NONE)
				.downfall(0.0f)
				.depth(0.125f)
				.parent(null)
				.waterColor(0)
				.waterFogColor(0)
				.scale(0.1f)
				.temperature(0.8F));
		
	    this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.AIR.getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(2))));

		OreGen.generate(this, OreGen.LUNASTONE_FILLER, BlockInit.DILITHIUM_ORE.get(), 100, 0, 30, 4);
		FeatureInit.generate(this, FeatureInit.METEOR);
	}
	
	@Override
	public int getSkyColor() {
		return 0;
	}
	
	@Override
	public int getGrassColor(double posX, double posZ) {
		return 65308;
	}
}
