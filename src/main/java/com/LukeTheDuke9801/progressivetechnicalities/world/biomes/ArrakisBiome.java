package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class ArrakisBiome extends Biome{
	public ArrakisBiome() {
		super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.SAND_SAND_GRAVEL_CONFIG)
				.category(Category.DESERT)
				.precipitation(RainType.NONE)
				.downfall(0.0f)
				.depth(0.45f)
				.parent(null)
				.waterColor(16711935)
				.waterFogColor(16711935)
				.scale(0.3f)
				.temperature(2F));
		
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.HUSK, 100, 1, 3));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 10, 1, 3));
	    // this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.RABBIT, 1, 1, 1));
	    
	    BlockClusterFeatureConfig CACTUS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.CACTUS.getDefaultState()), new ColumnBlockPlacer(1, 2))).tries(10).func_227317_b_().build();
	    this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(CACTUS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(5))));

		OreGen.generate(this, OreGen.SANDSTONE_FILLER, BlockInit.MELANGE_DEPOSIT.get(), 10, 0, 70, 20);
		OreGen.generate(this, OreGen.SANDSTONE_FILLER, Blocks.SAND, 25, 0, 70, 50);
		OreGen.generate(this, OreGen.SANDSTONE_FILLER, BlockInit.SANDSTONE_FIRE_GEM_ORE.get(), 4, 0, 16, 8);
	}
	
	@Override
	public int getSkyColor() {
		return 16777108;
	}
	
	@Override
	public int getGrassColor(double posX, double posZ) {
		return 7819015;
	}
}
