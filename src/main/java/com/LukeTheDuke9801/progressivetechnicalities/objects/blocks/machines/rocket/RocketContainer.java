package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.rocket;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModContainerTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.AbstractMachineContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;

public class RocketContainer extends AbstractMachineContainer {
		public RocketContainer(int id, PlayerInventory playerInventoryIn, final PacketBuffer data) {
	      super(ModContainerTypes.ELECTRIC_FURNACE.get(), IRecipeType.SMELTING, id, playerInventoryIn);
		}
		
	   public RocketContainer(int id, PlayerInventory playerInventoryIn) {
	      super(ModContainerTypes.ELECTRIC_FURNACE.get(), IRecipeType.SMELTING, id, playerInventoryIn);
	   }

	   public RocketContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray p_i50083_4_) {
	      super(ModContainerTypes.ELECTRIC_FURNACE.get(), IRecipeType.SMELTING, id, playerInventoryIn, furnaceInventoryIn, p_i50083_4_);
	   }
	}