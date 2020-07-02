package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.BridgingEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.LavaWalkerEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<Enchantment>(ForgeRegistries.ENCHANTMENTS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Enchantment> LAVA_WALKER = ENCHANTMENTS.register("lava_walker_enchant",
			() -> new LavaWalkerEnchantment(Rarity.VERY_RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] {EquipmentSlotType.FEET}));
		
	public static final RegistryObject<Enchantment> BRIDGING_ENCHANT = ENCHANTMENTS.register("bridging_enchant",
			() -> new BridgingEnchantment(Rarity.COMMON, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[] {EquipmentSlotType.FEET}));
		
}
