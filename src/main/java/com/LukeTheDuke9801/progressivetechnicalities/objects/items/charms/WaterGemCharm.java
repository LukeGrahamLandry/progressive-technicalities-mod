package com.LukeTheDuke9801.progressivetechnicalities.objects.items.charms;

import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.WaterGemArmor;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class WaterGemCharm extends Item{

	public WaterGemCharm(Properties properties) {
		super(properties);
	}
	
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote) {
			LivingEntity player = (LivingEntity) entityIn;
			if (player.isInWater()) {
				player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 200, 0));
				player.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 200, 0));
				player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 220, 0));
				player.addPotionEffect(new EffectInstance(Effects.HASTE, 200, 2));
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 200, 2));
				player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 200, 2));
				player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 1));
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Grants water breathing, dolphins grace, nightvision, haste III, speed III, strength III and regen II while in water"));
		}
	}
	
}
