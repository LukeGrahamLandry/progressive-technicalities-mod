package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.entities.AbstractWanderer;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class WandererContract extends Item {
	private String type;
	public WandererContract(String typeIn, Properties properties) {
		super(properties);
		this.type = typeIn;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Summons a wondering " + this.type));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public ActionResultType onItemUse(ItemUseContext context) {
		if (context.getHand() == Hand.OFF_HAND) return ActionResultType.PASS;
		
		EntityType<? extends AbstractWanderer> entityType;
		switch (this.type) {
		case "gem smith":
			entityType = ModEntityTypes.WANDERING_GEM_SMITH.get();
			break;
		case "astronomer":
			entityType = ModEntityTypes.WANDERING_ASTRONOMER.get();
			break;
		default:
			throw new NullPointerException("INVALID WONDERING TRADER TYPE");
		}
		
		BlockPos spawnPos = context.getPos().offset(context.getFace());
		
		entityType.spawn(context.getWorld(), ItemStack.EMPTY, context.getPlayer(), spawnPos, SpawnReason.SPAWN_EGG, false, false);
		
		context.getPlayer().setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
		
		return ActionResultType.SUCCESS;
	}
	
}