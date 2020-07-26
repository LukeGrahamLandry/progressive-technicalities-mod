package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;
import java.util.function.Consumer;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModArmorMaterial;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class FireGemArmor extends ArmorItem implements HitEventListener{
	public FireGemArmor(EquipmentSlotType slot, Properties builder) {
		super(ModArmorMaterial.FIREGEM, slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Full set gives fire resistance. Ignites attackers 1 second per piece"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		Entity trueSource = event.getSource().getTrueSource();
		if (trueSource instanceof LivingEntity) {
			int newFireTime = ((LivingEntity)trueSource).getFireTimer() + 25;
			if (newFireTime > 5*20) return;
			((LivingEntity)trueSource).setFireTimer(newFireTime);
		}
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!world.isRemote && hasFullSet(player)) {
			player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 100, 0));
	   }
		
		super.onArmorTick(stack, world, player);
	}
	
	
	
	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.FIREGEM_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.FIREGEM_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.FIREGEM_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.FIREGEM_BOOTS.get());
	}
}
