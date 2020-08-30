package com.LukeTheDuke9801.progressivetechnicalities.enchantments;

import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.Staff;
import net.minecraft.enchantment.EnchantmentType;

public class ModEnchantmentTypes {
    public static EnchantmentType STAFF = EnchantmentType.create("staff", (item) -> {
        return item instanceof Staff;
    });
}
