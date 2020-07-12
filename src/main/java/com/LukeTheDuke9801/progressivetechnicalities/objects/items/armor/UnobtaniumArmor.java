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

public class UnobtaniumArmor extends ArmorItem {
	int tick = 0;

	public UnobtaniumArmor(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Protection of iron but gives strength X, regen I, saturation I, speed V, jump boost III and haste III while wearing the full set"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		this.tick++;
		if (this.tick == 300) {
			this.tick = 0;
			
			if (!hasUnobtaniumArmor(player)) {
				return;
			}
			
			player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 500, 9));
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 500, 4));
			player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 500, 2));
			player.addPotionEffect(new EffectInstance(Effects.HASTE, 500, 2));
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 500, 0));
			player.addPotionEffect(new EffectInstance(Effects.SATURATION, 500, 0));
		}
		
		super.onArmorTick(stack, world, player);
	}
	
	public static boolean hasUnobtaniumArmor(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.UNOBTANIUM_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.UNOBTANIUM_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.UNOBTANIUM_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.UNOBTANIUM_BOOTS.get());
	}
	
	public static class Material extends BaseSpecialArmorMaterial {
        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotType) {
            return 4;
        }

        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":special";
        }

        @Override
        public float getToughness() {
            return 4;
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
            return Ingredient.fromItems(ItemInit.UNOBTANIUM_INGOT.get());
        }
    }
}
