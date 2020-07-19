package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.coal;

import java.util.Random;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.AbstractGenoratorTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;

public class CoalGeneratorBlock extends Block{
	public CoalGeneratorBlock(Block.Properties builder) {
	      super(builder);
	   }
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.COAL_GENERATOR.get().create();
	}

   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      AbstractGenoratorTileEntity te = ((AbstractGenoratorTileEntity)tileentity);
      if (!worldIn.isRemote) {
    	  ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
    	  int burnTime = ForgeHooks.getBurnTime(stack);
    	  ProgressiveTechnicalities.LOGGER.debug(burnTime);
    	  if (te.timeRemaining == 0 && burnTime > 0) {
    		  te.timeRemaining = burnTime / 40; // one second for every two of burn time
    		  stack.shrink(1);
    	  }
      }
      return ActionResultType.FAIL;
   }

}