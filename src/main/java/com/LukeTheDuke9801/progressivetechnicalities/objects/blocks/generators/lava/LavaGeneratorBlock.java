package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.lava;

import java.util.Random;

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

public class LavaGeneratorBlock extends Block{
	private static final int secondsPerFuel = 10;
	public LavaGeneratorBlock(Block.Properties builder) {
	      super(builder);
	   }
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.LAVA_GENERATOR.get().create();
	}

   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      AbstractGenoratorTileEntity te = ((AbstractGenoratorTileEntity)tileentity);
      if (!worldIn.isRemote) {
    	  ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
    	  if (te.timeRemaining == 0 && stack.getItem().equals(Items.LAVA_BUCKET)) {
    		  te.timeRemaining = secondsPerFuel;
    		  player.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BUCKET));
    	  }
      }
      return ActionResultType.FAIL;
   }

}