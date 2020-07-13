package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import javax.annotation.ParametersAreNonnullByDefault;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class AdvancedSpecialArmorMaterial implements IArmorMaterial {

    @Override
    public int getDurability(EquipmentSlotType slotType) {
        return 600;
    }

    @Override
    public int getEnchantability() {
        return 25;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(ItemInit.POTION_CORE.get());
    }
    
    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotType) {
    	if (slotType == EquipmentSlotType.CHEST) {
    		return 8;
    	} else if (slotType == EquipmentSlotType.LEGS){
    		return 6;
    	} else {
    		return 3;
    	}
    }

    @Override
    public float getToughness() {
        return 0;
    }
}