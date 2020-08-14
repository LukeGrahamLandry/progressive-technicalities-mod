package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.HitEventListener;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LongFallBoots extends ArmorItem implements HitEventListener {

	public LongFallBoots(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Grants immunity to fall damage"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		boolean isFallDamage = event.getSource() == DamageSource.FALL;
		if (isFallDamage) {
			event.setCanceled(true);
		}
	}

	public static class Material extends AdvancedSpecialArmorMaterial {
        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":special";
        }
	}
}
