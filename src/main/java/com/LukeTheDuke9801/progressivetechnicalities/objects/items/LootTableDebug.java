package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.XPContainer;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.AbstractGenoratorTileEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.List;
import java.util.Random;

public class LootTableDebug extends Item{
	public LootTableDebug(Properties properties) {
		super(properties);
	}


	public ActionResultType onItemUse(ItemUseContext context) {
		if (!KeyboardHelper.isHoldingShift()) return ActionResultType.SUCCESS;

		World world = context.getWorld();
		BlockPos pos = context.getPos();

		TileEntity tileentity = world.getTileEntity(pos);
		if (tileentity instanceof ChestTileEntity) {
			ResourceLocation ltable = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "chest/sky_island");
			((ChestTileEntity)tileentity).setLootTable(ltable, (new Random()).nextLong());
		}

		return ActionResultType.SUCCESS;
	}
}
