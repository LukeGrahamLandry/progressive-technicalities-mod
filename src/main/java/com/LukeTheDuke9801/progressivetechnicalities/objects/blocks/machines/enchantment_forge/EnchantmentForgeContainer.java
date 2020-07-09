package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.enchantment_forge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModContainerTypes;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantmentForgeContainer extends Container {
   private static final Logger LOGGER = LogManager.getLogger();
   private final IInventory outputSlot = new CraftResultInventory();
   private final IInventory inputSlots = new Inventory(2) {
      /**
       * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
       * it hasn't changed and skip it.
       */
      public void markDirty() {
         super.markDirty();
         EnchantmentForgeContainer.this.onCraftMatrixChanged(this);
      }
   };
   private final IntReferenceHolder maximumCost = IntReferenceHolder.single();
   private final IWorldPosCallable field_216980_g;
   public int materialCost;
   private String repairedItemName;
   private final PlayerEntity player;

   public EnchantmentForgeContainer(int id, PlayerInventory playerInventoryIn, final PacketBuffer data) {
	      this(id, playerInventoryIn);
		}
   
   public EnchantmentForgeContainer(int p_i50101_1_, PlayerInventory p_i50101_2_) {
      this(p_i50101_1_, p_i50101_2_, IWorldPosCallable.DUMMY);
   }

   public EnchantmentForgeContainer(int p_i50102_1_, PlayerInventory p_i50102_2_, final IWorldPosCallable p_i50102_3_) {
      super(ModContainerTypes.ENCHANTMENT_FORGE.get(), p_i50102_1_);
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
            return (playerIn.abilities.isCreativeMode || playerIn.experienceLevel >= EnchantmentForgeContainer.this.maximumCost.get()) && EnchantmentForgeContainer.this.maximumCost.get() > 0 && this.getHasStack();
         }

         public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
            if (!thePlayer.abilities.isCreativeMode) {
               thePlayer.addExperienceLevel(-EnchantmentForgeContainer.this.maximumCost.get());
            }
            
            ItemStack itemstack1 = EnchantmentForgeContainer.this.inputSlots.getStackInSlot(0);
            ItemStack itemstack2 = EnchantmentForgeContainer.this.inputSlots.getStackInSlot(1);
            
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
    	  
    	  int cost = 0;
    	  Enchantment enchant = Enchantments.VANISHING_CURSE;  // default value is never used
    	  
    	  if (!(item1.equals(Items.BOOK))){
    		  this.outputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
    		  return;
    	  }
    	  
    	  if (item2.equals(Items.OBSIDIAN)){
    		  enchant = Enchantments.UNBREAKING;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.QUARTZ_BLOCK)){
    		  enchant = Enchantments.SHARPNESS;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.ROTTEN_FLESH)){
    		  enchant = Enchantments.SMITE;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.SPIDER_EYE)){
    		  enchant = Enchantments.BANE_OF_ARTHROPODS;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.BLAZE_ROD)){
    		  enchant = Enchantments.FIRE_ASPECT;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.PISTON)){
    		  enchant = Enchantments.KNOCKBACK;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.IRON_SWORD)){
    		  enchant = Enchantments.SWEEPING;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.LAPIS_BLOCK)){
    		  enchant = Enchantments.LOOTING;
    		  cost = 15;
    	  }
    	  
    	  if (item2.equals(ItemInit.CHROMIUM_INGOT.get())){
    		  enchant = Enchantments.PROTECTION;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.BLAZE_POWDER)){
    		  enchant = Enchantments.FIRE_PROTECTION;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.ARROW)){
    		  enchant = Enchantments.PROJECTILE_PROTECTION;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.GUNPOWDER)){
    		  enchant = Enchantments.BLAST_PROTECTION;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.CACTUS)){
    		  enchant = Enchantments.THORNS;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.FEATHER)){
    		  enchant = Enchantments.FEATHER_FALLING;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.HEART_OF_THE_SEA)){
    		  enchant = Enchantments.RESPIRATION;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.COD)){
    		  enchant = Enchantments.DEPTH_STRIDER;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.SPONGE)){
    		  enchant = Enchantments.AQUA_AFFINITY;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.ENDER_PEARL)){
    		  enchant = Enchantments.INFINITY;
    		  cost = 25;
    	  }
    	  
    	  if (item2.equals(Items.FLINT)){
    		  enchant = Enchantments.POWER;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.STRING)){
    		  enchant = Enchantments.PUNCH;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.FIRE_CHARGE)){
    		  enchant = Enchantments.FLAME;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.REDSTONE_BLOCK)){
    		  enchant = Enchantments.EFFICIENCY;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.LAPIS_LAZULI)){
    		  enchant = Enchantments.FORTUNE;
    		  cost = 15;
    	  }
    	  
    	  if (item2.equals(Items.EMERALD)){
    		  enchant = Enchantments.SILK_TOUCH;
    		  cost = 25;
    	  }
    	  
    	  if (item2.equals(Items.GLOWSTONE_DUST)){
    		  enchant = Enchantments.CHANNELING;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.PUFFERFISH)){
    		  enchant = Enchantments.IMPALING;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.WATER_BUCKET)){
    		  enchant = Enchantments.LOYALTY;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.TROPICAL_FISH)){
    		  enchant = Enchantments.RIPTIDE;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.PHANTOM_MEMBRANE)){
    		  enchant = Enchantments.PIERCING;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.SUGAR_CANE)){
    		  enchant = Enchantments.MULTISHOT;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.SUGAR)){
    		  enchant = Enchantments.QUICK_CHARGE;
    		  cost = 10;
    	  }
    	  
    	  if (item2.equals(Items.BLUE_ICE)){
    		  enchant = Enchantments.FROST_WALKER;
    		  cost = 39;
    	  }
    	  
    	  if (item2.equals(Items.DIAMOND_BLOCK)){
    		  enchant = Enchantments.MENDING;
    		  cost = 39;
    	  }
    	  
    	  if (item2.equals(Items.LAVA_BUCKET)){
    		  enchant = EnchantmentInit.LAVA_WALKER.get();
    		  cost = 39;
    	  }
    	  
    	  
    	  if (cost > 0) {
    		  ItemStack result = new ItemStack(Items.ENCHANTED_BOOK);
    		  EnchantedBookItem.addEnchantment(result, new EnchantmentData(enchant, 1));

        	  this.outputSlot.setInventorySlotContents(0, result);
        	  this.maximumCost.set(cost);
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