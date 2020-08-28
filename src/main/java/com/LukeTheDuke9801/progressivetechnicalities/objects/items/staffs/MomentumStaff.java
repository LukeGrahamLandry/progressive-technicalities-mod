package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class MomentumStaff extends Item{
	public MomentumStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Rightclick to boost you in the direction you're lookinge"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		playerIn.getCooldownTracker().setCooldown(this, 5);

		double vecLen = 2;
		EffectInstance speedEffect = playerIn.getActivePotionEffect(Effects.SPEED);
		if (speedEffect != null) {
			vecLen += speedEffect.getAmplifier();
		}
		
		Vec3d vec = playerIn.getLookVec().scale(vecLen);
		playerIn.addVelocity(vec.getX(), vec.getY(), vec.getZ());
		
		playerIn.isAirBorne = true;
		playerIn.fallDistance = 0f;
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
	
}
