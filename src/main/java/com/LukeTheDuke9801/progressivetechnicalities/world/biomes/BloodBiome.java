package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;

public class BloodBiome extends Biome{
	public BloodBiome(Builder biomeBuilder) {
		super(biomeBuilder);
		
		
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 100, 5, 10));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 5, 10));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 5, 10));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CAVE_SPIDER, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SILVERFISH, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITHER_SKELETON, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.BLAZE, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.EVOKER, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.VEX, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.GHAST, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.PHANTOM, 100, 5, 10));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITHER, 5, 1, 1));
	    
	    
	    this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.05D, MineshaftStructure.Type.NORMAL)));
	    this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.3F)));
	    this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.2F)));
	    }
	
	@Override
	public int getSkyColor() {
		return 0;
	}
}
