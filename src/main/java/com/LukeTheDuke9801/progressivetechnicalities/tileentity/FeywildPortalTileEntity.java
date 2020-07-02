package com.LukeTheDuke9801.progressivetechnicalities.tileentity;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.NBTHelper;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.dimension.DimensionType;

public class FeywildPortalTileEntity extends TileEntity implements ITickableTileEntity{
	public int tick;
	public static int lifeSpan = 5*60*20;
	boolean initialized = false;
	
	public FeywildPortalTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public FeywildPortalTileEntity() {
		this(ModTileEntityTypes.FEYWILD_PORTAL.get());
	}
	
	@Override
	public void tick() {
		if (!initialized) {
			init();
		}
		tick++;
		if (tick >= lifeSpan) {
			removePortal();
			this.remove();
		}
	}
	
	private void init() {
		initialized = true;
		tick = 0;
	}
	
	private void removePortal() {
		DimensionType feyDimType = DimensionType.byName(ProgressiveTechnicalities.FEY_DIM_TYPE);
		boolean isInFeywild = this.world.getDimension().getType() == feyDimType;
		ProgressiveTechnicalities.LOGGER.debug(isInFeywild);
		if (isInFeywild) {
			this.world.setBlockState(this.pos, Blocks.MYCELIUM.getDefaultState());
		}
	      
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("initvalues", NBTHelper.toNBT(this));
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		CompoundNBT initValues = compound.getCompound("initvalues");
		if (initValues != null) {
			this.tick = initValues.getInt("tick");
			initialized = true;
			return;
		}
		init();
	}

}
