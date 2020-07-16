package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.mob_slayer;

import java.util.List;

import javax.annotation.Nullable;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.NBTHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class MobSlayerTileEntity extends TileEntity implements ITickableTileEntity{
	int tick = 0;
	int damagePerSecond = 3;
	int range = 8;
	
	public MobSlayerTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public MobSlayerTileEntity() {
		this(ModTileEntityTypes.MOB_SLAYER.get());
	}
	
	@Override
	public void tick() {
		tick++;
		if (tick == 20) {
			tick = 0;
			execute();
		}
	}
	
	private void execute(){
		if (world.isRemote) return;
		
		List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(null, getAABB());
		for (Entity entity : entities) {
			boolean isMonster = entity instanceof MonsterEntity;
			if (isMonster) {
				entity.attackEntityFrom(DamageSource.MAGIC, damagePerSecond);
				boolean isDead = ((MonsterEntity)entity).getHealth() == 0;
				if (isDead) {
					ExperienceOrbEntity orb = new ExperienceOrbEntity(world, this.pos.getX(), this.pos.getY()+2, this.pos.getZ(), 5);
					world.addEntity(orb);
				}
			}
		}
	}
	
	private AxisAlignedBB getAABB() {
		double x = this.pos.getX();
		double y = this.pos.getY();
		double z = this.pos.getZ();
		return new AxisAlignedBB(x-range,y-range,z-range,x+range,y+range,z+range);
	}
}
