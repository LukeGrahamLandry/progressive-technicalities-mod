package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.FlamingArrowEntity;
import com.LukeTheDuke9801.progressivetechnicalities.entities.TorchArrowEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TorchBowItem extends ModBowItem{
	public TorchBowItem(Properties builder) {
	 	super(0, builder);
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

			boolean hasInfinity = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;

			ItemStack ammoStack = findAmmo(player);
			// No ammo
			if (!hasInfinity && ammoStack == null) return;

			int timeUsed = this.getUseDuration(stack) - timeLeft;
			timeUsed = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, player, timeUsed, true);
			if (timeUsed < 0) return;
			float velocity = getArrowVelocity(timeUsed);
			if ((double)velocity < 0.1D) return;

			AbstractArrowEntity abstractarrowentity = new TorchArrowEntity(worldIn, player);
			abstractarrowentity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
			if (velocity == 1.0F) abstractarrowentity.setIsCritical(true);
			int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
			abstractarrowentity.setDamage(powerLevel);
			int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
			if (k > 0) abstractarrowentity.setKnockbackStrength(k);
			int flameLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack);  // 0 or 1
			abstractarrowentity.setFire((flameLevel+1) * 100);
			worldIn.addEntity(abstractarrowentity);

			stack.damageItem(1, player, (p_220009_1_) -> {
				p_220009_1_.sendBreakAnimation(player.getActiveHand());
			});

			if(!hasInfinity){
				ammoStack.shrink(1);
			}

			player.addStat(Stats.ITEM_USED.get(this));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean hasInfinity = playerIn.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemstack) > 0;

		ItemStack ammoStack = findAmmo(playerIn);


		boolean flag = ammoStack != null;

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
		if (ret != null) return ret;

		if (!hasInfinity && ammoStack == null) {
			return ActionResult.resultFail(itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return ActionResult.resultConsume(itemstack);
		}
	}

	@Nullable
	private ItemStack findAmmo(PlayerEntity player){
		for (ItemStack stack : player.inventory.mainInventory){
			if (stack.getItem().equals(Items.TORCH)){
				return stack;
			}
		}
		return null;
	}


	
}
