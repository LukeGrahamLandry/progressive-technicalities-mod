package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.JetPack1;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.JetPack2;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools.TitaniumAIOT;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
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

public class ItemRepairerBlock extends SimpleMachineBlock{
	public ItemRepairerBlock(Block.Properties builder) {
	      super(builder);
	   }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Right click with an item to repair it (takes power)"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   ItemStack handHeld = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		   
		   if (handHeld.isRepairable()) {
			   int xp = player.experienceTotal + getXPContainer(worldIn, pos).xp;
			   int canRepair = xp / 2;
			   int damage = handHeld.getDamage();
			   
			   if (canRepair > damage) {
				   int cost = damage * 2;
				   expendExperience(worldIn, pos, player, cost);
				   handHeld.setDamage(0);
				   return ActionResultType.SUCCESS;
			   } else {
				   expendExperience(worldIn, pos, player, xp);
				   handHeld.setDamage(damage - canRepair);
				   return ActionResultType.SUCCESS;
			   }
		   }
		   
		   return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	      
	   }

}