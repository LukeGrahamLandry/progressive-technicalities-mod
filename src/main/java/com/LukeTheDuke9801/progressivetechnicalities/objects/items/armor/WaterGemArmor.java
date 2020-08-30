package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModArmorMaterial;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class WaterGemArmor extends ArmorItem {
	int tick = 0;

	public WaterGemArmor(EquipmentSlotType slot, Properties builder) {
		super(ModArmorMaterial.WATERGEM, slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Full set gives water breathing, dolphins grace, nightvision, haste III, speed III, strength III and regen II while in water"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!world.isRemote) {
			this.tick++;
			if (this.tick == 20) {
				this.tick = 0;
				
				if (hasFullSet(player) && player.isInWater()) {
					player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 200, 0));
					player.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 200, 0));
					player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 220, 0));
					player.addPotionEffect(new EffectInstance(Effects.HASTE, 200, 2));
					player.addPotionEffect(new EffectInstance(Effects.SPEED, 200, 2));
					player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 200, 2));
					player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 1));
				}
			}
		}
		
		super.onArmorTick(stack, world, player);
	}
	
	
	
	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.WATERGEM_ARMOR_SET.HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.WATERGEM_ARMOR_SET.CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.WATERGEM_ARMOR_SET.LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.WATERGEM_ARMOR_SET.BOOTS.get());
	}
}
