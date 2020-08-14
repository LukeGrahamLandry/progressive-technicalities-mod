package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.MultiArrowEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class MultiShotBow extends ModBow {
	public MultiShotBow(Properties builder) {
	 	super(builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Shoots five arrows"));
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

			shootAnArrow(worldIn, player, stack, ammoStack, velocity, 0, 0);
			shootAnArrow(worldIn, player, stack, ammoStack, velocity, 5, 0);
			shootAnArrow(worldIn, player, stack, ammoStack, velocity, 0, 5);
			shootAnArrow(worldIn, player, stack, ammoStack, velocity, -5, 0);
			shootAnArrow(worldIn, player, stack, ammoStack, velocity, 0, -5);

			// reduce durability
			stack.damageItem(1, player, (p_220009_1_) -> {
				p_220009_1_.sendBreakAnimation(player.getActiveHand());
			});

			// use ammo
			if(!hasInfinity(player, stack, ammoStack)){
				ammoStack.shrink(5);
			}

			player.addStat(Stats.ITEM_USED.get(this));
		}
	}

	private void shootAnArrow(World worldIn, PlayerEntity player, ItemStack stack, ItemStack ammoStack, float velocity, int pitchOffset, int yawOffset){
		AbstractArrowEntity arrowEntity = getArrowEntity(worldIn, player, stack, ammoStack);
		arrowEntity.shoot(player, player.rotationPitch + pitchOffset, player.rotationYaw + yawOffset, 0.0F, velocity * 3.0F, 1.0F);
		if (velocity == 1.0F) arrowEntity.setIsCritical(true);  // just effects particles
		arrowEntity.setDamage(calcDamage(stack));
		arrowEntity.setKnockbackStrength(calcKnockback(stack));
		arrowEntity.setFire(calcFireTime(stack));
		worldIn.addEntity(arrowEntity);
	}

	protected AbstractArrowEntity getArrowEntity(World world, PlayerEntity player, ItemStack bowStack, ItemStack ammoStack){
		return new MultiArrowEntity(world, player);
	}
}
