package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.oil_generator.OilGeneratorTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CastingTableBlock extends Block{
	public CastingTableBlock(Block.Properties builder) {
	      super(builder);
	   }
	

	   /**
	    * Interface for handling interaction with blocks that impliment AbstractFurnaceBlock. Called in onBlockActivated
	    * inside AbstractFurnaceBlock.
	    */
	   
	   
	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   Item handHeld = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem();
		   if (handHeld instanceof BucketItem) {
			   Fluid fluid = ((BucketItem) handHeld).getFluid();
			   Item result = null;
			   if (fluid.isEquivalentTo(FluidInit.SILVER_FLUID.get())) {
				   result = ItemInit.SILVER_INGOT.get();
			   }
			   
			   if (result != null) {
				   player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.BUCKET));
				   
				   ItemEntity itementity = new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(result));
				   itementity.setDefaultPickupDelay();
				   worldIn.addEntity(itementity);
				   
				   return ActionResultType.SUCCESS;
			   }
		   }
		   return ActionResultType.FAIL;
	      
	   }

	}