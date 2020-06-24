package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class SnowStaff extends Item{
	public SnowStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Throws snowballs"));
			tooltip.add(new StringTextComponent("Shift right click to spawn a snow golem"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		if (KeyboardHelper.isHoldingShift()) {
			SnowGolemEntity golem = new SnowGolemEntity(EntityType.SNOW_GOLEM, worldIn);
			golem.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
			worldIn.addEntity(golem);
		} else {
			SnowballEntity ball = new SnowballEntity(worldIn, playerIn);
			ball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.addEntity(ball);
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
}
