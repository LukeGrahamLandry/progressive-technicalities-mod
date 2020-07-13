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
public abstract class BasicSpecialArmorMaterial implements IArmorMaterial {

    @Override
    public int getDurability(EquipmentSlotType slotType) {
        return 300;
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
        return Ingredient.fromItems(ItemInit.BASIC_POTION_CORE.get());
    }
    
    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotType) {
        return 2;
    }

    @Override
    public float getToughness() {
        return 0;
    }
}