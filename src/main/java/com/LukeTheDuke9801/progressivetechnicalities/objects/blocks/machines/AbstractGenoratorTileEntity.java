package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class AbstractGenoratorTileEntity extends TileEntity implements ITickableTileEntity{
	private int fluidAmount;
	private String fluidType;
	public static final int maxFluidAmount = 2000;
	public static final int energyPerMilibucket = 20;
	
	public AbstractGenoratorTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		this.fluidAmount = 0;
		this.fluidType = null;
	}
	
	@Override
	public void tick() {
		if (fluidAmount > 0) {
			fluidAmount--;
			TileEntity te = this.world.getTileEntity(this.pos.up());
			if (te instanceof AbstractMachineTileEntity) {
				((AbstractMachineTileEntity) te).addEnergy(this.energyPerMilibucket);
			}
			
		}
	}
	
	public boolean addFluid(int amount, Item bucketItemIn) {
		Fluid fluidIn = ((BucketItem)bucketItemIn).getFluid();
		boolean fluidIsValid = fluidIn.isEquivalentTo(FluidInit.OIL_FLUID.get());
		boolean validFluidType = (this.fluidAmount == 0) || fluidIn.getRegistryName().toString() == this.fluidType;
		boolean spaceForFluid = this.fluidAmount + amount <= this.maxFluidAmount;
		ProgressiveTechnicalities.LOGGER.debug(fluidIsValid);
		ProgressiveTechnicalities.LOGGER.debug(spaceForFluid);
		ProgressiveTechnicalities.LOGGER.debug(validFluidType);
		ProgressiveTechnicalities.LOGGER.debug("~");
		if (fluidIsValid && spaceForFluid && validFluidType) {
			this.fluidType = fluidIn.getRegistryName().toString();
			this.fluidAmount += amount;
			return true;
		} else {
			return false;
		}
	}
	

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("fluidAmount", this.fluidAmount);
		compound.putString("fluidType", this.fluidType);
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		
		this.fluidAmount = compound.getInt("fluidAmount");
		this.fluidType = compound.getString("fluidType");
	}

}
