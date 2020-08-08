package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.oil;

import java.util.List;
import java.util.Random;

import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.AbstractGenoratorTileEntity;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class OilGeneratorBlock extends Block{
	private static final int secondsPerFuel = 20;
	public OilGeneratorBlock(Block.Properties builder) {
	      super(builder);
	   }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Generates power from oil"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.OIL_GENERATOR.get().create();
	}

   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      AbstractGenoratorTileEntity te = ((AbstractGenoratorTileEntity)tileentity);
      if (!worldIn.isRemote) {
    	  ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
    	  if (te.timeRemaining == 0 && stack.getItem().equals(FluidInit.OIL_BUCKET.get())) {
    		  te.timeRemaining = secondsPerFuel;
    		  player.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BUCKET));
    	  }
      }
      return ActionResultType.FAIL;
   }

}