package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.coal;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.AbstractGenoratorTileEntity;

public class CoalGeneratorTileEntity extends AbstractGenoratorTileEntity {
   public CoalGeneratorTileEntity() {
      super(ModTileEntityTypes.COAL_GENERATOR.get(), 1);
   }
}