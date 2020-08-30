package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModArmorMaterial;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.HitEventListener;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class FeySteelArmorItem extends ArmorItem implements HitEventListener {

	public FeySteelArmorItem(EquipmentSlotType slot, Properties builder) {
		super(ModArmorMaterial.FEYSTEEL, slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Protection of diamond. Thorns effect deals 1 heart per piece"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		Entity trueSource = event.getSource().getTrueSource();
		if (trueSource instanceof LivingEntity) {
			((LivingEntity)trueSource).attackEntityFrom(DamageSource.causeThornsDamage(event.getEntity()), 2);
		}
	}
}
