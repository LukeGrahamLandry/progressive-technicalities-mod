package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class StructureCompass extends Item{
    private float angle = 0;
    private final String structureName;
    public StructureCompass(String structureNameIn, Item.Properties builder) {
        super(builder);
        this.structureName = structureNameIn;
        this.addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter() {
            @OnlyIn(Dist.CLIENT)
            public float call(ItemStack stack, @Nullable World p_call_2_, @Nullable LivingEntity p_call_3_) {
                if (stack.isOnItemFrame()) return (float) StructureCompass.NOT_FOUND;
                return StructureCompass.this.getAngle();
            }
        });
    }

    // TODO: use nbt to make itemstack specific so multiple players can use
    private float getAngle(){
        return this.angle;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote){
            this.angle = (float) getAngleToStructure(entityIn);
        }
    }

    private static double NOT_FOUND = 666;

    private double getAngleToStructure(Entity entity) {
        ServerWorld world = (ServerWorld)entity.getEntityWorld();
        BlockPos blockpos = world.findNearestStructure(this.structureName, entity.getPosition(), 100, false);
        if (blockpos == null) return NOT_FOUND;

        blockpos = blockpos.add(8, 0, 8);

        // logic from vanilla CompassItem
        double a = Math.atan2((double)blockpos.getZ() - entity.getPosZ(), (double)blockpos.getX() - entity.getPosX());
        double d1 = (double)entity.rotationYaw;
        d1 = MathHelper.positiveModulo(d1 / 360.0D, 1.0D);
        double d2 = a / (double)((float)Math.PI * 2F);
        double d0 = 0.5D - (d1 - 0.25D - d2);
        return MathHelper.positiveModulo((float)d0, 1.0F);
    }
}
