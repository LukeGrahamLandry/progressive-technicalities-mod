package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class FireBowItem extends ModBow {
	public FireBowItem(Item.Properties builder) {
	 	super(builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Ignite your foes for 10 seconds (and deals an extra heart)"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	protected double calcDamage(ItemStack bowStack){
		return super.calcDamage(bowStack) + 2;
	}

	@Override
	protected int calcFireTime(ItemStack bowStack){
		int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack);
		return (flameLevel + 2) * 100;
	}
}
