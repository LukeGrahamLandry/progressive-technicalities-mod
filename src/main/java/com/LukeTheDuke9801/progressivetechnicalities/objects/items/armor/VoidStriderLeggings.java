package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.HitEventListener;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class VoidStriderLeggings extends ArmorItem implements HitEventListener {

	public VoidStriderLeggings(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Grants immunity to to void damage, jetpack flight, slow falling and night vision while under y=0"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void onWearerHit(LivingHurtEvent event) {
		LivingEntity entity = event.getEntityLiving();
		boolean belowBedrock = entity.getPosY() <= 0;
		boolean isVoidDamage = event.getSource() == DamageSource.OUT_OF_WORLD;
		if (belowBedrock && isVoidDamage) {
			event.setCanceled(true);
		}
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		boolean belowBedrock = player.getPosY() <= 0;
		
		if (belowBedrock) {
			setTimer(stack, 100);
			player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 220, 0));
			player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 40, 0));
		}
		
		if (getTimer(stack) > 0) {
			if (KeyboardHelper.isHoldingSpace()) {
				player.setMotion(player.getMotion().getX(), Math.min(player.getMotion().getY() + 0.2D, 1D), player.getMotion().getZ());
				player.fallDistance = 0f;
			}
			
			setTimer(stack, getTimer(stack)-1);
		}
		
		super.onArmorTick(stack, world, player);
	}
	
	private int getTimer(ItemStack stack) {
		CompoundNBT nbtTagCompound = stack.getTag();
		
		if (nbtTagCompound == null || !nbtTagCompound.contains("timer")) {
			return 0;
		}
		
		int timer = nbtTagCompound.getInt("timer");
		return timer;
	}
	
	private void setTimer(ItemStack stack, int timer) {
		CompoundNBT nbtTagCompound = new CompoundNBT();
		nbtTagCompound.putInt("timer", timer);
		stack.setTag(nbtTagCompound);
	}
	
	public static class Material extends AdvancedSpecialArmorMaterial {
        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":special";
        }
    }
}
