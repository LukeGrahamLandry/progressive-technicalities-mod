package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.electric_furnace;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.AbstractMachineTileEntity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ElectricFurnaceTileEntity extends AbstractMachineTileEntity {
   public ElectricFurnaceTileEntity() {
      super(ModTileEntityTypes.ELECTRIC_FURNACE.get(), IRecipeType.SMELTING);
   }

   protected ITextComponent getDefaultName() {
      return new TranslationTextComponent("container.furnace");
   }

   protected Container createMenu(int id, PlayerInventory player) {
      return new ElectricFurnaceContainer(id, player, this, this.furnaceData);
   }
}