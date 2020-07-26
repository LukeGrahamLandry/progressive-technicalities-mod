package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.village_gen;

import com.LukeTheDuke9801.progressivetechnicalities.entities.AbstractWanderer;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.IXPContainer;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.XPContainer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class VillageGenTileEntity extends TileEntity implements ITickableTileEntity{
	public VillageGenTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public VillageGenTileEntity() {
		this(ModTileEntityTypes.VILLAGE_GEN.get());
	}
	
	@Override
	public void tick() {
		placeHouse(this.pos.up());
		AbstractWanderer e = ModEntityTypes.WANDERING_GEM_SMITH.get().create(this.world, null, null, null, this.pos.add(2, 1, 2), SpawnReason.SPAWN_EGG, false, false);
		e.setHome(this.pos.add(2, 1, 3));
		this.world.addEntity(e);

		placeHouse(this.pos.up().add(7, 0, 0));
		e = ModEntityTypes.WANDERING_ASTRONOMER.get().create(this.world, null, null, null, this.pos.add(9, 1, 2), SpawnReason.SPAWN_EGG, false, false);
		e.setHome(this.pos.add(9, 1, 3));
		this.world.addEntity(e);

		this.remove();
	}

	private void placeHouse(BlockPos blPos){
		// corners
		placeColumn(Blocks.BONE_BLOCK, blPos, 4);
		placeColumn(Blocks.BONE_BLOCK, blPos.add(4, 0, 0), 4);
		placeColumn(Blocks.BONE_BLOCK, blPos.add(0, 0, 4), 4);
		placeColumn(Blocks.BONE_BLOCK, blPos.add(4, 0, 4), 4);

		// walls
		for (int i=1;i<4;i++){
			placeColumn(Blocks.QUARTZ_BLOCK, blPos.add(i, 0, 0), 4);
		}
		for (int i=1;i<4;i++){
			placeColumn(Blocks.QUARTZ_BLOCK, blPos.add(0, 0, i), 4);
		}
		for (int i=1;i<4;i++){
			placeColumn(Blocks.QUARTZ_BLOCK, blPos.add(4-i, 0, 4), 4);
		}
		for (int i=1;i<4;i++){
			placeColumn(Blocks.QUARTZ_BLOCK, blPos.add(4, 0, 4-i), 4);
		}

		// roof & floor
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				placeBlock(Blocks.QUARTZ_BLOCK, blPos.add(1+i, 3, 1+j));
			}
		}
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
				placeBlock(Blocks.QUARTZ_BLOCK, blPos.add(i, -1, j));
			}
		}
		placeBlock(Blocks.GLOWSTONE, blPos.add(2, -1, 2));

		// door
		placeBlock(Blocks.AIR, blPos.add(2, 0, 0));
		placeBlock(Blocks.AIR, blPos.add(2, 1, 0));

		// empty inside
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				placeColumn(Blocks.AIR, blPos.add(1+i, 0, 1+j), 3);
			}
		}

		// empty front
		for (int i=0;i<7;i++){
			for (int j=0;j<2;j++){
				placeColumn(Blocks.AIR, blPos.add(i, 0, -1 -j), 4);
				placeBlock(Blocks.QUARTZ_BLOCK, blPos.add(i, -1, -1 -j));
			}
		}
	}


	private void placeColumn(Block block, BlockPos bottomPos, int height){
		for (int i=0;i<height;i++){
			placeBlock(block, bottomPos.up(i));
		}
	}

	private void placeBlock(Block block, BlockPos pos){
		this.world.setBlockState(pos, block.getDefaultState());
	}
}
