package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.oil;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.AbstractGenoratorTileEntity;

public class OilGeneratorTileEntity extends AbstractGenoratorTileEntity {
   public OilGeneratorTileEntity() {
      super(ModTileEntityTypes.OIL_GENERATOR.get(), 3);
   }
}