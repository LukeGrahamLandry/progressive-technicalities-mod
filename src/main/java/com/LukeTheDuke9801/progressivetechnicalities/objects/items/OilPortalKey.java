package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.FeyPortalBlock;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;

public class OilPortalKey extends Item {
   public OilPortalKey(Item.Properties builder) {
      super(builder);
   }
   
   @Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Rightclick a group of obsidian in a plus shape to open a portal to the Feywild"));
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
         Direction direction = context.getFace();
         BlockState blockstate = world.getBlockState(blockpos);
         Block block = blockstate.getBlock();
         
         if (validPortalSpawnLocation(world, blockpos)) {
        	 genPortal(world, blockpos);
        	 itemstack.shrink(1);
         }

         return ActionResultType.SUCCESS;
      }
   }
   
   private boolean validPortalSpawnLocation(World world, BlockPos pos) {
		boolean valid = world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN;
		valid = valid && world.getBlockState(pos.north()).getBlock() == Blocks.OBSIDIAN;
		valid = valid && world.getBlockState(pos.south()).getBlock() == Blocks.OBSIDIAN;
		valid = valid && world.getBlockState(pos.east()).getBlock() == Blocks.OBSIDIAN;
		valid = valid && world.getBlockState(pos.west()).getBlock() == Blocks.OBSIDIAN;
				
		return valid;
	}
	
	private void genPortal(World world, BlockPos pos) {
		world.setBlockState(pos, BlockInit.OIL_PORTAL.get().getDefaultState());
		world.setBlockState(pos.up().north(), Blocks.TORCH.getDefaultState());
		world.setBlockState(pos.up().south(), Blocks.TORCH.getDefaultState());
		world.setBlockState(pos.up().east(), Blocks.TORCH.getDefaultState());
		world.setBlockState(pos.up().west(), Blocks.TORCH.getDefaultState());
	}

}