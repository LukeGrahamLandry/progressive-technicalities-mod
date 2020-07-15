package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.tinker_table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModContainerTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.ModularArmor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TinkerTableContainer extends Container {
   private static final Logger LOGGER = LogManager.getLogger();
   private final IInventory outputSlot = new CraftResultInventory();
   private final IInventory inputSlots = new Inventory(2) {
      /**
       * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
       * it hasn't changed and skip it.
       */
      public void markDirty() {
         super.markDirty();
         TinkerTableContainer.this.onCraftMatrixChanged(this);
      }
   };
   private final IntReferenceHolder maximumCost = IntReferenceHolder.single();
   private final IWorldPosCallable field_216980_g;
   public int materialCost;
   private String repairedItemName;
   private final PlayerEntity player;

   public TinkerTableContainer(int id, PlayerInventory playerInventoryIn, final PacketBuffer data) {
	      this(id, playerInventoryIn);
		}
   
   public TinkerTableContainer(int p_i50101_1_, PlayerInventory p_i50101_2_) {
      this(p_i50101_1_, p_i50101_2_, IWorldPosCallable.DUMMY);
   }

   public TinkerTableContainer(int p_i50102_1_, PlayerInventory p_i50102_2_, final IWorldPosCallable p_i50102_3_) {
      super(ModContainerTypes.TINKER_TABLE.get(), p_i50102_1_);
      this.field_216980_g = p_i50102_3_;
      this.player = p_i50102_2_.player;
      this.trackInt(this.maximumCost);
      this.addSlot(new Slot(this.inputSlots, 0, 27, 47));
      this.addSlot(new Slot(this.inputSlots, 1, 76, 47));
      this.addSlot(new Slot(this.outputSlot, 2, 134, 47) {
         /**
          * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
          */
         public boolean isItemValid(ItemStack stack) {
            return false;
         }

         /**
          * Return whether this slot's stack can be taken from this slot.
          */
         public boolean canTakeStack(PlayerEntity playerIn) {
            return (playerIn.abilities.isCreativeMode || playerIn.experienceLevel >= TinkerTableContainer.this.maximumCost.get()) && TinkerTableContainer.this.maximumCost.get() > 0 && this.getHasStack();
         }

         public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
            if (!thePlayer.abilities.isCreativeMode) {
               thePlayer.addExperienceLevel(-TinkerTableContainer.this.maximumCost.get());
            }
            
            ItemStack itemstack1 = TinkerTableContainer.this.inputSlots.getStackInSlot(0);
            ItemStack itemstack2 = TinkerTableContainer.this.inputSlots.getStackInSlot(1);
            
            itemstack1.shrink(1);
            itemstack2.shrink(1);
            
            updateRepairOutput();

            return stack;
         }
      });

      for(int i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(p_i50102_2_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(int k = 0; k < 9; ++k) {
         this.addSlot(new Slot(p_i50102_2_, k, 8 + k * 18, 142));
      }

   }

   /**
    * Callback for when the crafting matrix is changed.
    */
   public void onCraftMatrixChanged(IInventory inventoryIn) {
      super.onCraftMatrixChanged(inventoryIn);
      if (inventoryIn == this.inputSlots) {
         this.updateRepairOutput();
      }

   }

   /**
    * called when the Anvil Input Slot changes, calculates the new result and puts it in the output slot
    */
   public void updateRepairOutput() {
      ItemStack itemstack1 = this.inputSlots.getStackInSlot(0);
      ItemStack itemstack2 = this.inputSlots.getStackInSlot(1);
      
      if (itemstack1.isEmpty() || itemstack2.isEmpty()) {
         this.outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
         this.maximumCost.set(0);
      } else {
    	  Item item1 = itemstack1.getItem();
    	  Item item2 = itemstack2.getItem();
    	  
    	  if (!(item1 instanceof ModularArmor)){
    		  this.outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
    		  return;
    	  }
    	  
    	  String moduleKey = ModularArmor.getModuleKeyFromItem(item2);
    	  
    	  if (ModularArmor.canIncreaseModuleLevel(itemstack1, moduleKey)) {
    		  ItemStack result = itemstack1.copy();
    		  
    		  ModularArmor.increaseModuleLevel(result, moduleKey);
    		  
    		  this.outputSlot.setInventorySlotContents(0, result);
        	  this.maximumCost.set(20);
    	  } else {
    		  this.outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
    	  }
    	  
    	  this.detectAndSendChanges();
      }
   }

   public static int getNewRepairCost(int oldRepairCost) {
      return oldRepairCost * 2 + 1;
   }

   /**
    * Called when the container is closed.
    */
   public void onContainerClosed(PlayerEntity playerIn) {
      super.onContainerClosed(playerIn);
      this.field_216980_g.consume((p_216973_2_, p_216973_3_) -> {
         this.clearContainer(playerIn, p_216973_2_, this.inputSlots);
      });
   }

   /**
    * Determines whether supplied player can use this container
    */
   public boolean canInteractWith(PlayerEntity playerIn) {
      return true;
   }

   /**
    * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
    * inventory and the other inventory(s).
    */
   public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index == 2) {
            if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
               return ItemStack.EMPTY;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if (index != 0 && index != 1) {
            if (index >= 3 && index < 39 && !this.mergeItemStack(itemstack1, 0, 2, false)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
            return ItemStack.EMPTY;
         }

         if (itemstack1.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.getCount() == itemstack.getCount()) {
            return ItemStack.EMPTY;
         }

         slot.onTake(playerIn, itemstack1);
      }

      return itemstack;
   }

   /**
    * used by the Anvil GUI to update the Item Name being typed by the player
    */
   public void updateItemName(String newName) {
	   /*
      this.repairedItemName = newName;
      if (this.getSlot(2).getHasStack()) {
         ItemStack itemstack = this.getSlot(2).getStack();
         if (StringUtils.isBlank(newName)) {
            itemstack.clearCustomName();
         } else {
            itemstack.setDisplayName(new StringTextComponent(this.repairedItemName));
         }
      }

      this.updateRepairOutput();
      */
   }

   /**
    * Get's the maximum xp cost
    */
   @OnlyIn(Dist.CLIENT)
   public int getMaximumCost() {
      return this.maximumCost.get();
   }

   public void setMaximumCost(int value) {
      this.maximumCost.set(value);
   }
}