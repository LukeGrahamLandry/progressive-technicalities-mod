package com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools;

import java.util.List;
import java.util.Set;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class UnobtaniumDrill extends ToolItem {
	   private static final Set<Block> EFFECTIVE_ON = ImmutableSet.of();
	   public UnobtaniumDrill(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
	      super((float)attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder.addToolType(net.minecraftforge.common.ToolType.AXE, tier.getHarvestLevel()));
	   }
	   
	   @Override
		public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
			if (KeyboardHelper.isHoldingShift()) {
				tooltip.add(new StringTextComponent("Instantly mines everything. Rightclick a bedrock to mine it and get a bedrockium shard"));
			}
			
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}

	   /**
	    * Check whether this Item can harvest the given Block
	    */
	   public boolean canHarvestBlock(BlockState blockIn) {
	      return false;
	   }

	   public float getDestroySpeed(ItemStack stack, BlockState state) {
	      return this.efficiency;
	   }
	   
	   @Override
	   public ActionResultType onItemUse(ItemUseContext context) {
		   World world = context.getWorld();
		   BlockPos pos = context.getPos();
		   boolean isBedrock = world.getBlockState(pos).getBlock().equals(Blocks.BEDROCK);
		   if (isBedrock) {
			   world.setBlockState(pos, Blocks.AIR.getDefaultState());
			   ItemEntity itementity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.BEDROCKIUM_SHARD.get()));
			   itementity.setNoPickupDelay();
			   world.addEntity(itementity);
			   return ActionResultType.SUCCESS;
		   }
		   
			   
		   return super.onItemUse(context);
	   }

	   
}