package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines;


import com.LukeTheDuke9801.progressivetechnicalities.objects.items.Battery;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BatterySlot extends Slot {
   private final AbstractMachineContainer field_216939_a;

   public BatterySlot(AbstractMachineContainer p_i50084_1_, IInventory p_i50084_2_, int p_i50084_3_, int p_i50084_4_, int p_i50084_5_) {
      super(p_i50084_2_, p_i50084_3_, p_i50084_4_, p_i50084_5_);
      this.field_216939_a = p_i50084_1_;
   }

   /**
    * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
    */
   public boolean isItemValid(ItemStack stack) {
      return stack.getItem() instanceof Battery;
   }
   
   public static boolean isItemValidBattery(ItemStack stack) {
	      return stack.getItem() instanceof Battery;
	   }

   public int getItemStackLimit(ItemStack stack) {
      return 1;
   }

   public static boolean isBucket(ItemStack stack) {
      return stack.getItem() == Items.BUCKET;
   }
}