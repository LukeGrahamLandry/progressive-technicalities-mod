package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.TorchArrowEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ModBow extends BowItem {
	public ModBow(Properties builder) {
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
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)entityLiving;

			ItemStack ammoStack = findAmmo(player, stack);
			boolean requiresAmmo = !hasInfinity(player, stack, ammoStack);
			// No ammo
			if (requiresAmmo && ammoStack == null) return;

			int timeUsed = this.getUseDuration(stack) - timeLeft;
			timeUsed = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, player, timeUsed, true);
			if (timeUsed < 0) return;
			float velocity = getArrowVelocity(timeUsed);
			if ((double)velocity < 0.1D) return;

			AbstractArrowEntity arrowEntity = getArrowEntity(worldIn, player, stack, ammoStack);
			arrowEntity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
			if (velocity == 1.0F) arrowEntity.setIsCritical(true);  // just effects particles
			arrowEntity.setDamage(calcDamage(stack));
			arrowEntity.setKnockbackStrength(calcKnockback(stack));
			arrowEntity.setFire(calcFireTime(stack));
			worldIn.addEntity(arrowEntity);

			// reduce durability
			stack.damageItem(1, player, (p_220009_1_) -> {
				p_220009_1_.sendBreakAnimation(player.getActiveHand());
			});

			// use ammo
			if(!hasInfinity(player, stack, ammoStack)){
				ammoStack.shrink(1);
			}

			player.addStat(Stats.ITEM_USED.get(this));
		}
	}

	protected AbstractArrowEntity getArrowEntity(World world, PlayerEntity player, ItemStack bowStack, ItemStack ammoStack){
		return ((ArrowItem)ammoStack.getItem()).createArrow(world, ammoStack, player);
	}

	protected double calcDamage(ItemStack bowStack){
		int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);
		double baseDamage = 2.0D;
		return baseDamage + (double)powerLevel * 0.5D + 0.5D;
	}

	protected int calcKnockback(ItemStack bowStack){
		return EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bowStack);
	}

	protected int calcFireTime(ItemStack bowStack){
		int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack);
		return flameLevel * 100;
	}

	@Nullable
	protected ItemStack findAmmo(PlayerEntity player, ItemStack bowStack){
		return player.findAmmo(bowStack);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		ItemStack ammoStack = findAmmo(playerIn, itemstack);

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, ammoStack != null);
		if (ret != null) return ret;

		if (!hasInfinity(playerIn, itemstack, ammoStack) && ammoStack == null) {
			return ActionResult.resultFail(itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return ActionResult.resultConsume(itemstack);
		}
	}

	protected boolean hasInfinity(PlayerEntity player, ItemStack bowStack, @Nullable ItemStack ammoStack){
		boolean infinity;
		if (ammoStack != null && ammoStack.getItem() instanceof ArrowItem){
			infinity = (ammoStack.getItem() instanceof ArrowItem && ((ArrowItem)ammoStack.getItem()).isInfinite(ammoStack, bowStack, player));
		} else {
			infinity = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bowStack) > 0;
		}
		return player.abilities.isCreativeMode || infinity;
	}


	
}
