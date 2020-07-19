package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.lava;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.AbstractGenoratorTileEntity;

public class LavaGeneratorTileEntity extends AbstractGenoratorTileEntity {
   public LavaGeneratorTileEntity() {
      super(ModTileEntityTypes.LAVA_GENERATOR.get(), 10);
   }
}