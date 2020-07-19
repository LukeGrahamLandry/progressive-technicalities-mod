package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.entities.FlamingArrowEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class FireBowItem extends ModBowItem{
	public FireBowItem(Item.Properties builder) {
	 	super(2, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Ignite your foes for 10 seconds (and deals an extra heart)"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	protected AbstractArrowEntity getArrowEntity(World worldIn, LivingEntity shooter, ItemStack stack, ArrowItem arrow) {
		FlamingArrowEntity arrowentity = new FlamingArrowEntity(worldIn, shooter);
        arrowentity.setPotionEffect(stack);
        return (AbstractArrowEntity)arrowentity;
	}
	
}
