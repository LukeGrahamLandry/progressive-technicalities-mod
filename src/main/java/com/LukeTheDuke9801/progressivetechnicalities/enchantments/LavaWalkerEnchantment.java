package com.LukeTheDuke9801.progressivetechnicalities.enchantments;

import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;

public class LavaWalkerEnchantment extends Enchantment{
	public LavaWalkerEnchantment(Rarity rarityIn, EnchantmentType enchantmentType, EquipmentSlotType[] slots) {
		super(rarityIn, enchantmentType, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public int getMinLevel() {
		return 1;
	}
	
	public boolean isTreasureEnchantment() {
      return true;
	}
	
	public boolean isAllowedOnBooks() {
      return false;
	}
	
	public int getMinEnchantability(int enchantmentLevel) {
	      return 999;
	   }
	
	public static void solidifyNearby(LivingEntity living, World worldIn, BlockPos pos, int level) {
	      if (living.onGround || true) {
	         BlockState blockstate = FluidInit.LAVA_ICE.get().getDefaultState();
	         float f = (float)Math.min(16, 2 + level);
	         BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

	         for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f))) {
	            if (blockpos.withinDistance(living.getPositionVec(), (double)f)) {
	               blockpos$mutable.setPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
	               BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable);
	               if (blockstate1.isAir(worldIn, blockpos$mutable)) {
	                  BlockState blockstate2 = worldIn.getBlockState(blockpos);
	                  boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.get(FlowingFluidBlock.LEVEL) == 0; 
	                  if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.isValidPosition(worldIn, blockpos) && worldIn.func_226663_a_(blockstate, blockpos, ISelectionContext.dummy()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(living, new net.minecraftforge.common.util.BlockSnapshot(worldIn, blockpos, blockstate2), net.minecraft.util.Direction.UP)) {
	                     worldIn.setBlockState(blockpos, blockstate);
	                     worldIn.getPendingBlockTicks().scheduleTick(blockpos,FluidInit.LAVA_ICE.get(), MathHelper.nextInt(living.getRNG(), 60, 120));
	                  }
	               }
	            }
	         }

	      }
	   }
}
