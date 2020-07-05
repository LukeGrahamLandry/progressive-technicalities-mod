package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMachineScreen<T extends AbstractMachineContainer> extends ContainerScreen<T> {
   private final ResourceLocation guiTexture;

   public AbstractMachineScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn, ResourceLocation guiTextureIn) {
      super(screenContainer, inv, titleIn);
      this.guiTexture = guiTextureIn;
   }

   public void init() {
      super.init();
   }

   public void tick() {
      super.tick();
   }

   public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
      this.renderBackground();
      super.render(p_render_1_, p_render_2_, p_render_3_);
      this.renderHoveredToolTip(p_render_1_, p_render_2_);
   }

   /**
    * Draw the foreground layer for the GuiContainer (everything in front of the items)
    */
   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      String s = this.title.getFormattedText();
      this.font.drawString(s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
      this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
      
      s = Integer.toString(((AbstractMachineContainer)this.container).getEnergyAmount()) + " J";
      this.font.drawString(s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 16.0F, 4210752);
   }

   /**
    * Draws the background layer of this container (behind the items).
    */
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.minecraft.getTextureManager().bindTexture(this.guiTexture);
      int i = this.guiLeft;
      int j = this.guiTop;
      this.blit(i, j, 0, 0, this.xSize, this.ySize);
      if (((AbstractMachineContainer)this.container).isBurning()) {
         int k = ((AbstractMachineContainer)this.container).getBurnLeftScaled();
         // this.blit(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
      }

      int l = ((AbstractMachineContainer)this.container).getCookProgressionScaled();
      // this.blit(i + 79, j + 34, 176, 14, l + 1, 16);
   }

   public void removed() {
      super.removed();
   }
}