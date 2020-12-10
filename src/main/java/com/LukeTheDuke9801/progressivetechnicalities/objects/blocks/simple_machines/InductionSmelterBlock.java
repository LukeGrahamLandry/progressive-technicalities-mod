package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.List;

public class InductionSmelterBlock extends SimpleMachineBlock{
	private static final int cost = 5;
	
	public InductionSmelterBlock(Block.Properties builder) {
	      super(builder);
	   }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Right click with an item to smelt it (also turns dusts to ingots) (takes power)"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	   
   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	   ItemStack stack = player.getHeldItem(handIn);
	   Item item = stack.getItem();
	   
	   Item result = getOutput(item, worldIn);
	   if (result != item) {
		   boolean success = expendExperience(worldIn, pos, player, cost);
		   if (!success) return ActionResultType.FAIL;
		   
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

	   if (item.equals(Items.ROTTEN_FLESH)) {
		   result = Items.LEATHER;
	   }

	   if (item.equals(ItemInit.IRON_DUST.get())) {
		   result = Items.IRON_INGOT;
	   }
	   if (item.equals(ItemInit.GOLD_DUST.get())) {
		   result = Items.GOLD_INGOT;
	   }
	   
	   if (item.equals(ItemInit.VARIDIUM_DUST.get())) {
		   result = ItemInit.VARIDIUM_INGOT.get();
	   }
	   if (item.equals(ItemInit.CARBIDE_DUST.get())) {
		   result = ItemInit.CARBIDE_INGOT.get();
	   }
	   if (item.equals(ItemInit.CHROMIUM_DUST.get())) {
		   result = ItemInit.CHROMIUM_INGOT.get();
	   }
	   if (item.equals(ItemInit.FEYSTEEL_DUST.get())) {
		   result = ItemInit.FEYSTEEL_INGOT.get();
	   }
	   if (item.equals(ItemInit.OBSIDIAN_DUST.get())) {
		   result = ItemInit.OBSIDIAN_INGOT.get();
	   }
	   if (item.equals(ItemInit.NYMPHARIUM_DUST.get())) {
		   result = ItemInit.NYMPHARIUM_INGOT.get();
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

	   if (item.equals(BlockInit.TITANIUM_ORE.get().asItem())) {
		   result = ItemInit.TITANIUM_INGOT.get();
	   }
	   if (item.equals(BlockInit.FEYSTEEL_ORE.get().asItem())) {
		   result = ItemInit.FEYSTEEL_INGOT.get();
	   }
	   
	   if (result == null) {
		   result = smelt(new ItemStack(item), world).getItem();
	   }
	   
	   return result;
   }
   
   private static ItemStack smelt(ItemStack stack, World world) {
       return world.getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(stack), world)
               .map(FurnaceRecipe::getRecipeOutput)
               .filter(itemStack -> !itemStack.isEmpty())
               .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
               .orElse(stack);
   }
}