package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.oil.OilGeneratorTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.PreWrittenBook;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class PulverizerBlock extends SimpleMachineBlock{
	private static final int cost = 5;
	
	public PulverizerBlock(Block.Properties builder) {
	      super(builder);
	   }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Right click with an item to pulverize it (ie. ore to dust for ore doubling) (takes power)"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	   
   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	   ItemStack stack = player.getHeldItem(handIn);
	   Item item = stack.getItem();
	   
	   RecipeResult result = getOutput(item);
	   if (result != null) {
		   boolean success = expendExperience(worldIn, pos, player, this.cost);
		   if (!success) return ActionResultType.FAIL;
		   
		   stack.shrink(1);
		   
		   for (int i=0;i<result.count;i++) {
			   ItemEntity itementity = new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(result.item));
			   itementity.setNoPickupDelay();
			   worldIn.addEntity(itementity);
		   }
		   return ActionResultType.SUCCESS;
	   }
	   return ActionResultType.FAIL;
      
   }
   
   private RecipeResult getOutput(Item item) {
	   Item result = null;
	   int count = 0;

	   if (item instanceof PreWrittenBook) {
		   result = ItemInit.CHROMIUM_DUST.get();
		   count = 1;
	   }
	   if (item.equals(Items.BLAZE_ROD)) {
		   result = Items.BLAZE_POWDER;
		   count = 4;
	   }
	   if (item.equals(Items.BONE)) {
		   result = Items.BONE_MEAL;
		   count = 5;
	   }
	   if (item.equals(Items.SUGAR_CANE)) {
		   result = Items.SUGAR;
		   count = 2;
	   }
	   if (item.equals(Items.COBBLESTONE)) {
		   result = Items.GRAVEL;
		   count = 1;
	   }
	   if (item.equals(Items.GRAVEL)) {
		   result = Items.SAND;
		   count = 1;
	   }
	   if (item.equals(Items.GLASS)) {
		   result = Items.SAND;
		   count = 1;
	   }
	   if (item.equals(Items.WHITE_WOOL)) {
		   result = Items.STRING;
		   count = 4;
	   }
	   if (item.equals(Items.BRICK)) {
		   result = Items.CLAY_BALL;
		   count = 1;
	   }
	   if (item.equals(Items.FLOWER_POT)) {
		   result = Items.BRICK;
		   count = 3;
	   }
	   if (item.equals(Items.COAL)) {
		   result = ItemInit.TINY_COAL.get();
		   count = 8;
	   }
	   if (item.equals(Items.CLAY)) {
		   result = Items.CLAY_BALL;
		   count = 4;
	   }
	   if (item.equals(Items.BRICKS)) {
		   result = Items.BRICK;
		   count = 4;
	   }
	   if (item.equals(Items.QUARTZ_BLOCK)) {
		   result = Items.QUARTZ;
		   count = 4;
	   }
		   
	   if (item.equals(Items.COAL_ORE)) {
		   result = Items.COAL;
		   count = 3;
	   }
	   if (item.equals(Items.DIAMOND_ORE)) {
		   result = Items.DIAMOND;
		   count = 2;
	   }
	   if (item.equals(Items.EMERALD_ORE)) {
		   result = Items.EMERALD;
		   count = 2;
	   }
	   if (item.equals(Items.NETHER_QUARTZ_ORE)) {
		   result = Items.QUARTZ;
		   count = 2;
	   }
	   if (item.equals(Items.LAPIS_ORE)) {
		   result = Items.LAPIS_LAZULI;
		   count = 7;
	   }
	   if (item.equals(Items.REDSTONE_ORE)) {
		   result = Items.REDSTONE;
		   count = 7;
	   }
	   if (item.equals(Items.GOLD_ORE)) {
		   result = ItemInit.GOLD_DUST.get();
		   count = 2;
	   }
	   if (item.equals(Items.IRON_ORE)) {
		   result = ItemInit.IRON_DUST.get();
		   count = 2;
	   }
	   
	   if (item.equals(BlockInit.CHROMIUM_ORE.get().asItem())) {
		   result = ItemInit.CHROMIUM_DUST.get();
		   count = 2;
	   }
	   if (item.equals(BlockInit.TITANIUM_ORE.get().asItem())) {
		   result = ItemInit.TITANIUM_DUST.get();
		   count = 2;
	   }
	   if (item.equals(BlockInit.FEYSTEEL_ORE.get().asItem())) {
		   result = ItemInit.FEYSTEEL_DUST.get();
		   count = 2;
	   }
	   if (item.equals(BlockInit.CHROMIUM_ORE.get().asItem())) {
		   result = ItemInit.CHROMIUM_DUST.get();
		   count = 2;
	   }
	   
	   if (item.equals(Items.OBSIDIAN)) {
		   result = ItemInit.OBSIDIAN_DUST.get();
		   count = 1;
	   }
	   if (item.equals(Items.IRON_INGOT)) {
		   result = ItemInit.IRON_DUST.get();
		   count = 1;
	   }
	   if (item.equals(Items.GOLD_INGOT)) {
		   result = ItemInit.GOLD_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.CHROMIUM_INGOT.get())) {
		   result = ItemInit.CHROMIUM_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.CARBIDE_INGOT.get())) {
		   result = ItemInit.CARBIDE_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.TITANIUM_INGOT.get())) {
		   result = ItemInit.TITANIUM_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.STEEL_INGOT.get())) {
		   result = ItemInit.STEEL_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.NYMPHARIUM_INGOT.get())) {
		   result = ItemInit.NYMPHARIUM_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.VARIDIUM_INGOT.get())) {
		   result = ItemInit.VARIDIUM_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.FEYSTEEL_INGOT.get())) {
		   result = ItemInit.FEYSTEEL_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.SPACE_INGOT.get())) {
		   result = ItemInit.DIASTIMA_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.AIR_GEM.get())) {
		   result = ItemInit.AIR_GEM_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.BEDROCKIUM_INGOT.get())) {
		   result = ItemInit.BEDROCKIUM_DUST.get();
		   count = 1;
	   }
	   if (item.equals(ItemInit.UNOBTANIUM_INGOT.get())) {
		   result = ItemInit.UNOBTANIUM_DUST.get();
		   count = 1;
	   }
	   
	   if (item.equals(BlockInit.UNOBTANIUM_ORE.get().asItem())) {
		   result = ItemInit.UNOBTANIUM_SHARD.get();
		   count = 2;
	   }
	   
	   if (result == null) return null;
	   return new RecipeResult(result, count);
   }
   
   private class RecipeResult{
	   public Item item;
	   public int count;
	   public RecipeResult(Item itemIn, int countIn) {
		   this.item = itemIn;
		   this.count = countIn;
	   }
   }
}