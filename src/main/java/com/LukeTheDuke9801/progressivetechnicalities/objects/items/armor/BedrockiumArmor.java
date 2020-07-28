package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;
import java.util.function.Consumer;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class BedrockiumArmor extends ArmorItem {
	public BedrockiumArmor(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Makes you invincible but slow and weak"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!world.isRemote && hasFullSet(player)) {
			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 100, 4));
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 2));
			player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 2));
			player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 4));
			player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 100, 0));
		}
		super.onArmorTick(stack, world, player);
	}
	
	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		if (hasFullSet(entity)) {
			entity.getItemStackFromSlot(EquipmentSlotType.HEAD).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.CHEST).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.LEGS).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.FEET).setDamage(0);
			return 0;
		}
		return super.damageItem(stack, amount, entity, onBroken);
	}
	
	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.BEDROCKIUM_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.BEDROCKIUM_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.BEDROCKIUM_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.BEDROCKIUM_BOOTS.get());
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
	
	public static class Material implements IArmorMaterial {
		@Override
	    public SoundEvent getSoundEvent() {
	        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
	    }
		 
        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotType) {
            return 10;
        }

        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":bedrockium";
        }

        @Override
        public float getToughness() {
            return 10;
        }
        
        @Override
        public int getDurability(EquipmentSlotType slotType) {
            return 1000;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(ItemInit.BEDROCKIUM_INGOT.get());
        }
    }
}
