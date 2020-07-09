package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.enchantment_forge;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EnchantmentForgeBlock extends FallingBlock {
	   public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	   private static final TranslationTextComponent field_220273_k = new TranslationTextComponent("container.enchantment_forge");

	   public EnchantmentForgeBlock(Block.Properties properties) {
	      super(properties);
	      this.setDefaultState(this.stateContainer.getBaseState());
	   }

	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	      if (worldIn.isRemote) {
	         return ActionResultType.SUCCESS;
	      } else {
	         player.openContainer(state.getContainer(worldIn, pos));
	         player.addStat(Stats.INTERACT_WITH_ANVIL);
	         return ActionResultType.SUCCESS;
	      }
	   }

	   @Nullable
	   public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
	      return new SimpleNamedContainerProvider((p_220272_2_, p_220272_3_, p_220272_4_) -> {
	         return new EnchantmentForgeContainer(p_220272_2_, p_220272_3_, IWorldPosCallable.of(worldIn, pos));
	      }, field_220273_k);
	   }

	   protected void onStartFalling(FallingBlockEntity fallingEntity) {
	      fallingEntity.setHurtEntities(true);
	   }

	   public void onEndFalling(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState) {
	      worldIn.playEvent(1031, pos, 0);
	   }

	   public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
	      return false;
	   }
	}