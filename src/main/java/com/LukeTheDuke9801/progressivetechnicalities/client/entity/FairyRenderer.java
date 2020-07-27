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
    private static final ResourceLocation VEX_TEXTURE = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "textures/entity/fairy.png");
    private static final ResourceLocation VEX_CHARGING_TEXTURE = new ResourceLocation("textures/entity/illager/vex_charging.png");

    public FairyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FairyModel(), 0.3F);
    }

    protected int getBlockLight(VexEntity entityIn, float partialTicks) {
        return 15;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(FairyEntity entity) {
        return entity.isCharging() ? VEX_CHARGING_TEXTURE : VEX_TEXTURE;
    }

    protected void preRenderCallback(FairyEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.4F, 0.4F, 0.4F);
    }
}