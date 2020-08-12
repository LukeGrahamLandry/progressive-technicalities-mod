package com.LukeTheDuke9801.progressivetechnicalities.objects.fluids;

import com.LukeTheDuke9801.progressivetechnicalities.entities.FairyEntity;
import com.LukeTheDuke9801.progressivetechnicalities.entities.FeyFoxEntity;
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
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class NymphariumFluidBlock extends FlowingFluidBlock {
    static final Random rand = new Random();
    public static final DamageSource DAMAGE_SOURCE = (new DamageSource("nympharium")).setDamageBypassesArmor();
    public NymphariumFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity){
            LivingEntity living = (LivingEntity) entityIn;
            if (!(entityIn instanceof DolphinEntity || entityIn instanceof FairyEntity || entityIn instanceof FeyFoxEntity)){
                // only actually damages twice a second
                living.attackEntityFrom(DAMAGE_SOURCE, 4);
            }
        } else {
            entityIn.addVelocity(0, 0.5D, 0);
        }
    }
}
