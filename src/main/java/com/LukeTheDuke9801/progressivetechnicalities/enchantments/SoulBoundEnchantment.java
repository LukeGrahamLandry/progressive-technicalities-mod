package com.LukeTheDuke9801.progressivetechnicalities.enchantments;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.HashMap;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = ProgressiveTechnicalities.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class SoulBoundEnchantment extends Enchantment{
	public SoulBoundEnchantment(Rarity rarityIn, EnchantmentType enchantmentType, EquipmentSlotType[] slots) {
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
	
	public boolean isTreasureEnchantment() {
      return true;
	}
	
	public boolean isAllowedOnBooks() {
      return false;
	}
	
	public int getMinEnchantability(int enchantmentLevel) {
	      return 999;
	   }
	
	public static boolean hasSoulBound(ItemStack stack) {
    	ListNBT enchants = stack.getEnchantmentTagList();
		for (int i=0;i<enchants.size();i++) {
			CompoundNBT enchant = enchants.getCompound(i);
		    String id = enchant.getString("id");
		    Enchantment enchantment = Registry.ENCHANTMENT.getValue(new ResourceLocation(id)).get();
		    if (enchantment instanceof SoulBoundEnchantment) {
		    	return true;
		    }
		}
		return false;
    }

    private static HashMap<String, NonNullList<ItemStack>> soulboundItemsMap = new HashMap<String, NonNullList<ItemStack>>();

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntityLiving();
			String name = player.getName().getUnformattedComponentText();
			NonNullList<ItemStack> soulboundItems = getPlayersSoulboundItems(name);

			NonNullList<ItemStack> mainInventory = player.inventory.mainInventory;
			int i = 0;
			for (ItemStack stack : mainInventory) {
				if (SoulBoundEnchantment.hasSoulBound(stack)) {
					soulboundItems.add(stack);
					player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
				}
				i++;
			}

			NonNullList<ItemStack> armorInventory = player.inventory.armorInventory;
			i = 0;
			for (ItemStack stack : armorInventory) {
				if (SoulBoundEnchantment.hasSoulBound(stack)) {
					soulboundItems.add(stack);
					player.inventory.armorInventory.set(i, ItemStack.EMPTY);
				}
				i++;
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerSpawn(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntity();
			String name = player.getName().getUnformattedComponentText();
			NonNullList<ItemStack> soulboundItems = getPlayersSoulboundItems(name);

			if (soulboundItems.size() == 0) return;

			for (ItemStack stack : soulboundItems) {
				((PlayerEntity) event.getEntity()).addItemStackToInventory(stack);
			}

			soulboundItemsMap.remove(name);
		}
	}

	private static NonNullList<ItemStack> getPlayersSoulboundItems(String name){
		if (!soulboundItemsMap.containsKey(name)){  // should always be true cause its removed when you respawn
			soulboundItemsMap.put(name, NonNullList.create());
		}
		return soulboundItemsMap.get(name);
	}
}
