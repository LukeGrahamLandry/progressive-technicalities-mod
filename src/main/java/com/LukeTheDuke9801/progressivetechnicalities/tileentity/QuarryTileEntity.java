package com.LukeTheDuke9801.progressivetechnicalities.tileentity;

import javax.annotation.Nullable;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.NBTHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity{
	public int x, y, z, tick;
	boolean initialized = false;
	
	public QuarryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public QuarryTileEntity() {
		this(ModTileEntityTypes.QUARRY.get());
	}
	
	@Override
	public void tick() {
		if (!initialized) {
			init();
		}
		tick++;
		if (tick == 40) {
			tick = 40;
			if (y > 2) {
				execute();
			}
		}
	}
	
	private void init() {
		initialized = true;
		x = this.pos.getX() - 1;
		y = this.pos.getY() - 1;
		z = this.pos.getZ() - 1;
		tick = 0;
	}
	
	private void execute(){
		int index = 0;
		Block[] blocksRemoved = new Block[9];
		for (int x=0;x<3;x++) {
			for (int z=0;z<3;z++) {
				BlockPos posToBreak = new BlockPos(this.x + x, this.y, this.z + z);
				blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
				destroyBlock(posToBreak, true, null);
				index++;
			}
		}
		this.y--;
	}

	private Boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity) {
		BlockState blockstate = world.getBlockState(pos);
		if (blockstate.isAir(world, pos)) {
			return false;
		} else {
			IFluidState iFluidstate = world.getFluidState(pos);
			world.playEvent(2001, pos, Block.getStateId(blockstate)); // play block break sound
			if(dropBlock) {
				TileEntity tileentity = blockstate.hasTileEntity() ? world.getTileEntity(pos) : null;
				Block.spawnDrops(blockstate, world, pos.add(0, 1.5, 0), tileentity, entity, ItemStack.EMPTY);
 			}
			return world.setBlockState(pos, iFluidstate.getBlockState(), 3);
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
