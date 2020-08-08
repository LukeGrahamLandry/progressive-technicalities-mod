package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.mob_spawner;

import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.MagicEggShell;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class MobSpawnerBlock extends Block{
	
	public MobSpawnerBlock(Properties propertises) {
		super(propertises);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Spawns monsters (requires power). Set type with a Magic Eggshell. "));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.MOB_SPAWNER.get().create();
	}

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   ItemStack handHeld = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		   if (handHeld.getItem() instanceof MagicEggShell) {
			   EntityType mobType = MagicEggShell.getType(handHeld);
			   if (mobType != null) {
				   ((MobSpawnerTileEntity) worldIn.getTileEntity(pos)).setMobType(mobType);
				   MagicEggShell.setEmpty(handHeld);
				   return ActionResultType.SUCCESS;
			   }
		   }
		   return ActionResultType.FAIL;
	   }
}
