package com.LukeTheDuke9801.progressivetechnicalities.enchantments;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class BridgingEnchantment extends Enchantment{
	public BridgingEnchantment(Rarity rarityIn, EnchantmentType enchantmentType, EquipmentSlotType[] slots) {
		super(rarityIn, enchantmentType, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public int getMinLevel() {
		return 1;
	}
	
	@Mod.EventBusSubscriber(modid=ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD)
	public static class AutojumpEquiped{
		@SubscribeEvent
		public static void doStuff (PlayerTickEvent event) {
			PlayerEntity playerIn = event.player;
			World worldIn = playerIn.world;
			
			if (playerIn.getItemStackFromSlot(EquipmentSlotType.FEET) != ItemStack.EMPTY) {
				if (playerIn.isCrouching()) {
					if (worldIn.getBlockState(playerIn.getPosition().down()) == Blocks.BARRIER.getDefaultState()) {
						worldIn.setBlockState(playerIn.getPosition().down(), Blocks.AIR.getDefaultState());
						worldIn.setBlockState(playerIn.getPosition().down().down(), Blocks.BARRIER.getDefaultState());
					}
					
				} else {
					if (worldIn.getBlockState(playerIn.getPosition().down()) == Blocks.AIR.getDefaultState()) {
						worldIn.setBlockState(playerIn.getPosition().down(), Blocks.BARRIER.getDefaultState());
						if (worldIn.getBlockState(playerIn.getPosition().down().down()) == Blocks.BARRIER.getDefaultState()) {
							worldIn.setBlockState(playerIn.getPosition().down().down(), Blocks.AIR.getDefaultState());
						}
					}
				}
			}
		}
	}
}
