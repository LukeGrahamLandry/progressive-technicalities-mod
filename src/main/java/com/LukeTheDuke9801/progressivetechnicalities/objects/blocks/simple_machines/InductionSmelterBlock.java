package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class InductionSmelterBlock extends Block{
	private static final int cost = 5;
	
	public InductionSmelterBlock(Block.Properties builder) {
	      super(builder);
	   }
	   
	   
   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	   ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
	   Item item = stack.getItem();
	   
	   if (player.experienceTotal < cost) {
		   return ActionResultType.FAIL;
	   }
	   
	   Item result = getOutput(item, worldIn);
	   if (result != null) {
		   player.giveExperiencePoints(-cost);
		   stack.shrink(1);
		   
		   ItemEntity itementity = new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(result));
		   itementity.setNoPickupDelay();
		   worldIn.addEntity(itementity);
		   
		   return ActionResultType.SUCCESS;
	   }
	   return ActionResultType.FAIL;
      
   }
   
   private Item getOutput(Item item, World world) {
	   Item result = null;
	   
	   if (item.equals(ItemInit.IRON_DUST.get())) {
		   result = Items.IRON_INGOT;
	   }
	   if (item.equals(ItemInit.GOLD_DUST.get())) {
		   result = Items.GOLD_INGOT;
	   }
	   
	   if (item.equals(ItemInit.ALUMINUM_DUST.get())) {
		   result = ItemInit.ALUMINUM.get();
	   }
	   if (item.equals(ItemInit.CARBIDE_DUST.get())) {
		   result = ItemInit.CARBIDE_INGOT.get();
	   }
	   if (item.equals(ItemInit.CHROMIUM_DUST.get())) {
		   result = ItemInit.CHROMIUM_INGOT.get();
	   }
	   if (item.equals(ItemInit.ELECTRUM_DUST.get())) {
		   result = ItemInit.ELECTRUM_INGOT.get();
	   }
	   if (item.equals(ItemInit.FEYSTEEL_DUST.get())) {
		   result = ItemInit.FEYSTEEL_INGOT.get();
	   }
	   if (item.equals(ItemInit.OBSIDIAN_DUST.get())) {
		   result = ItemInit.OBSIDIAN_INGOT.get();
	   }
	   if (item.equals(ItemInit.SILVER_DUST.get())) {
		   result = ItemInit.SILVER_INGOT.get();
	   }
	   if (item.equals(ItemInit.STEEL_DUST.get())) {
		   result = ItemInit.STEEL_INGOT.get();
	   }
	   if (item.equals(ItemInit.TITANIUM_DUST.get())) {
		   result = ItemInit.TITANIUM_INGOT.get();
	   }
	   if (item.equals(ItemInit.DIASTIMA_DUST.get())) {
		   result = ItemInit.SPACE_INGOT.get();
	   }
	   if (item.equals(ItemInit.UNOBTANIUM_DUST.get())) {
		   result = ItemInit.UNOBTANIUM_INGOT.get();
	   }
	   if (item.equals(ItemInit.BEDROCKIUM_DUST.get())) {
		   result = ItemInit.BEDROCKIUM_INGOT.get();
	   }
	   if (item.equals(ItemInit.SKY_GEM_DUST.get())) {
		   result = ItemInit.SKY_GEM.get();
	   }
	   
	   if (item.equals(BlockInit.TITANIUM_ORE.get().asItem())) {
		   result = ItemInit.TITANIUM_INGOT.get();
	   }
	   if (item.equals(BlockInit.FEYSTEEL_ORE.get().asItem())) {
		   result = ItemInit.FEYSTEEL_INGOT.get();
	   }
	   
	   return result;
   }
}