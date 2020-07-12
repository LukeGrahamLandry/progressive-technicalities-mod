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
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class BedrockiumArmor extends ArmorItem {
	int tick = 0;

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
		this.tick++;
		if (this.tick == 300) {
			this.tick = 0;
			
			if (!hasBedrockiumArmor(player)) {
				return;
			}
			
			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 500, 4));
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 500, 2));
			player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 500, 2));
			player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 500, 4));
			player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 500, 0));
		}
		
		super.onArmorTick(stack, world, player);
	}
	
	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		if (hasBedrockiumArmor(entity)) {
			entity.getItemStackFromSlot(EquipmentSlotType.HEAD).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.CHEST).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.LEGS).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.FEET).setDamage(0);
			return 0;
		}
		return super.damageItem(stack, amount, entity, onBroken);
	}
	
	public static boolean hasBedrockiumArmor(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.BEDROCKIUM_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.BEDROCKIUM_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.BEDROCKIUM_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.BEDROCKIUM_BOOTS.get());
	}
	
	public static class Material extends BaseSpecialArmorMaterial {
        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotType) {
            return 10;
        }

        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":special";
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
