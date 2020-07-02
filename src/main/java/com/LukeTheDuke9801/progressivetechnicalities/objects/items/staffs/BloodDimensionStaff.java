package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class BloodDimensionStaff extends Item{
	public BloodDimensionStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Summons lava"));
			tooltip.add(new StringTextComponent("Shift rightclick toggles between oil dimension and overworld (spawns a portal)"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		DimensionType bloodDimType = DimensionType.byName(ProgressiveTechnicalities.OIL_DIM_TYPE);
		if (KeyboardHelper.isHoldingShift()) {
			if (worldIn.dimension.getType() == DimensionType.OVERWORLD) {
				playerIn.changeDimension(bloodDimType);
			} else if (worldIn.dimension.getType() == bloodDimType){
				playerIn.changeDimension(DimensionType.OVERWORLD);
			}
			playerIn.setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());  // load the chunk?
		} 
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
}
