package com.LukeTheDuke9801.progressivetechnicalities.objects.items.charms;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class MinersCharm extends Item{

	public MinersCharm(Properties properties) {
		super(properties);
	}
	
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		((PlayerEntity)entityIn).addPotionEffect(new EffectInstance(Effects.HASTE, 100, 0));
		
		BlockPos pos = entityIn.getPosition();
		int light = worldIn.getLight(pos);
		if (light < 7 && worldIn.getBlockState(pos).isAir(null, null) && worldIn.getBlockState(pos.down()).isSolid()) {
			worldIn.setBlockState(pos, Blocks.TORCH.getDefaultState());
		}
		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Grants haste I and placed torches at your feet in the dark"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
