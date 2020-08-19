package com.LukeTheDuke9801.progressivetechnicalities.client.entity;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.FairyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.VexModel;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.util.ResourceLocation;

public class FairyRenderer extends BipedRenderer<FairyEntity, FairyModel> {
    private static final ResourceLocation ANGRY_FAIRY_TEXTURE = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "textures/entity/angry_fairy.png");

    public FairyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FairyModel(), 0.3F);
    }

    protected int getBlockLight(FairyEntity entityIn, float partialTicks) {
        return 15;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(FairyEntity entity) {
        if (entity.isAngry()) return ANGRY_FAIRY_TEXTURE;
        else {
            return entity.texture.resourceLocation;
        }
    }

    protected void preRenderCallback(FairyEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.4F, 0.4F, 0.4F);
    }
}