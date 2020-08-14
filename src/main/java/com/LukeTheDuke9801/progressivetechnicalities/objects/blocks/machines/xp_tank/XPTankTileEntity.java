package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.xp_tank;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.XPContainerHolder;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.XPContainer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class XPTankTileEntity extends TileEntity implements ITickableTileEntity, XPContainerHolder {
	private int tick = 0;
	
	public XPContainer xpContainer;
	public XPContainer getXPContainer() {
		return this.xpContainer;
	}
	
	public XPTankTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		this.xpContainer = new XPContainer(1000, 50, true);
	}
	
	public XPTankTileEntity() {
		this(ModTileEntityTypes.XP_TANK.get());
	}
	
	@Override
	public void tick() {
		tick++;
		if (tick == 20) {
			tick = 0;
			if (world.isRemote) return;
			execute();
		}
	}
	
	void execute() {
		NonNullList<BlockPos> sides = NonNullList.create();
		sides.add(pos.up()); sides.add(pos.down()); sides.add(pos.north()); 
		sides.add(pos.south()); sides.add(pos.east()); sides.add(pos.west());
		
		NonNullList<XPContainer> containers = NonNullList.create();
		
		for (BlockPos position : sides) {
			XPContainer container = XPContainer.getXPContainerAtPos(world, position);
			if (container == null) continue;
			
			if (!container.isTank && !container.isFull()) {
				containers.add(container);
			}
		}
		
		if (containers.size() == 0) return;
		
		int sendable = Math.min(this.xpContainer.xp, this.xpContainer.transferRate);
		int amountToSend = sendable / containers.size();
		boolean success = this.xpContainer.removeXP(sendable);
		if (!success) return;  // should never fail ^
		
		for (XPContainer container : containers) {
			int remaining = container.addXP(amountToSend);
			this.xpContainer.addXP(remaining);
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("xpContainer", this.xpContainer.toNBT());
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.xpContainer = XPContainer.fromNBT(compound);
		
	}

}
