package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.entities.ShulkerArrowEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ShulkerBowItem extends ModBowItem{
	public ShulkerBowItem(Item.Properties builder) {
	 	super(0, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Send your foes to the sky (for 2 seconds)"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	protected AbstractArrowEntity getArrowEntity(World worldIn, LivingEntity shooter, ItemStack stack, ArrowItem arrow) {
		ShulkerArrowEntity arrowentity = new ShulkerArrowEntity(worldIn, shooter);
        arrowentity.setPotionEffect(stack);
        return (AbstractArrowEntity)arrowentity;
	}
	
}
