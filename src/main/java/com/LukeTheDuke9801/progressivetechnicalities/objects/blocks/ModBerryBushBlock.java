package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ModBerryBushBlock extends SweetBerryBushBlock implements IGrowable {
	private Item berryItem; 
   public ModBerryBushBlock(Item itemIn, Block.Properties properties) {
      super(properties);
      this.berryItem = itemIn;
   }

   public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
      return new ItemStack(this.berryItem);
   }
}
