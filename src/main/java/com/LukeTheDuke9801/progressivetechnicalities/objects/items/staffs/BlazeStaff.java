package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.entities.ThrowableFireballEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class BlazeStaff extends Item{
	public BlazeStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Throws fireballs. Shift right click to spawn a blaze"));
		} 
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		playerIn.getCooldownTracker().setCooldown(this, 20);

		if (KeyboardHelper.isHoldingShift()) {
			BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, worldIn);
			blaze.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
			worldIn.addEntity(blaze);
		} else {
			ThrowableFireballEntity ball = new ThrowableFireballEntity(worldIn, playerIn);
			ball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.addEntity(ball);
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
}
