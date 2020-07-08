package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class UnobtaniumSword extends SwordItem {
	public UnobtaniumSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Kills anything in one hit but also releases a destructive shockwave"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
	      target.onKillCommand();
	      
	      attacker.getEntityWorld().createExplosion((Entity)null, target.getPosX(), target.getPosY(), target.getPosZ(), 20f, false, Explosion.Mode.DESTROY);
			
	      return true;
	}
}