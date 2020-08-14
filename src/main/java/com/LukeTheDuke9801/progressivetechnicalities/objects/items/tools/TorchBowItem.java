package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.TorchArrowEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TorchBowItem extends ModBow {
	public TorchBowItem(Properties builder) {
	 	super(builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Shoot torches"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	protected AbstractArrowEntity getArrowEntity(World world, PlayerEntity player, ItemStack bowStack, ItemStack ammoStack){
		return new TorchArrowEntity(world, player);
	}

	@Override
	protected double calcDamage(ItemStack bowStack){
		int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);
		return (double)powerLevel * 0.5D;
	}

	@Override
	protected int calcFireTime(ItemStack bowStack){
		int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack);
		return (flameLevel + 1) * 100;
	}

	@Nullable
	@Override
	protected ItemStack findAmmo(PlayerEntity player, ItemStack bowStack){
		for (ItemStack stack : player.inventory.mainInventory){
			if (stack.getItem().equals(Items.TORCH)){
				return stack;
			}
		}
		return null;
	}
}
