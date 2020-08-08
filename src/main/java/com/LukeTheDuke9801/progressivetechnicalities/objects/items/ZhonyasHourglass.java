package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class ZhonyasHourglass extends Item{
	static int MAX_TIMER = 60;
	static int COOLDOWN = 120*20;

	public ZhonyasHourglass(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Right click to stop moving and taking damage for 3 seconds (2 minute cooldown)"));
		} 
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		playerIn.getCooldownTracker().setCooldown(this, COOLDOWN);
		ItemStack stack = playerIn.getHeldItem(handIn);

		setTimer(stack, MAX_TIMER);
		playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, MAX_TIMER, 4));
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		int timer = getTimer(stack);
		if (timer > 0){
			setTimer(stack, timer - 1);
			entityIn.setMotion(0, 0, 0);
		}

		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	private int getTimer(ItemStack stack) {
		CompoundNBT nbtTagCompound = stack.getTag();

		if (nbtTagCompound == null) return 0;

		return nbtTagCompound.getInt("freezeTimer");
	}

	private void setTimer(ItemStack stack, int n) {
		CompoundNBT nbtTagCompound = new CompoundNBT();
		nbtTagCompound.putInt("freezeTimer", n);
		stack.setTag(nbtTagCompound);
	}
}
