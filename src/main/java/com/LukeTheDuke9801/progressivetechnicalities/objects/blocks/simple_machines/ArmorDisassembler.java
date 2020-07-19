package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.JetPack1;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.JetPack2;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools.TitaniumAIOT;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class ArmorDisassembler extends SimpleMachineBlock{
	private static final int cost = 20;
	
	public ArmorDisassembler(Block.Properties builder) {
	      super(builder);
	   }
	   
	   
	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   ItemStack handHeld = player.getHeldItem(handIn);
		   if (!worldIn.isRemote && handHeld.getItem() instanceof ArmorItem) {
			   
			   int baseMaterialAmount = 0;
			   boolean isSpecialArmour = false;
			   
			   EquipmentSlotType slotType =  ((ArmorItem) handHeld.getItem()).getEquipmentSlot();

			   if (slotType == EquipmentSlotType.HEAD) {
				   baseMaterialAmount = 5;
			   } else if (slotType == EquipmentSlotType.CHEST) {
				   baseMaterialAmount = 8;
			   } else if (slotType == EquipmentSlotType.LEGS) {
				   baseMaterialAmount = 7;
			   } else if (slotType == EquipmentSlotType.FEET) {
				   baseMaterialAmount = 4;
			   } else {
				   return ActionResultType.FAIL;
			   }
			   
			   if (!handHeld.isRepairable()) {
				   return ActionResultType.FAIL;
			   }
			   
			   if (handHeld.getItem() instanceof JetPack1 || handHeld.getItem() instanceof JetPack2){
				   return ActionResultType.FAIL;
			   }
			   
			   double maxDurability = handHeld.getItem().getMaxDamage(handHeld);
			   double durability = maxDurability - handHeld.getItem().getDamage(handHeld);
			   double multiplier = durability / maxDurability;
			   
			   Item resultItem;
			   int number = (int)(baseMaterialAmount * multiplier);
			   if (((ArmorItem) handHeld.getItem()).getArmorMaterial() == ArmorMaterial.CHAIN) {
				   resultItem = ItemInit.CHROMIUM_INGOT.get();
			   } else {
				   resultItem = ((ArmorItem) handHeld.getItem()).getArmorMaterial().getRepairMaterial().getMatchingStacks()[0].getItem();
			   }
			   
			   
			   if (number > 0) {
				   boolean success = expendExperience(worldIn, pos, player, this.cost);
				   if (!success) return ActionResultType.FAIL;
				   
				   player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
				   
				   for (int i=0;i<number;i++) {
					   ItemEntity itementity = new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(resultItem));
					   itementity.setDefaultPickupDelay();
					   worldIn.addEntity(itementity);
				   }
			   }
			   
			   return ActionResultType.SUCCESS;
			   
		   }
		   
		   if (!worldIn.isRemote && handHeld.getItem() instanceof TieredItem) { 
			   int baseMaterialAmount = 0;
			   
			   Item item = handHeld.getItem();

			   if (item instanceof PickaxeItem || item instanceof AxeItem) {
				   baseMaterialAmount = 3;
			   } else if (item instanceof ShovelItem) {
				   baseMaterialAmount = 1;
			   } else if (item instanceof HoeItem || item instanceof SwordItem) {
				   baseMaterialAmount = 2;
			   } else if (item instanceof TitaniumAIOT) {
				   baseMaterialAmount = 4;
			   } else {
				   return ActionResultType.FAIL;
			   }
			   
			   if (!handHeld.isRepairable()) {
				   return ActionResultType.FAIL;
			   }
			   
			   double maxDurability = handHeld.getItem().getMaxDamage(handHeld);
			   double durability = maxDurability - handHeld.getItem().getDamage(handHeld);
			   double multiplier = durability / maxDurability;
			   
			   Item resultItem;
			   int number = (int)(baseMaterialAmount * multiplier);
			   resultItem = ((TieredItem) handHeld.getItem()).getTier().getRepairMaterial().getMatchingStacks()[0].getItem();
			   
			   if (number > 0) {
				   boolean success = expendExperience(worldIn, pos, player, this.cost);
				   if (!success) return ActionResultType.FAIL;
				   
				   player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
				   
				   for (int i=0;i<number;i++) {
					   ItemEntity itementity = new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(resultItem));
					   itementity.setDefaultPickupDelay();
					   worldIn.addEntity(itementity);
				   }
			   }
			   
			   return ActionResultType.SUCCESS;
		   }
		   
		   
		   return ActionResultType.FAIL;
	      
	   }

	}