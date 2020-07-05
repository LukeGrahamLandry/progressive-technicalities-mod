package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.oil_generator;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class OilGeneratorBlock extends Block{
	public OilGeneratorBlock(Block.Properties builder) {
	      super(builder);
	   }
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.OIL_GENERATOR.get().create();
	}

	   /**
	    * Interface for handling interaction with blocks that impliment AbstractFurnaceBlock. Called in onBlockActivated
	    * inside AbstractFurnaceBlock.
	    */
	   
	   
	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	      TileEntity tileentity = worldIn.getTileEntity(pos);
	      if (tileentity instanceof OilGeneratorTileEntity && !worldIn.isRemote) {
	         Item handHeld = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem();
        	 boolean fluidUsed = ((OilGeneratorTileEntity)tileentity).addFluid(1000, handHeld);
        	 if (fluidUsed) {
        		 player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.BUCKET));
        		 return ActionResultType.SUCCESS;
        	 }
	         
	      }
	      return ActionResultType.FAIL;
	   }

	}