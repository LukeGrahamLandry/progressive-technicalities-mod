package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class JetPack2 extends ArmorItem{

	public JetPack2(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (KeyboardHelper.isHoldingSpace()) {
			player.setMotion(player.getMotion().getX(), Math.min(player.getMotion().getY() + 0.15D, 0.6D), player.getMotion().getZ());
			player.fallDistance = 0f;
		}
		
		super.onArmorTick(stack, world, player);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Allows slow upwards flight"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public static class Material extends BaseSpecialArmorMaterial {
        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotType) {
            return 0;
        }

        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":health_chestplate";
        }

        @Override
        public float getToughness() {
            return 0;
        }
    }
}
