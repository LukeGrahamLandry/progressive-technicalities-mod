package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.ProjectileHitAction;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.SimpleProjectile;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Supplier;

public class ProjectileStaff extends Staff{
	private final Supplier<ProjectileHitAction> projectileHitActionSupplier;
	private final int cooldown;
	public ProjectileStaff(Supplier<ProjectileHitAction> projectileSupplierIn, int durability, int cooldownIn) {
		super(new Item.Properties().group(ProgressiveTechnicalities.ProgtechItemGroup.instance).maxDamage(durability));
		this.projectileHitActionSupplier = projectileSupplierIn;
		this.cooldown = cooldownIn;
	}

	@Override
	public int getBaseCooldown(ItemStack stack) {
		return this.cooldown;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Right click to shoot a " + this.projectileHitActionSupplier.get().getName()));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		shootProjectile(playerIn, stack);
		doDurabilityAndCooldown(stack, playerIn);
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	protected void shootProjectile(PlayerEntity player, ItemStack stack){
		int force = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.STAFF_FORCE.get(), stack);
		ProjectileItemEntity projectile = new SimpleProjectile(player.world, player, this.projectileHitActionSupplier.get(), force);
		shoot(projectile, player, player.rotationPitch, player.rotationYaw, 0, 1.5F, 1.0F);
		player.world.addEntity(projectile);
	}

	public void shoot(ProjectileItemEntity projectile, Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(rotationYawIn * ((float)Math.PI / 180F)) * MathHelper.cos(rotationPitchIn * ((float)Math.PI / 180F));
		float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * ((float)Math.PI / 180F));
		float f2 = MathHelper.cos(rotationYawIn * ((float)Math.PI / 180F)) * MathHelper.cos(rotationPitchIn * ((float)Math.PI / 180F));
		projectile.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
	}
}
