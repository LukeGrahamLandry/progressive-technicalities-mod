package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.oil.OilGeneratorTileEntity;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class CastingTableBlock extends SimpleMachineBlock{
	public static final int cost = 5;
	public CastingTableBlock(Block.Properties builder) {
	      super(builder);
	   }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Right click with a bucket of fluid to solidify it (takes power)"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	   
	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   Item handHeld = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem();
		   if (handHeld instanceof BucketItem) {
			   Fluid fluid = ((BucketItem) handHeld).getFluid();
			   Item result = null;
			   if (fluid.isEquivalentTo(FluidInit.NYMPHARIUM_FLUID.get())) {
				   result = ItemInit.NYMPHARIUM_INGOT.get();
			   }
			   if (fluid.isEquivalentTo(FluidInit.OIL_FLUID.get())) {
				   result = ItemInit.TINY_COAL.get();
			   }
			   if (fluid.isEquivalentTo(Fluids.LAVA)) {
				   result = Items.OBSIDIAN;
			   }
			   if (fluid.isEquivalentTo(Fluids.WATER)) {
				   result = Items.ICE;
			   }
			   
			   if (result != null) {
				   boolean success = expendExperience(worldIn, pos, player, this.cost);
				   if (!success) return ActionResultType.FAIL;
				   
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