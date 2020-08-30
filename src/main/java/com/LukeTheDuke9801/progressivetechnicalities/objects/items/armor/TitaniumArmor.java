package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;

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

public class TitaniumArmor extends ArmorItem {
	int tick = 0;

	public TitaniumArmor(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Protection of iron but full set gives strength X, regen I, saturation I, speed V, jump boost III and haste III"));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!world.isRemote && hasFullSet(player)) {
			player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 100, 9));
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 100, 4));
			player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 100, 2));
			player.addPotionEffect(new EffectInstance(Effects.HASTE, 100, 2));
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 0));
			player.addPotionEffect(new EffectInstance(Effects.SATURATION, 100, 0));
		}
		
		super.onArmorTick(stack, world, player);
	}
	
	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.TITANIUM_ARMOR_SET.HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.TITANIUM_ARMOR_SET.CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.TITANIUM_ARMOR_SET.LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.TITANIUM_ARMOR_SET.BOOTS.get());
	}
	
	public static class Material implements IArmorMaterial {
		@Override
	    public SoundEvent getSoundEvent() {
	        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
	    }
		 
        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotType) {
            return 4;
        }

        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":titanium";
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
            return 50;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(ItemInit.TITANIUM_INGOT.get());
        }
    }
}
