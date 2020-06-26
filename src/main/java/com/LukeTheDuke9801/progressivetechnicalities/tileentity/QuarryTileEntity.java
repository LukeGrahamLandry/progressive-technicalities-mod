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
	public int x, y, z, tick, xAdd, zAdd;
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
		if (tick == 10) {
			tick = 0;
			if (y > 2) {
				execute();
			} else {
				if (world.isRemote) return;
				destroyBlock(this.pos, true, null);
			}
		}
	}
	
	private void init() {
		initialized = true;
		x = this.pos.getX() - 1;
		y = this.pos.getY() - 1;
		z = this.pos.getZ() - 1;
		xAdd = 0;
		zAdd = 0;
		tick = 0;
	}
	
	private void execute(){
		if (world.isRemote) return;
		
		// int index = 0;
		// Block[] blockRemoved = new Block[9];
		BlockPos posToBreak = new BlockPos(this.x + this.xAdd, this.y, this.z + this.zAdd);
		// blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
		destroyBlock(posToBreak, true, null);
		// index++;
		
		// expanded for loop
		this.zAdd++;
		if (this.zAdd > 2) {
			this.zAdd = 0;
			this.xAdd++;
		}
		
		if (this.xAdd > 2) {
			this.xAdd = 0;
			this.y--;
		}
		
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
				Block.spawnDrops(blockstate, world, this.pos.add(0, 2, 0), tileentity, entity, ItemStack.EMPTY);
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
