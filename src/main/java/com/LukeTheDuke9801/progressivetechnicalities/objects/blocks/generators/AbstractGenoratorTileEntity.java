package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class AbstractGenoratorTileEntity extends TileEntity implements ITickableTileEntity{
	private int tick = 0;
	public int timeRemaining;
	private int amountGenerated;
	
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
				outputEX();
				timeRemaining--;
			}
		}
	}
	
	private void outputEX() {
		ExperienceOrbEntity orb = new ExperienceOrbEntity(world, this.pos.getX(), this.pos.getY()+2, this.pos.getZ(), this.amountGenerated);
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
