package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.rocket;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.AbstractMachineScreen;

import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RocketScreen extends AbstractMachineScreen<RocketContainer> {
   private static final ResourceLocation GUI_TEXTURE =new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "textures/gui/default_machine.png");

   public RocketScreen(RocketContainer p_i51089_1_, PlayerInventory p_i51089_2_, ITextComponent p_i51089_3_) {
      super(p_i51089_1_, p_i51089_2_, p_i51089_3_, GUI_TEXTURE);
   }
}