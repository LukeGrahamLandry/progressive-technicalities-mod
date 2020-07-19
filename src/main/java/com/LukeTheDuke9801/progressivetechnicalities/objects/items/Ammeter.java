package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.XPContainer;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.AbstractGenoratorTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class Ammeter extends Item {
   public Ammeter(Item.Properties builder) {
      super(builder);
   }
   
   @Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Lets you know how much xp a block is storing"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

   /**
    * Called when this item is used when targetting a Block
    */
   public ActionResultType onItemUse(ItemUseContext context) {
      World world = context.getWorld();
      if (!world.isRemote) {
         BlockPos pos = context.getPos();
         String s = "";
         
         XPContainer container = XPContainer.getXPContainerAtPos(world, pos);
         if (container != null) {
        	 s += String.format("Capacity: %d, Current: %d, Rate: %d", container.capacity, container.xp, container.transferRate);
         }
         
         AbstractGenoratorTileEntity generator = null;
 		 if (world.getTileEntity(pos) instanceof AbstractGenoratorTileEntity) {
 			generator = ((AbstractGenoratorTileEntity)world.getTileEntity(pos));
 		 }
 		 if (generator != null) {
 			 s += String.format("Rate: %d, Time Remaining: %d", generator.amountGenerated, generator.timeRemaining);
 		 }
 		 
         PlayerEntity player = context.getPlayer();
         player.sendMessage(new StringTextComponent(s));
         
      }
      
      return ActionResultType.SUCCESS;
   }
}