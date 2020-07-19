package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines;

import java.util.Optional;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools.TitaniumAIOT;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class DisenchanterBlock extends SimpleMachineBlock{
	public static final int cost = 20;
	public DisenchanterBlock(Block.Properties builder) {
	      super(builder);
	   }
	   
	   
	   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   ItemStack handHeld = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		   if (!worldIn.isRemote && handHeld.isEnchanted()) {
			   ListNBT enchants = handHeld.getEnchantmentTagList();
			   
			   boolean success = expendExperience(worldIn, pos, player, this.cost * enchants.size());
			   if (!success) return ActionResultType.FAIL;
			   
			   for (int i=0;i<enchants.size();i++) {
				   CompoundNBT enchant = enchants.getCompound(i);
				   String id = enchant.getString("id");
				   int level = enchant.getInt("lvl");
				   Enchantment enchantment = Registry.ENCHANTMENT.getValue(new ResourceLocation(id)).get();
				   EnchantmentData enchantData = new EnchantmentData(enchantment, level);
				   ItemStack result = new ItemStack(Items.ENCHANTED_BOOK);
				   EnchantedBookItem.addEnchantment(result, enchantData);
				   
				   ItemEntity itementity = new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), result);
				   itementity.setDefaultPickupDelay();
				   worldIn.addEntity(itementity);
			   }
			   
			   handHeld.removeChildTag("Enchantments");
			   
			   return ActionResultType.SUCCESS;
			   
		   }
		   return ActionResultType.FAIL;
	      
	   }

	}