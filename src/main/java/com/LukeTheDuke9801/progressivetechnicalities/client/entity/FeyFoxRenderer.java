package com.LukeTheDuke9801.progressivetechnicalities.client.entity;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.FairyEntity;
import com.LukeTheDuke9801.progressivetechnicalities.entities.FeyFoxEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.FoxHeldItemLayer;
import net.minecraft.client.renderer.entity.model.FoxModel;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class FeyFoxRenderer extends MobRenderer<FeyFoxEntity, FoxModel<FeyFoxEntity>> {
    private static final ResourceLocation FOX = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "textures/entity/fey_fox.png");
    private static final ResourceLocation SLEEPING_FOX = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "textures/entity/fey_fox_sleep.png");

    public FeyFoxRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FoxModel<>(), 0.4F);
    }

    protected void applyRotations(FeyFoxEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (entityLiving.func_213480_dY() || entityLiving.isStuck()) {
            float f = -MathHelper.lerp(partialTicks, entityLiving.prevRotationPitch, entityLiving.rotationPitch);
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f));
        }

    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(FeyFoxEntity entity) {
        return entity.isSleeping() ? SLEEPING_FOX : FOX;
    }
}