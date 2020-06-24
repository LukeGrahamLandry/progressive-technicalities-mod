package net.minecraft.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.util.List;
import net.minecraft.block.BannerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBannerBlock;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BannerTileEntityRenderer extends TileEntityRenderer<BannerTileEntity> {
   private final ModelRenderer field_228833_a_ = func_228836_a_();
   private final ModelRenderer field_228834_c_ = new ModelRenderer(64, 64, 44, 0);
   private final ModelRenderer field_228835_d_;

   public BannerTileEntityRenderer(TileEntityRendererDispatcher p_i226002_1_) {
      super(p_i226002_1_);
      this.field_228834_c_.addBox(-1.0F, -30.0F, -1.0F, 2.0F, 42.0F, 2.0F, 0.0F);
      this.field_228835_d_ = new ModelRenderer(64, 64, 0, 42);
      this.field_228835_d_.addBox(-10.0F, -32.0F, -1.0F, 20.0F, 2.0F, 2.0F, 0.0F);
   }

   public static ModelRenderer func_228836_a_() {
      ModelRenderer modelrenderer = new ModelRenderer(64, 64, 0, 0);
      modelrenderer.addBox(-10.0F, 0.0F, -2.0F, 20.0F, 40.0F, 1.0F, 0.0F);
      return modelrenderer;
   }

   public void render(BannerTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
      if (tileEntityIn.getPatternList() != null) {
         float f = 0.6666667F;
         boolean flag = tileEntityIn.getWorld() == null;
         matrixStackIn.push();
         long i;
         if (flag) {
            i = 0L;
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            this.field_228834_c_.showModel = !tileEntityIn.func_226955_h_();
         } else {
            i = tileEntityIn.getWorld().getGameTime();
            BlockState blockstate = tileEntityIn.getBlockState();
            if (blockstate.getBlock() instanceof BannerBlock) {
               matrixStackIn.translate(0.5D, 0.5D, 0.5D);
               float f1 = (float)(-blockstate.get(BannerBlock.ROTATION) * 360) / 16.0F;
               matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
               this.field_228834_c_.showModel = true;
            } else {
               matrixStackIn.translate(0.5D, (double)-0.16666667F, 0.5D);
               float f3 = -blockstate.get(WallBannerBlock.HORIZONTAL_FACING).getHorizontalAngle();
               matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f3));
               matrixStackIn.translate(0.0D, -0.3125D, -0.4375D);
               this.field_228834_c_.showModel = false;
            }
         }

         matrixStackIn.push();
         matrixStackIn.scale(0.6666667F, -0.6666667F, -0.6666667F);
         IVertexBuilder ivertexbuilder = ModelBakery.LOCATION_BANNER_BASE.getBuffer(bufferIn, RenderType::getEntitySolid);
         this.field_228834_c_.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
         this.field_228835_d_.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
         if (tileEntityIn.func_226955_h_()) {
            this.field_228833_a_.rotateAngleX = 0.0F;
         } else {
            BlockPos blockpos = tileEntityIn.getPos();
            float f2 = ((float)Math.floorMod((long)(blockpos.getX() * 7 + blockpos.getY() * 9 + blockpos.getZ() * 13) + i, 100L) + partialTicks) / 100.0F;
            this.field_228833_a_.rotateAngleX = (-0.0125F + 0.01F * MathHelper.cos(((float)Math.PI * 2F) * f2)) * (float)Math.PI;
         }

         this.field_228833_a_.rotationPointY = -32.0F;
         func_228837_a_(tileEntityIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, this.field_228833_a_, ModelBakery.LOCATION_BANNER_BASE, true);
         matrixStackIn.pop();
         matrixStackIn.pop();
      }
   }

   public static void func_228837_a_(BannerTileEntity p_228837_0_, MatrixStack p_228837_1_, IRenderTypeBuffer p_228837_2_, int p_228837_3_, int p_228837_4_, ModelRenderer p_228837_5_, Material p_228837_6_, boolean p_228837_7_) {
      p_228837_5_.render(p_228837_1_, p_228837_6_.getBuffer(p_228837_2_, RenderType::getEntitySolid), p_228837_3_, p_228837_4_);
      List<BannerPattern> list = p_228837_0_.getPatternList();
      List<DyeColor> list1 = p_228837_0_.getColorList();

      for(int i = 0; i < 17 && i < list.size() && i < list1.size(); ++i) {
         BannerPattern bannerpattern = list.get(i);
         DyeColor dyecolor = list1.get(i);
         float[] afloat = dyecolor.getColorComponentValues();
         Material material = new Material(p_228837_7_ ? Atlases.BANNER_ATLAS : Atlases.SHIELD_ATLAS, bannerpattern.func_226957_a_(p_228837_7_));
         p_228837_5_.render(p_228837_1_, material.getBuffer(p_228837_2_, RenderType::getEntityNoOutline), p_228837_3_, p_228837_4_, afloat[0], afloat[1], afloat[2], 1.0F);
      }

   }
}