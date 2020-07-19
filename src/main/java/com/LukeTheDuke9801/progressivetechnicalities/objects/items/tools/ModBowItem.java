package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

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
import net.minecraft.world.World;

public class ModBowItem extends BowItem{
	private int extraDamage;
	public ModBowItem(int extraDamageIn, Item.Properties builder) {
	 	super(builder);
	 	this.extraDamage = extraDamageIn;
	}
	
	protected AbstractArrowEntity getArrowEntity(World worldIn, LivingEntity shooter, ItemStack stack, ArrowItem arrow) {
		return (AbstractArrowEntity) arrow.createArrow(worldIn, stack, shooter);
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
	      if (entityLiving instanceof PlayerEntity) {
	         PlayerEntity playerentity = (PlayerEntity)entityLiving;
	         boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
	         ItemStack itemstack = playerentity.findAmmo(stack);

	         int i = this.getUseDuration(stack) - timeLeft;
	         i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
	         if (i < 0) return;

	         if (!itemstack.isEmpty() || flag) {
	            if (itemstack.isEmpty()) {
	               itemstack = new ItemStack(Items.ARROW);
	            }

	            float f = getArrowVelocity(i);
	            if (!((double)f < 0.1D)) {
	               boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
	               if (!worldIn.isRemote) {
	                  ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
	                  
	                  // this is the part I changed from normal bows
	                  AbstractArrowEntity abstractarrowentity = this.getArrowEntity(worldIn, playerentity, itemstack, arrowitem);
	                  
	                  abstractarrowentity = customeArrow(abstractarrowentity);
	                  abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
	                  if (f == 1.0F) {
	                     abstractarrowentity.setIsCritical(true);
	                  }

	                  int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
	                  // alos changed this
	                  if (j > 0) {
	                     abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D + this.extraDamage);
	                  } else {
	                	  abstractarrowentity.setDamage(abstractarrowentity.getDamage() + this.extraDamage);
	                  }

	                  int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
	                  if (k > 0) {
	                     abstractarrowentity.setKnockbackStrength(k);
	                  }

	                  if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
	                     abstractarrowentity.setFire(100);
	                  }

	                  stack.damageItem(1, playerentity, (p_220009_1_) -> {
	                     p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
	                  });
	                  if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
	                     abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
	                  }

	                  worldIn.addEntity(abstractarrowentity);
	               }

	               worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	               if (!flag1 && !playerentity.abilities.isCreativeMode) {
	                  itemstack.shrink(1);
	                  if (itemstack.isEmpty()) {
	                     playerentity.inventory.deleteStack(itemstack);
	                  }
	               }

	               playerentity.addStat(Stats.ITEM_USED.get(this));
	            }
	         }
	      }
	   }
}
