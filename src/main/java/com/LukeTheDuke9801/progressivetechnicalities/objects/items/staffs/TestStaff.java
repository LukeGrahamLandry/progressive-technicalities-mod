package com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs;

import java.util.List;
import java.util.UUID;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TestStaff extends Item{
	public TestStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("for testing things"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerIn, Hand handIn){
		AttributeModifier modifier = new AttributeModifier(UUID.fromString("131fa735-ff9a-45c8-8197-fa8e561829e1"),
				"ProgTechScalingMobs_health", 5, AttributeModifier.Operation.ADDITION);
		playerIn.getAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(modifier);

		return super.onItemRightClick(world, playerIn, handIn);
		
	}
}
