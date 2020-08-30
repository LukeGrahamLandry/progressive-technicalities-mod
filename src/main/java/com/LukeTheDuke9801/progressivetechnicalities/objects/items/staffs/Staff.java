package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import com.LukeTheDuke9801.progressivetechnicalities.enchantments.StaffCooldownReductionEnchant;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class Staff extends Item {
    public Staff(Properties properties) {
        super(properties);
    }

    // in ticks
    protected int getBaseCooldown(ItemStack stack){
        return 0;
    }

    protected void doDurabilityAndCooldown(ItemStack stack, PlayerEntity player){
        int cdr = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STAFF_COOLDOWN_REDUCTION.get(), stack);
        double cdrFactor = 1 - ((double)cdr / 10);  // each level is 10% less cooldown (additive)
        int cooldown = (int) (getBaseCooldown(stack) * cdrFactor);
        player.getCooldownTracker().setCooldown(stack.getItem(), cooldown);

        stack.damageItem(1, player, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 20;
    }
}
