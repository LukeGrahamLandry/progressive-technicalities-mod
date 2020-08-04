package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.DimensionHelper;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class FeywildPortalKey extends Item implements RitualCatalyst {
   public FeywildPortalKey(Item.Properties builder) {
      super(builder);
   }
   
   @Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Activates a basic ritual that sends you to the feywild. Rightclick in the feywild to create a portal home"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

   /**
    * Called when this item is used when targetting a Block
    */
   public ActionResultType onItemUse(ItemUseContext context) {
      World world = context.getWorld();
      if (world.isRemote) {
         return ActionResultType.SUCCESS;
      } else {
         ItemStack itemstack = context.getItem();
         BlockPos blockpos = context.getPos();
         
         if (validPortalSpawnLocation(world, blockpos)) {
        	 genPortal(world, blockpos);
        	 itemstack.shrink(1);
         }

         return ActionResultType.SUCCESS;
      }
   }
   
   private boolean validPortalSpawnLocation(World world, BlockPos pos) {
		boolean valid = world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK;
		valid = valid && world.getBlockState(pos.north()).getBlock() == Blocks.GRASS_BLOCK;
		valid = valid && world.getBlockState(pos.south()).getBlock() == Blocks.GRASS_BLOCK;
		valid = valid && world.getBlockState(pos.east()).getBlock() == Blocks.GRASS_BLOCK;
		valid = valid && world.getBlockState(pos.west()).getBlock() == Blocks.GRASS_BLOCK;

		boolean isInFeywild = world.getDimension().getType() == DimensionHelper.FEYWILD;
		
		return valid && isInFeywild;
	}
	
	private void genPortal(World world, BlockPos pos) {
		world.setBlockState(pos, BlockInit.FEYWILD_PORTAL.get().getDefaultState());
		world.setBlockState(pos.north(), Blocks.MYCELIUM.getDefaultState());
		world.setBlockState(pos.south(), Blocks.MYCELIUM.getDefaultState());
		world.setBlockState(pos.east(), Blocks.MYCELIUM.getDefaultState());
		world.setBlockState(pos.west(), Blocks.MYCELIUM.getDefaultState());
		world.setBlockState(pos.up().north(), Blocks.BROWN_MUSHROOM.getDefaultState());
		world.setBlockState(pos.up().south(), Blocks.BROWN_MUSHROOM.getDefaultState());
		world.setBlockState(pos.up().east(), Blocks.RED_MUSHROOM.getDefaultState());
		world.setBlockState(pos.up().west(), Blocks.RED_MUSHROOM.getDefaultState());
	}

	private boolean validSpawnLocation(World world, BlockPos pos) {
		return !world.getBlockState(pos).isSolid() &&
				!world.getBlockState(pos.up()).isSolid() &&
				world.getBlockState(pos.down()).isSolid();
	}

	public void doRitual(World world, BlockPos pos, PlayerEntity player){
		DimensionHelper.changeDimension(player, DimensionHelper.FEYWILD);

		player.setPosition(player.getPosX(), 63, player.getPosZ());

		// make you spawn on a new portal in the lowest air space above sea level
		for (int i=0; i<128; i++) {
			if (validSpawnLocation(player.world, player.getPosition())) {
				break;
			} else {
				player.teleportKeepLoaded(player.getPosX(), player.getPosY() + 1, player.getPosZ());
			}
		}
	}

}