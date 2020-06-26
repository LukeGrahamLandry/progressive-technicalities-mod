package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;

public class BloodBiome extends Biome{
	public BloodBiome(Builder biomeBuilder) {
		super(biomeBuilder);
		addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 2, 5));
		addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SILVERFISH, 20, 5, 10));
	    addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 20, 3, 6));
	    addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 20, 1, 3));
	    addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 20, 1, 3));
	    
	    this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.05D, MineshaftStructure.Type.NORMAL)));
	}
	
	@Override
	public int getSkyColor() {
		return 16711680;
	}
}
