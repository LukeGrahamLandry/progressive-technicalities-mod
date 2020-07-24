package com.LukeTheDuke9801.progressivetechnicalities.client.entity;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.WanderingGemSmith;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WanderingGemSmithRenderer extends MobRenderer<WanderingGemSmith, VillagerModel<WanderingGemSmith>> {
   private static final ResourceLocation field_217780_a = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "textures/entity/wandering_gem_smith.png");

   public WanderingGemSmithRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new VillagerModel<>(0.0F), 0.5F);
      this.addLayer(new HeadLayer<>(this));
      this.addLayer(new CrossedArmsItemLayer<>(this));
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getEntityTexture(WanderingGemSmith entity) {
      return field_217780_a;
   }

   protected void preRenderCallback(WanderingGemSmith entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
      float f = 0.9375F;
      matrixStackIn.scale(0.9375F, 0.9375F, 0.9375F);
   }
}