package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModArmorMaterial;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EarthGemArmor extends ArmorItem implements HitEventListener{
	int tick = 0;

	public EarthGemArmor(EquipmentSlotType slot, Properties builder) {
		super(ModArmorMaterial.EARTHGEM, slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Full set gives haste I, knockback resistance. Gives attackers slowness II for 2 seconds per piece"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		Entity trueSource = event.getSource().getTrueSource();
		if (trueSource instanceof LivingEntity) {
			LivingEntity source = (LivingEntity)trueSource;
			EffectInstance slow = source.getActivePotionEffect(Effects.SLOWNESS);

			int newSlowTime;
			if (slow == null || slow.getAmplifier() < 1){
				newSlowTime = 40;
			} else if (slow.getAmplifier() == 1){
				newSlowTime = slow.getDuration() + 40;
				if (newSlowTime > 160) return;
			} else {
				return;
			}

			source.addPotionEffect(new EffectInstance(Effects.SLOWNESS, newSlowTime, 1));
		}
	}
	
	@Nonnull
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
		UUID uuid = new UUID((getTranslationKey(stack) + slot.toString()).hashCode(), 0);
		if (slot == getEquipmentSlot()) {
			int reduction = material.getDamageReductionAmount(slot);
			multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(),
					new AttributeModifier(uuid, "progtech:earth_armor", (double) reduction / 20, AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!world.isRemote && hasFullSet(player)) {
			player.addPotionEffect(new EffectInstance(Effects.HASTE, 200, 0));
		}
		
		super.onArmorTick(stack, world, player);
	}
	
	
	
	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.EARTHGEM_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.EARTHGEM_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.EARTHGEM_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.EARTHGEM_BOOTS.get());
	}
}
