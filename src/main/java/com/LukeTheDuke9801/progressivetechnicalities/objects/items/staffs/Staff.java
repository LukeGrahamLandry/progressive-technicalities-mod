package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import com.LukeTheDuke9801.progressivetechnicalities.enchantments.StaffCooldownReductionEnchant;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import java.util.Random;

public interface Staff {
    // in ticks
    default int getBaseCooldown(ItemStack stack){
        return 0;
    }

    default void doDurabilityAndCooldown(ItemStack stack, PlayerEntity player){
        int cdr = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STAFF_COOLDOWN_REDUCTION.get(), stack);
        double cdrFactor = 1 - ((double)cdr / 10);  // I 90%, II 80%, III 70%
        int cooldown = (int) (getBaseCooldown(stack) * cdrFactor);
        player.getCooldownTracker().setCooldown(stack.getItem(), cooldown);

        stack.damageItem(1, player, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
    }
}
