package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines;

import net.minecraftforge.energy.EnergyStorage;

public class ModEnergyStorage extends EnergyStorage{
	public ModEnergyStorage(int capacity, int rate) {
		super(capacity, rate);
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
