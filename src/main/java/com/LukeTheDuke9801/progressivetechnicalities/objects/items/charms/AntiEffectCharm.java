package com.LukeTheDuke9801.progressivetechnicalities.objects.items.charms;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class AntiEffectCharm extends Item{
	private Effect effect;
	public AntiEffectCharm(Effect effectIn, Properties properties) {
		super(properties);
		this.effect = effectIn;
	}
	
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		((PlayerEntity)entityIn).removePotionEffect(this.effect);
		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Makes you immune to the " + this.effect.getDisplayName().getString() + " effect"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
