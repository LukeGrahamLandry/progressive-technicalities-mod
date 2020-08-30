package com.LukeTheDuke9801.progressivetechnicalities.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class StaffForceEnchant extends Enchantment{
	public StaffForceEnchant(Rarity rarityIn, EquipmentSlotType[] slots) {
		super(rarityIn, ModEnchantmentTypes.STAFF, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	@Override
	public int getMinLevel() {
		return 1;
	}
	
	public boolean isTreasureEnchantment() {
      return false;
	}
	
	public boolean isAllowedOnBooks() {
      return true;
	}
	
	public int getMinEnchantability(int enchantmentLevel) {
	      return 10;
	   }
}
