package com.LukeTheDuke9801.progressivetechnicalities.objects.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class OilFluidBlock extends FlowingFluidBlock {
    public OilFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity){
            LivingEntity living = (LivingEntity) entityIn;
            living.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 4));
            if (living.areEyesInFluid(FluidTags.WATER)) {
                living.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 20, 0));
            }
        }

    }
}
