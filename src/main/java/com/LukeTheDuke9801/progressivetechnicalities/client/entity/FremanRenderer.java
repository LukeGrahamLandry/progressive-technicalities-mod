package com.LukeTheDuke9801.progressivetechnicalities.client.entity;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.FremanEntity;
import com.LukeTheDuke9801.progressivetechnicalities.entities.WanderingAstronomer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FremanRenderer extends IllagerRenderer<FremanEntity> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "textures/entity/freman.png");

   public FremanRenderer(EntityRendererManager renderManagerIn){
           super(renderManagerIn,new IllagerModel<>(0.0F,0.0F,64,64),0.5F);
           this.addLayer(new HeldItemLayer<FremanEntity, IllagerModel<FremanEntity>>(this){
            public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, FremanEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch){
                    if(entitylivingbaseIn.isAggressive()){
                        super.render(matrixStackIn,bufferIn,packedLightIn,entitylivingbaseIn,limbSwing,limbSwingAmount,partialTicks,ageInTicks,netHeadYaw,headPitch);
                    }

                    }
           });
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getEntityTexture(FremanEntity entity){
      return TEXTURE;
   }
}