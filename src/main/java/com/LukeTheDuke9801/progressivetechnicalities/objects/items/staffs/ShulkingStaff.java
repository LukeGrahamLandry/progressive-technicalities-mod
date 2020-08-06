package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ShulkingStaff extends Item{
	public ShulkingStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Shoots shulker bullets (fails if no monsters within 32 blocks). Also has a levitating melee attack"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addPotionEffect(new EffectInstance(Effects.LEVITATION, 40, 0));
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		playerIn.getCooldownTracker().setCooldown(this, 20);

		List<Entity> entities = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, getAABB(playerIn));
		NonNullList<MonsterEntity> targets = NonNullList.create();
		for (Entity e : entities) {
			if (e instanceof MonsterEntity) {
				targets.add((MonsterEntity) e);
				
			}
		}
		
		if (targets.size() > 0) {
			LivingEntity target = worldIn.getClosestEntity(targets, EntityPredicate.DEFAULT.setDistance(20), null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
			ShulkerBulletEntity bullet = new ShulkerBulletEntity(worldIn, playerIn, target, Direction.Axis.Y);
			worldIn.addEntity(bullet);
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
	
	private AxisAlignedBB getAABB(PlayerEntity player) {
		int range = 32;
		double x = player.getPosX();
		double y = player.getPosY();
		double z = player.getPosZ();
		return new AxisAlignedBB(x-range,y-range,z-range,x+range,y+range,z+range);
	}
}
