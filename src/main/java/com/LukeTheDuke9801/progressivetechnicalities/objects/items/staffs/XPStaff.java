package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class XPStaff extends Item{
	public XPStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		int xp = getXP(stack);
		return xp != 0;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			int xp = getXP(stack);
			tooltip.add(new StringTextComponent("Shift rightclick to store xp, rightclick to retrive. All staffs share a xp pool"));
			tooltip.add(new StringTextComponent("Currently holding " + Integer.toString(xp/2) + " xp"));
		} 
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		int xp = getXP(stack);
		
		int e = playerIn.xpBarCap();
		if (KeyboardHelper.isHoldingShift()) {
			if (playerIn.experienceTotal >= e) {
				xp += e;
				playerIn.giveExperiencePoints(-e);
			} else {
				xp += playerIn.experienceTotal;
				playerIn.giveExperiencePoints(-playerIn.experienceTotal);
			}
			
		} else {
			if (xp >= e) {
				xp -= e;
				playerIn.giveExperiencePoints(e);
			} else {
				playerIn.giveExperiencePoints(xp);
				xp = 0;
			}
		}
		
		setXP(stack, xp);
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
	
	private int getXP(ItemStack stack) {
		CompoundNBT nbtTagCompound = stack.getTag();
		
		if (nbtTagCompound == null || !nbtTagCompound.contains("xp")) {
			return 0;
		}
		
		int xp = nbtTagCompound.getInt("xp");
		return xp;
	}
	
	private void setXP(ItemStack stack, int xp) {
		CompoundNBT nbtTagCompound = new CompoundNBT();
		nbtTagCompound.putInt("xp", xp);
		stack.setTag(nbtTagCompound);
	}
}
