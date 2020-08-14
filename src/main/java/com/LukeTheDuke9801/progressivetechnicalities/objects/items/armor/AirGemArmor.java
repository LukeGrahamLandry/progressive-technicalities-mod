package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModArmorMaterial;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.HitEventListener;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class AirGemArmor extends ArmorItem implements HitEventListener {
	private static final int maxFlightTime = 10*20;

	public AirGemArmor(EquipmentSlotType slot, Properties builder) {
		super(ModArmorMaterial.AIRGEM, slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Full set gives you flight (for 10 seconds, recharges while on ground). Blocks fall damage"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		int timer = getFlightTimer(stack);
		if (!player.abilities.isFlying && timer < maxFlightTime){
			setFlightTimer(stack, timer + 1);
		}
		if (player.abilities.isFlying && timer > 0){
			setFlightTimer(stack, timer - 1);
		}

		if (hasFullSet(player) && timer > 0) {
			player.abilities.allowFlying = true;
		} else {
			player.abilities.allowFlying = false;
			player.abilities.isFlying = false;
		}

		super.onArmorTick(stack, world, player);
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		boolean isFallDamage = event.getSource() == DamageSource.FALL;
		if (isFallDamage) {
			event.setCanceled(true);
		}
	}
	
	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.AIRGEM_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.AIRGEM_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.AIRGEM_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.AIRGEM_BOOTS.get());
	}

	private int getFlightTimer(ItemStack stack) {
		CompoundNBT nbtTagCompound = stack.getTag();

		if (nbtTagCompound == null) return 0;

		return nbtTagCompound.getInt("flightTimer");
	}

	private void setFlightTimer(ItemStack stack, int n) {
		CompoundNBT nbtTagCompound = new CompoundNBT();
		nbtTagCompound.putInt("flightTimer", n);
		stack.setTag(nbtTagCompound);
	}
}
