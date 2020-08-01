package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

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
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

// Blocks fall damage and knock back

public class SkyGuardianHelm extends ArmorItem implements HitEventListener{
	public SkyGuardianHelm(EquipmentSlotType slot, Properties builder) {
		super(ModArmorMaterial.AIRGEM, slot, builder);
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		boolean isFallDamage = event.getSource() == DamageSource.FALL;
		if (isFallDamage) {
			event.setCanceled(true);
		}
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		stack.setDamage(0);
		return 0;
	}
	
	@Nonnull
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
		UUID uuid = new UUID((getTranslationKey(stack) + slot.toString()).hashCode(), 0);
		if (slot == getEquipmentSlot()) {
			multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(),
					new AttributeModifier(uuid, "progtech:sky_guardian_helm", 1, AttributeModifier.Operation.ADDITION));
		}
		return multimap;
	}


}
