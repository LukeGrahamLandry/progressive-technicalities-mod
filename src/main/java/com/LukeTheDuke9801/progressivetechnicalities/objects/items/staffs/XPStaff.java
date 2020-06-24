package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

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

public class XPStaff extends Item{
	private int xp;
	
	public XPStaff(Properties properties) {
		super(properties);
		this.xp = 0;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return this.xp != 0;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Shift rightclick to store xp, rightclick to retrive. All staffs share a xp pool"));
			tooltip.add(new StringTextComponent("Currently holding " + Integer.toString(this.xp/2) + " xp"));
		} 
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		int e = playerIn.xpBarCap();
		if (KeyboardHelper.isHoldingShift()) {
			if (playerIn.experienceTotal >= e) {
				this.xp += e;
				playerIn.giveExperiencePoints(-e);
			} else {
				this.xp += playerIn.experienceTotal;
				playerIn.giveExperiencePoints(-playerIn.experienceTotal);
			}
			
		} else {
			if (this.xp >= e) {
				this.xp -= e;
				playerIn.giveExperiencePoints(e);
			} else {
				playerIn.giveExperiencePoints(this.xp);
				this.xp = 0;
			}
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
}
