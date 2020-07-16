package com.LukeTheDuke9801.progressivetechnicalities.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class SoulBoundEnchantment extends Enchantment{
	public SoulBoundEnchantment(Rarity rarityIn, EnchantmentType enchantmentType, EquipmentSlotType[] slots) {
		super(rarityIn, enchantmentType, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public int getMinLevel() {
		return 1;
	}
	
	public boolean isTreasureEnchantment() {
      return true;
	}
	
	public boolean isAllowedOnBooks() {
      return false;
	}
	
	public int getMinEnchantability(int enchantmentLevel) {
	      return 999;
	   }
	
	public static boolean hasSoulBound(ItemStack stack) {
    	ListNBT enchants = stack.getEnchantmentTagList();
		for (int i=0;i<enchants.size();i++) {
			CompoundNBT enchant = enchants.getCompound(i);
		    String id = enchant.getString("id");
		    Enchantment enchantment = Registry.ENCHANTMENT.getValue(new ResourceLocation(id)).get();
		    if (enchantment instanceof SoulBoundEnchantment) {
		    	return true;
		    }
		}
		return false;
    }
}
