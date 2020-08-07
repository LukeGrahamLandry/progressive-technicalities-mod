package com.LukeTheDuke9801.progressivetechnicalities.objects.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class NymphariumFluidBlock extends FlowingFluidBlock {
    public NymphariumFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity){
            LivingEntity living = (LivingEntity) entityIn;
            if (!(entityIn instanceof DolphinEntity)){
                // only actually damages twice a second
                living.attackEntityFrom(DamageSource.LAVA, 4);  // TODO: when I add charm from fairies, make it not lava damage
            }
        }

    }
}
