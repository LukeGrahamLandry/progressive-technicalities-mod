package com.LukeTheDuke9801.progressivetechnicalities.world.dimension;

import net.minecraft.world.gen.GenerationSettings;

public class BloodDimensionGenSettings extends GenerationSettings{
	public int getBiomeSize() {
		return 4;
	}
	
	public int getRiverSize() {
		return 4;
	}
	
	public int getBiomeId() {
		return -1;
	}
	
	public int getBedrockFloorHeight() {
		return -1;
	}
}
