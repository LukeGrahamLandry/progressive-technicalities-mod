package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class RecallStaff extends Item{
	public RecallStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Teleport to your bed"));
			tooltip.add(new StringTextComponent("Shift rightclick to teleport to world spawn"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		if (worldIn.dimension.getType() == DimensionType.OVERWORLD) {
			BlockPos pos; 
			if (KeyboardHelper.isHoldingShift()) {
				// world spwan
				pos = worldIn.getSpawnPoint();
			} else {
				// player spawn
				pos = playerIn.getBedLocation(DimensionType.OVERWORLD);
			}
			
			playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
			
		}
		
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
}
