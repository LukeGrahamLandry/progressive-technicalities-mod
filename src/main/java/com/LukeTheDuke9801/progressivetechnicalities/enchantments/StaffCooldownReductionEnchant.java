package com.LukeTheDuke9801.progressivetechnicalities.enchantments;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

public class StaffCooldownReductionEnchant extends Enchantment{
	public StaffCooldownReductionEnchant(Rarity rarityIn, EquipmentSlotType[] slots) {
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
	      return 5;
	   }
}
