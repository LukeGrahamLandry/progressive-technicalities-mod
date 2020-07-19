package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XPContainer {
	public int capacity;
	public int transferRate;
	public boolean isTank;
	public int xp; 
	
	public XPContainer(int capacityIn, int transferRateIn, boolean isTankIn) {
		this.capacity = capacityIn;
		this.transferRate = transferRateIn;
		this.isTank = isTankIn;
		this.xp = 0;
	}
	
	// returns the amount that didn't fit in
	public int addXP(int amount) {
		int remaining = 0;
		
		if (amount > this.transferRate) {
			this.xp += this.transferRate;
			remaining += amount - this.transferRate;
		} else {
			this.xp += amount;
		}
		
		if (this.xp > this.capacity) {
			remaining += this.xp - this.capacity;
			this.xp = this.capacity;
		} 
		
		return remaining;
	}
	
	// returns true if it could remove the power
	public boolean removeXP(int amount) {
		if (this.xp >= amount) {
			this.xp -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isFull() {
		return this.xp == this.capacity;
	}
	
	public static XPContainer getXPContainerAtPos(World world, BlockPos pos) {
		XPContainer container = null;
		if (world.getTileEntity(pos) instanceof IXPContainer) {
			container = ((IXPContainer)world.getTileEntity(pos)).getXPContainer();
		}
		return container;
	}
	
	public CompoundNBT toNBT() {
		CompoundNBT compound = new CompoundNBT();
		compound.putInt("capacity", this.capacity);
		compound.putInt("transferRate",this. transferRate);
		compound.putBoolean("isTank", this.isTank);
		compound.putInt("xp", this.xp);
		return compound;
	}
	
	public static XPContainer fromNBT(CompoundNBT compound) {
		CompoundNBT c = compound.getCompound("xpContainer");
		XPContainer obj = new XPContainer(c.getInt("capacity"), c.getInt("transferRate"), c.getBoolean("isTank"));
		obj.addXP(c.getInt("xp"));
		return obj;
	}
}
