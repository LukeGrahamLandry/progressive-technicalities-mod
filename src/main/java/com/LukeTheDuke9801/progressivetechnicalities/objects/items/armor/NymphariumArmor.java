package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.NymphariumFluidBlock;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class NymphariumArmor extends ArmorItem implements HitEventListener {
	public NymphariumArmor(EquipmentSlotType slot, Properties builder) {
		super(ModArmorMaterial.NYMPHARIUM, slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("While wearing full set, press shift to stop moving and nympharium fluid heals you"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (hasFullSet(player) && KeyboardHelper.isHoldingShift()){
			player.setMotion(0, 0, 0);
			player.fallDistance = 0.0F;
		}

		super.onArmorTick(stack, world, player);
	}
	
	
	
	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.NYMPHARIUM_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.NYMPHARIUM_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.NYMPHARIUM_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.NYMPHARIUM_BOOTS.get());
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		if (hasFullSet(event.getEntityLiving()) && event.getSource() == NymphariumFluidBlock.DAMAGE_SOURCE){
			event.getEntityLiving().heal(event.getAmount());
			event.setCanceled(true);
		}
	}
}
