package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class TorchArrowEntity extends ArrowEntity{
	public TorchArrowEntity(EntityType<? extends ArrowEntity> type, World worldIn) {
	      super(type, worldIn);
    }

    public TorchArrowEntity(World worldIn, double x, double y, double z) {
       super(worldIn, x, y, z);
    }

    public TorchArrowEntity(World worldIn, LivingEntity shooter) {
       super(worldIn, shooter);
    }
    
    protected void onEntityHit(EntityRayTraceResult ray) {
    	super.onEntityHit(ray);
    	Entity entity = ray.getEntity();
        ((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.GLOWING, 200, 0));
    }

    protected void onHit(RayTraceResult raytraceResultIn) {
        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.onEntityHit((EntityRayTraceResult)raytraceResultIn);
        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceResultIn;
            BlockState blockstate = this.world.getBlockState(blockraytraceresult.getPos());
            blockstate.onProjectileCollision(this.world, blockstate, blockraytraceresult, this);

            setToTorch(blockraytraceresult);
            this.remove();
        }
    }

    protected void setToTorch(BlockRayTraceResult blockraytraceresult) {
        Direction direction = blockraytraceresult.getFace();
        BlockPos torchPos = blockraytraceresult.getPos().offset(direction);

        if (direction == Direction.DOWN || !this.world.getBlockState(torchPos).isAir(null, null)){
            ItemEntity itementity = new ItemEntity(world, torchPos.getX(), torchPos.getY(), torchPos.getZ(), new ItemStack(Items.TORCH));
            world.addEntity(itementity);
        } else if (direction == Direction.UP) {
            this.world.setBlockState(torchPos, Blocks.TORCH.getDefaultState());
        }  else {
            this.world.setBlockState(torchPos, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, direction));
        }
    }
}
