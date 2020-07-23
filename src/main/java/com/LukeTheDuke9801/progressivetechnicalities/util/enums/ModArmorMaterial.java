package com.LukeTheDuke9801.progressivetechnicalities.util.enums;

import java.util.function.Supplier;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ModArmorMaterial implements IArmorMaterial {
	   CARBIDE(ProgressiveTechnicalities.MOD_ID + ":carbide", 66, new int[]{4, 7, 9, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F, () -> {
		   return Ingredient.fromItems(ItemInit.CARBIDE_INGOT.get());}), 
	   OBSIDIAN(ProgressiveTechnicalities.MOD_ID + ":obsidian", 100000, new int[]{2, 6, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, () -> {
		   return Ingredient.fromItems(ItemInit.OBSIDIAN_INGOT.get());}),
	   FEYSTEEL(ProgressiveTechnicalities.MOD_ID + ":feysteel", 33, new int[]{3, 6, 8, 3}, 25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, () -> {
		   return Ingredient.fromItems(ItemInit.FEYSTEEL_INGOT.get());}),
	   STEEL(ProgressiveTechnicalities.MOD_ID + ":steel", 66, new int[]{5, 9, 11, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 5.0F, () -> {
		   return Ingredient.fromItems(ItemInit.STEEL_INGOT.get());}),
	   FIREGEM(ProgressiveTechnicalities.MOD_ID + ":firegem", 33, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, () -> {
		   return Ingredient.fromItems(ItemInit.FIRE_GEM.get());}),
	   SKYGEM(ProgressiveTechnicalities.MOD_ID + ":skygem", 20, new int[]{1, 5, 5, 1}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0, () -> {
		   return Ingredient.fromItems(ItemInit.SKY_GEM.get());}),
	   WATERGEM(ProgressiveTechnicalities.MOD_ID + ":watergem", 33, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, () -> {
		   return Ingredient.fromItems(ItemInit.WATER_GEM.get());}),
	   EARTHGEM(ProgressiveTechnicalities.MOD_ID + ":earthgem", 33, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F, () -> {
		   return Ingredient.fromItems(ItemInit.EARTH_GEM.get());});
	
	   private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	   private final String name;
	   private final int maxDamageFactor;
	   private final int[] damageReductionAmountArray;
	   private final int enchantability; 
	   private final SoundEvent soundEvent;
	   private final float toughness;
	   private final LazyValue<Ingredient> repairMaterial;

	   private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float toughnessIn, Supplier<Ingredient> repairMaterialSupplier) {
	      this.name = nameIn;
	      this.maxDamageFactor = maxDamageFactorIn;
	      this.damageReductionAmountArray = damageReductionAmountsIn;
	      this.enchantability = enchantabilityIn;
	      this.soundEvent = equipSoundIn;
	      this.toughness = toughnessIn;
	      this.repairMaterial = new LazyValue<>(repairMaterialSupplier);
	   }

	   public int getDurability(EquipmentSlotType slotIn) {
	      return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	   }

	   public int getDamageReductionAmount(EquipmentSlotType slotIn) {
	      return this.damageReductionAmountArray[slotIn.getIndex()];
	   }

	   public int getEnchantability() {
	      return this.enchantability;
	   }

	   public SoundEvent getSoundEvent() {
	      return this.soundEvent;
	   }

	   public Ingredient getRepairMaterial() {
	      return this.repairMaterial.getValue();
	   }

	   @OnlyIn(Dist.CLIENT)
	   public String getName() {
	      return this.name;
	   }

	   public float getToughness() {
	      return this.toughness;
	   }
}