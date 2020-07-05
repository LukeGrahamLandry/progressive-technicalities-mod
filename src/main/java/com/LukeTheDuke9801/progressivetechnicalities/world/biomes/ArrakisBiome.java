package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class ArrakisBiome extends Biome{
	public ArrakisBiome(Builder biomeBuilder) {
		super(biomeBuilder);
		
		
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.HUSK, 100, 1, 3));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 10, 1, 3));
	    // this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.RABBIT, 1, 1, 1));
	    
	    BlockClusterFeatureConfig CACTUS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.CACTUS.getDefaultState()), new ColumnBlockPlacer(1, 2))).tries(10).func_227317_b_().build();
	    this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(CACTUS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(5))));
	    
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
