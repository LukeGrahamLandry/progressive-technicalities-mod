package com.LukeTheDuke9801.progressivetechnicalities.world.biomes;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class PandoraBiome extends Biome{
	public PandoraBiome() {
		super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
				.precipitation(Biome.RainType.RAIN)
				.category(Biome.Category.JUNGLE)
				.depth(0.1F)
				.scale(0.2F)
				.temperature(0.95F)
				.downfall(0.9F)
				.waterColor(4159204)
				.waterFogColor(329011));

	  DefaultBiomeFeatures.addLakes(this);
	  DefaultBiomeFeatures.addBamboo(this);
	  DefaultBiomeFeatures.addJungleTreeForest(this);
	  DefaultBiomeFeatures.addExtraDefaultFlowers(this);
	  DefaultBiomeFeatures.addJungleGrass(this);
	  DefaultBiomeFeatures.addMushrooms(this);
	  DefaultBiomeFeatures.addReedsAndPumpkins(this);
	  DefaultBiomeFeatures.addSprings(this);
	  DefaultBiomeFeatures.addJunglePlants(this);
	  DefaultBiomeFeatures.addFreezeTopLayer(this);
	  this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PARROT, 40, 1, 2));
	  this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PANDA, 1, 1, 2));
	  this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
	  this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.OCELOT, 2, 1, 3));

	  OreGen.generate(this, OreGen.STONE_FILLER, BlockInit.UNOBTANIUM_ORE.get(), 10, 0, 16, 6);
	  OreGen.generate(this, OreGen.STONE_FILLER, Blocks.INFESTED_STONE, 25, 0, 70, 50);
	  OreGen.generate(this, OreGen.STONE_FILLER, BlockInit.EARTH_GEM_ORE.get(), 4, 0, 16, 8);
	}
	
	@Override
	public int getSkyColor() {
		return 65444;
	}
	
	@Override
	public int getGrassColor(double posX, double posZ) {
		return 65280;
	}
}
