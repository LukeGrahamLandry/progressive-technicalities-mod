package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.mob_spawner;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.IXPContainer;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.XPContainer;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MobSpawnerTileEntity extends TileEntity implements ITickableTileEntity, IXPContainer{
	int tick = 0;
	String mobType;
	int cost = 5;
	
	public XPContainer xpContainer;
	public XPContainer getXPContainer() {
		return this.xpContainer;
	}
	
	public MobSpawnerTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		this.mobType = "";
		this.xpContainer = new XPContainer(200, 20, false);
	}
	
	public MobSpawnerTileEntity() {
		this(ModTileEntityTypes.MOB_SPAWNER.get());
	}
	
	@Override
	public void tick() {
		tick++;
		if (tick == 100) {
			tick = 0;
			execute();
		}
	}
	
	private void execute(){
		if (world.isRemote) return;
		
		if (this.mobType != "") {
			boolean success = this.xpContainer.removeXP(this.cost);
			if (!success) return;
			
			EntityType e = EntityType.byKey(this.mobType).get();
			e.spawn(world, ItemStack.EMPTY, null, pos.up(), SpawnReason.SPAWNER, false, false);
		}
	}
	
	public void setMobType(EntityType typeIn) {
		String key = EntityType.getKey(typeIn).toString();
		this.mobType = key;
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putString("type", this.mobType);
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.mobType = compound.getString("type");
		
	}
}
