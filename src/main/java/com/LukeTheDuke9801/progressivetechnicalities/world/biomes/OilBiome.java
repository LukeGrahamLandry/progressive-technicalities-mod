package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FeatureInit;
import com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class OilBiome extends Biome{
	public OilBiome() {
		super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT,
				new SurfaceBuilderConfig(Blocks.OBSIDIAN.getDefaultState(), Blocks.OBSIDIAN.getDefaultState(), Blocks.OBSIDIAN.getDefaultState()))
				.category(Category.PLAINS)
				.precipitation(RainType.SNOW)
				.downfall(0.8f)
				.depth(0.125f)
				.waterColor(0)
				.waterFogColor(0)
				.parent(null)
				.scale(2f)
				.temperature(0f));

		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CAVE_SPIDER, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SILVERFISH, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 100, 5, 10));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.PHANTOM, 100, 5, 10));

	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITHER_SKELETON, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.BLAZE, 100, 5, 10));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.GHAST, 100, 5, 10));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.MAGMA_CUBE, 100, 5, 10));

	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITHER, 1, 1, 1));
	    
	    
	    this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.05D, MineshaftStructure.Type.NORMAL)));
	    this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.3F)));
	    this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.2F)));

		OreGen.generate(this, OreGen.DARKSTONE_FILLER, BlockInit.TITANIUM_ORE.get(), 100, 0, 200, 4);
		FeatureInit.generate(this, FeatureInit.RITUAL);
	}
	
	@Override
	public int getSkyColor() {
		return 0;
	}
}
