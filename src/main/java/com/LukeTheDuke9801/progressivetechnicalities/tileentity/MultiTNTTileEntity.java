package com.LukeTheDuke9801.progressivetechnicalities.tileentity;


import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.NBTHelper;

import net.minecraft.entity.item.TNTEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MultiTNTTileEntity extends TileEntity implements ITickableTileEntity{
	public int x, y, z, tick;
	boolean initialized = false;
	
	public MultiTNTTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public MultiTNTTileEntity() {
		this(ModTileEntityTypes.MULTI_TNT.get());
	}
	
	@Override
	public void tick() {
		if (!initialized) {
			init();
		}
		tick++;
		if (tick < 80) {
			execute();
		}
	}
	
	private void init() {
		initialized = true;
		x = this.pos.getX();
		y = this.pos.getY() + 1;
		z = this.pos.getZ();
		tick = 0;
	}
	
	private void execute(){
		if (world.isRemote) return;
		
		TNTEntity tnt = new TNTEntity(this.world, this.x, this.y, this.z, null);
		this.world.addEntity(tnt);
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
			this.x = initValues.getInt("x");
			this.y = initValues.getInt("y");
			this.z = initValues.getInt("z");
			this.tick = initValues.getInt("tick");
			initialized = true;
			return;
		}
		init();
	}

}
