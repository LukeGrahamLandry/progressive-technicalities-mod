package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FeatureInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.world.feature.FruitTree;
import com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FruitForestBiome extends Biome{
	public FruitForestBiome() {
		super(new Builder().waterColor(16711680).waterFogColor(0).surfaceBuilder(SurfaceBuilder.DEFAULT,
				new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState()))
				.category(Category.FOREST)
				.precipitation(RainType.NONE)
				.downfall(0.0f)
				.depth(0.125f)
				.parent(null)
				.waterColor(16711935)
				.waterFogColor(16711935)
				.scale(0.05f)
				.temperature(0.8F));

		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntityTypes.FAIRY, 25, 1, 1));

		addCherryTrees(this);

		OreGen.generate(this, OreGen.FEYSTONE_FILLER, BlockInit.FEYSTEEL_ORE.get(), 20, 16, 64, 4);
		OreGen.generate(this, OreGen.FEYSTONE_FILLER, BlockInit.FEY_DIAMOND_ORE.get(), 1, 0, 16, 8);
	}

	public static void addCherryTrees(Biome biomeIn) {
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.NORMAL_TREE.withConfiguration(FruitTree.CHERRY_TREE_CONFIG).withPlacement(
						Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(3, 0.1f, 1))));
	}
	
	@Override
	public int getSkyColor() {
		return 16711935;
	}
	
	@Override
	public int getGrassColor(double posX, double posZ) {
		return 16711904;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public int getFoliageColor() {
		return 16711904;
	}
}
