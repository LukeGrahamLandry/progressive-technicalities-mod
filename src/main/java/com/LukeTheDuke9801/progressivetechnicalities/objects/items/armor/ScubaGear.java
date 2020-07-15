package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ScubaGear extends ArmorItem{

	public ScubaGear(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (player.isInWater()) {
			player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 100, 0));
		}
		
		super.onArmorTick(stack, world, player);
	}
	

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Grants water breathing while in water"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public static class Material extends BasicSpecialArmorMaterial {
        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":scuba";
        }
    }
}
