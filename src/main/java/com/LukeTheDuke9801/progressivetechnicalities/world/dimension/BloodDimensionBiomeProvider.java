package com.LukeTheDuke9801.progressivetechnicalities.world.dimension;

import java.util.List;
import java.util.Set;

import com.LukeTheDuke9801.progressivetechnicalities.init.BiomeInit;
import com.google.common.collect.ImmutableSet;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class BloodDimensionBiomeProvider extends BiomeProvider{
	private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.BLOOD_SPIKES.get());

	public BloodDimensionBiomeProvider() {
		super(biomeList);
	}

	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return BiomeInit.BLOOD_SPIKES.get();
	}

	public Biome getBiome(List<Biome> biomeList, double noiseVal) {
		return BiomeInit.BLOOD_SPIKES.get();
	}
}
