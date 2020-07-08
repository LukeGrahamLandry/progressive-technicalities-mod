package com.LukeTheDuke9801.progressivetechnicalities.util.enums;

import java.util.function.Supplier;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;

import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ModItemTier implements IItemTier{
	// tier, durability, efficency, attack damage, enchantability, repair material
	CARBIDE(4, 3000, 15.0f, 4.0f, 30, () -> {
		return Ingredient.fromItems(ItemInit.CARBIDE_INGOT.get()); 
	}),
	TITANIUM(4, 3000, 15.0f, 4.0f, 30, () -> {
		return Ingredient.fromItems(ItemInit.TITANIUM_INGOT.get()); 
	}),
	OBSIDIAN(2, 1000000, 6.0F, 2.0F, 25, () -> {
		return Ingredient.fromItems(ItemInit.OBSIDIAN_INGOT.get()); 
	}),
	UNOBTANIUM(4, 3000, 300.0f, 0f, 0, () -> {
		return Ingredient.fromItems(ItemInit.UNOBTANIUM_INGOT.get()); 
	});
	
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;
	
	private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairMaterial = new LazyValue<>(repairMaterial);
	}
	
	@Override
	public int getHarvestLevel(){
		return harvestLevel;
	}
	@Override
	public int getMaxUses(){
		return this.maxUses;
	}
	@Override
	public float getEfficiency(){
		return this.efficiency;
	}
	@Override
	public float getAttackDamage(){
		return this.attackDamage;
	}
	@Override
	public int getEnchantability(){
		return this.enchantability;
	}
	@Override
	public Ingredient getRepairMaterial(){
		return this.repairMaterial.getValue();
	}
	
}
