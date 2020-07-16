package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.auto_spawner;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.MagicEggShell;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class AutoSpawnerBlock extends Block{
	
	public AutoSpawnerBlock(Properties propertises) {
		super(propertises);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.AUTO_SPAWNER.get().create();
	}

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   ItemStack handHeld = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		   if (handHeld.getItem() instanceof MagicEggShell) {
			   EntityType mobType = MagicEggShell.getType(handHeld);
			   if (mobType != null) {
				   ((AutoSpawnerTileEntity) worldIn.getTileEntity(pos)).setMobType(mobType);
				   MagicEggShell.setEmpty(handHeld);
				   return ActionResultType.SUCCESS;
			   }
		   }
		   return ActionResultType.FAIL;
	      
	   }
}
