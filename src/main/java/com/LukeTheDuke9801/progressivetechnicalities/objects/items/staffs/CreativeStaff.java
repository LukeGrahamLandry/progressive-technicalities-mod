package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CreativeStaff extends Item{
	private Block block;
	
	public CreativeStaff(Properties properties) {
		super(properties);
		this.block = Blocks.DIRT;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Shift rightclick to select block"));
			tooltip.add(new StringTextComponent("Rightclick to place <" + this.block.getNameTextComponent().getString() + ">"));
		} 
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext ctx) {
		World world = ctx.getWorld();
		BlockPos pos = ctx.getPos();
		if (KeyboardHelper.isHoldingShift()) {
			this.block = world.getBlockState(pos).getBlock();
			
			return ActionResultType.SUCCESS;
			
		} else {
			ItemStack stackToPlace = new ItemStack(this.block);
			return substituteUse(ctx, stackToPlace);
		}
	}
	
	public static ActionResultType substituteUse(ItemUseContext ctx, ItemStack toUse) {
		// Stolen from https://github.com/Vazkii/Botania/blob/b260b74ba88cab20dfe1dfe0ec68515b371752ea/src/main/java/vazkii/botania/common/core/helper/PlayerHelper.java#L122
		ItemStack save = ItemStack.EMPTY;
		BlockRayTraceResult hit = new BlockRayTraceResult(ctx.getHitVec(), ctx.getFace(), ctx.getPos(), ctx.isInside());
		ItemUseContext newCtx;

		if (ctx.getPlayer() != null) {
			save = ctx.getPlayer().getHeldItem(ctx.getHand());
			ctx.getPlayer().setHeldItem(ctx.getHand(), toUse);
			// Need to construct a new one still to refresh the itemstack
			newCtx = new ItemUseContext(ctx.getPlayer(), ctx.getHand(), hit);
			
			BlockPos finalPos = new BlockItemUseContext(newCtx).getPos();

			ActionResultType result = toUse.onItemUse(newCtx);

			ctx.getPlayer().setHeldItem(ctx.getHand(), save);
			
			return result;
		} else {
			return ActionResultType.FAIL;
		}

		
	}
}
