package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

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

public class ItemRepairer extends Block{
	public ItemRepairer(Block.Properties builder) {
	      super(builder);
	   }
	   
	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   ItemStack handHeld = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		   
		   if (handHeld.isRepairable()) {
			   int xp = player.experienceTotal;
			   int canRepair = xp / 2;
			   int damage = handHeld.getDamage();
			   
			   if (canRepair > damage) {
				   player.giveExperiencePoints(-(damage * 2));
				   handHeld.setDamage(0);
				   return ActionResultType.SUCCESS;
			   } else {
				   player.giveExperiencePoints(-canRepair);
				   handHeld.setDamage(damage - canRepair);
				   return ActionResultType.SUCCESS;
			   }
		   }
		   
		   return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	      
	   }

}