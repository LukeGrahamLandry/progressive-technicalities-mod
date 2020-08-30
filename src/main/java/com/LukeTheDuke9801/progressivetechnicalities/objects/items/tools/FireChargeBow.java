package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.Fireball;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.SimpleProjectile;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FireChargeBow extends ModBow {
	public FireChargeBow(Properties builder) {
	 	super(builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Shoots fire balls"));
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

			SimpleProjectile arrowEntity = new SimpleProjectile(worldIn, player, new Fireball(), 0);
			arrowEntity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
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

	@Nullable
	@Override
	protected ItemStack findAmmo(PlayerEntity player, ItemStack bowStack){
		for (ItemStack stack : player.inventory.mainInventory){
			if (stack.getItem().equals(Items.FIRE_CHARGE)){
				return stack;
			}
		}
		return null;
	}
}
