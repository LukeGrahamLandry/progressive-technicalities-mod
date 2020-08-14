package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators;

import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.XPContainerHolder;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.XPContainer;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractGenoratorTileEntity extends TileEntity implements ITickableTileEntity{
	private int tick = 0;
	public int timeRemaining;
	public int amountGenerated;
	
	public AbstractGenoratorTileEntity(final TileEntityType<?> tileEntityTypeIn, int amountGeneratedIn) {
		super(tileEntityTypeIn);
		this.timeRemaining = 0;
		this.amountGenerated = amountGeneratedIn;
	}
	
	@Override
	public void tick() {
		tick++;
		if (tick == 20) {
			tick = 0;
			if (timeRemaining > 0) {
				boolean shouldDropOrbs = !outputToTanks();
				if (shouldDropOrbs) {
					spawnXPEntity(this.amountGenerated);
				}
				timeRemaining--;
			}
		}
	}
	
	// returns false if there are no adjacent tanks
	private boolean outputToTanks() {
		NonNullList<BlockPos> sides = NonNullList.create();
		sides.add(pos.up()); sides.add(pos.down()); sides.add(pos.north()); 
		sides.add(pos.south()); sides.add(pos.east()); sides.add(pos.west());
		
		NonNullList<XPContainer> containers = NonNullList.create();
		
		for (BlockPos position : sides) {
			XPContainer container;
			if (world.getTileEntity(position) instanceof XPContainerHolder) {
				container = ((XPContainerHolder)world.getTileEntity(position)).getXPContainer();
			} else {continue;}
			
			if (!container.isFull()) {
				containers.add(container);
			}
		}
		
		if (containers.size() == 0) return false;
		
		int amountToSend = amountGenerated / containers.size();
		
		// if amountGenerated is less than containers.size()
		if (amountToSend == 0) {
			containers.get(0).addXP(amountGenerated);
			return true;
		}
		
		int remaining = 0;
		for (XPContainer container : containers) {
			remaining = container.addXP(amountToSend + remaining);
		}
		
		if (remaining > 0) {
			spawnXPEntity(remaining);  // dont delete overflow
		}
		
		return true;
	}
	
	private void spawnXPEntity(int amount) {
		ExperienceOrbEntity orb = new ExperienceOrbEntity(world, this.pos.getX(), this.pos.getY()+2, this.pos.getZ(), amount);
		world.addEntity(orb);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("timeRemaining", this.timeRemaining);
		compound.putInt("amountGenerated", this.amountGenerated);
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		
		this.timeRemaining = compound.getInt("timeRemaining");
		this.amountGenerated = compound.getInt("amountGenerated");
	}

}
