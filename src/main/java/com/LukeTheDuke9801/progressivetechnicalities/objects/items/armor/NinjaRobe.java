package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
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
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

public class NinjaRobe extends ArmorItem implements HitEventListener{
	private static final Random rand = new Random();

	public NinjaRobe(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("20% chance to dodge an attack"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		int chance = 20;
		if (rand.nextInt(100 ) < chance && !event.getSource().isUnblockable()){
			event.setCanceled(true);
		}
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return 0;
	}

	public static class Material extends BasicSpecialArmorMaterial {
		@Override
		public String getName() {
			return ProgressiveTechnicalities.MOD_ID + ":special";
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotType) {
			return 0;
		}
	}
}
