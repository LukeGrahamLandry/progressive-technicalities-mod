package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.IXPContainer;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.XPContainer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class SimpleMachineTileEntity extends TileEntity implements IXPContainer{
	public XPContainer xpContainer;
	public XPContainer getXPContainer() {
		return this.xpContainer;
	}
	
	public SimpleMachineTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		this.xpContainer = new XPContainer(100, 10, true);
	}
	
	public SimpleMachineTileEntity() {
		this(ModTileEntityTypes.SIMPLE_MACHINE.get());
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
