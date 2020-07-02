package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class MagicEggShell extends Item {
	public MagicEggShell(Properties properties) {
		super(properties);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Hit a creature to trap it in the egg"));
			tooltip.add(new StringTextComponent("Can be used to spawn the creature or set an empty powered spawner"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		if (entity instanceof LivingEntity) {
			player.setHeldItem(Hand.MAIN_HAND, new ItemStack(ItemInit.MOB_EGG.get()));
			MobEgg handHeld = (MobEgg)player.getHeldItem(Hand.MAIN_HAND).getItem();
			handHeld.setType(entity.getType());
			entity.onKillCommand();
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
}