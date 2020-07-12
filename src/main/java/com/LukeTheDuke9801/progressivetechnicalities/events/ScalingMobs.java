package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD)
public class ScalingMobs {
	// Make them drop things
	@SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event) {
		LivingEntity mob = event.getEntityLiving();
		boolean isMonster = mob instanceof MonsterEntity;
		if (!isMonster) {
			return;
		}
		long ticks = mob.getEntityWorld().getGameTime(); 
    	long minutes = ticks/20/60;
    	int hours = (int) (minutes / 60);
    	int percentile = mob.getRNG().nextInt(10); // 0 - 9 inclusive
    	
    	Item result = Items.AIR;
    	if (hours >= 5) {
    		if (percentile < 2) {
    			result = Items.DIAMOND_ORE;
    		}
    	}
    	if (hours >= 10) {
    		if (percentile >= 2 && percentile < 4) {
    			result = ItemInit.XP_CHARGE.get();
    		}
    	}
    	if (hours >= 20) {
    		if (percentile >= 4 && percentile < 6) {
    			result = ItemInit.TITANIUM_INGOT.get();
    		}
    	}
    	if (hours >= 30) {
    		if (percentile >= 6 && percentile < 8) {
    			result = ItemInit.UNOBTANIUM_SHARD.get();
    		}
    	}
    	if (hours >= 35) {
    		if (percentile >= 8 && percentile < 10) {
    			result = ItemInit.GREATER_XP_CHARGE.get();
    		}
    	}
    	
    	dropItem(result, mob);
	}
	
	public static void dropItem(Item item, LivingEntity mob) {
		World world = mob.getEntityWorld();
		ItemEntity itementity = new ItemEntity(world, mob.getPosX(), mob.getPosY(), mob.getPosZ(), new ItemStack(item));
	    itementity.setDefaultPickupDelay();
	    world.addEntity(itementity);
	}
	
	
	// Make them stronger
	@SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {
    	long ticks = event.getWorld().getGameTime(); 
    	long minutes = ticks/20/60;
    	int hours = (int) (minutes / 60);
    	
    	Entity entity = event.getEntity();
    	
    	if (entity instanceof MobEntity) {
    		MobEntity mob = (MobEntity) entity;
    		EntityType entityType = mob.getType();
    		boolean alreadyHasPotions = false;
    		
    		if (entityType == EntityType.ZOMBIE || entityType == EntityType.HUSK || entityType == EntityType.DROWNED || entityType == EntityType.WITHER_SKELETON) {
    			applyArmor(hours, mob);
    			applySword(hours, mob);
    			
    			applyPotionEffects(hours-10, mob);
    			alreadyHasPotions = true;
    			
        		zeroAllDropChances(mob);
        	} else if (entityType == EntityType.SKELETON) {
        		applyArmor(hours, mob);
        		applyBow(hours, mob);
        		
        		applyPotionEffects(hours-10, mob);
        		alreadyHasPotions = true;
        		
        		zeroAllDropChances(mob);
        	} else if (entityType == EntityType.CREEPER && hours >= 25) {
        		mob.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 1*60*20, 1));
        		
        	} else if (entityType == EntityType.SPIDER || entityType == EntityType.CAVE_SPIDER
        			|| entityType == EntityType.MAGMA_CUBE || entityType == EntityType.SLIME) {
        		applyStrength(hours, mob);
        		
        		applyPotionEffects(hours-10, mob);
        		alreadyHasPotions = true;
        	} else if (entityType == EntityType.IRON_GOLEM || entityType == EntityType.WOLF) {
        		applyStrength(hours, mob);
        		
        		applyPotionEffects(hours-10, mob);
        		alreadyHasPotions = true;
        	} else if (entityType == EntityType.SNOW_GOLEM && hours >= 25) {
        		mob.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 60*60*20, 1));
        	}
    		
    		if (mob instanceof MonsterEntity && !alreadyHasPotions && entityType != EntityType.CREEPER) {
    			applyPotionEffects(hours, mob);
    		}
    	}
    }
	
	private static void applyArmor(int hours, MobEntity mob) {
		Item chest = Items.AIR;
		Item legs = Items.AIR;
		Item feet = Items.AIR;
		
		if (hours == 0) {
			return;
		} else if (hours == 1) {
			chest = Items.LEATHER_CHESTPLATE;
			legs = Items.LEATHER_LEGGINGS;
			feet = Items.LEATHER_BOOTS;
		} else if (hours == 2) {
			chest = Items.CHAINMAIL_CHESTPLATE;
			legs = Items.CHAINMAIL_LEGGINGS;
			feet = Items.CHAINMAIL_BOOTS;
		} else if (hours == 3) {
			chest = Items.IRON_CHESTPLATE;
			legs = Items.IRON_LEGGINGS;
			feet = Items.IRON_BOOTS;
		} else if (hours == 4) {
			chest = Items.DIAMOND_CHESTPLATE;
			legs = Items.DIAMOND_LEGGINGS;
			feet = Items.DIAMOND_BOOTS;
		} else {
			chest = ItemInit.CARBIDE_CHESTPLATE.get();
			legs = ItemInit.CARBIDE_LEGGINGS.get();
			feet = ItemInit.CARBIDE_BOOTS.get();
		}
		
		if (hours >= 6) {
			Item head = ItemInit.CARBIDE_HELMET.get();
			mob.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(head));
		}
		
		ItemStack chestStack = new ItemStack(chest);
		ItemStack legsStack = new ItemStack(legs);
		ItemStack feetStack = new ItemStack(feet);
		
		if (hours >= 7) {
			chestStack.addEnchantment(Enchantments.PROTECTION, 4);
			legsStack.addEnchantment(Enchantments.PROTECTION, 4);
			feetStack.addEnchantment(Enchantments.PROTECTION, 4);
		}
		
		if (hours >= 8) {
			chestStack.addEnchantment(Enchantments.THORNS, 3);
			legsStack.addEnchantment(Enchantments.THORNS, 3);
			feetStack.addEnchantment(Enchantments.THORNS, 3);
		}
		
		if (hours >= 9) {
			feetStack.addEnchantment(Enchantments.FEATHER_FALLING, 4);
			feetStack.addEnchantment(Enchantments.DEPTH_STRIDER, 3);
		}
		
		mob.setItemStackToSlot(EquipmentSlotType.CHEST, chestStack);
		mob.setItemStackToSlot(EquipmentSlotType.LEGS, legsStack);
		mob.setItemStackToSlot(EquipmentSlotType.FEET, feetStack);
	}
	
	private static void applySword(int hours, MobEntity mob) {
		Item sword = Items.AIR;
		
		if (hours == 0) {
			return;
		} else if (hours == 1) {
			sword = Items.WOODEN_SWORD;
		} else if (hours == 2) {
			sword = Items.STONE_SWORD;
		} else if (hours == 3) {
			sword = Items.IRON_SWORD;
		} else if (hours == 4) {
			sword = Items.DIAMOND_SWORD;
		} else {
			sword = ItemInit.CARBIDE_SWORD.get();
		}
		
		ItemStack swordStack = new ItemStack(sword);
		
		if (hours >= 6) {
			int sharpnessLevel = Math.min(hours-5, 5);
			swordStack.addEnchantment(Enchantments.SHARPNESS, sharpnessLevel);
		}
		
		if (hours >= 11) {
			swordStack.addEnchantment(Enchantments.FIRE_ASPECT, 2);
		}
		
		mob.setHeldItem(Hand.MAIN_HAND, swordStack);
		
		if (hours >= 12) {
			applyStrength(hours-8, mob);
		}
	}
	
	private static void applyBow(int hours, MobEntity mob) {
		ItemStack bow = mob.getHeldItemMainhand();
		
		if (hours == 0) {
			return;
		} else {
			int powerLevel = Math.min(hours, 5);
			bow.addEnchantment(Enchantments.POWER, powerLevel);
		}
		
		if (hours >= 6) {
			bow.addEnchantment(Enchantments.FLAME, 2);
		}
		
		if (hours == 7) {
			bow.addEnchantment(Enchantments.PUNCH, 1);
		} else if (hours >= 8) {
			bow.addEnchantment(Enchantments.PUNCH, 2);
		}

		if (hours >= 10) {
			ItemStack arrow = new ItemStack(Items.TIPPED_ARROW);
			PotionUtils.addPotionToItemStack(arrow, Potions.POISON);
			mob.setHeldItem(Hand.OFF_HAND, arrow);
		} 
	}
	
	private static void applyPotionEffects(int time, MobEntity mob) {
		if (time <= 0) {
			return;
		}
		int speedLevel = Math.min(time, 4);
		mob.addPotionEffect(new EffectInstance(Effects.SPEED, 30*60*20, speedLevel));
		
		if (time <= 9) {
			return;
		}
		int regenLevel = Math.min(time-9, 0);
		mob.addPotionEffect(new EffectInstance(Effects.REGENERATION, 30*60*20, regenLevel));
		
		if (time >= 10) {
			int absorptionLevel = Math.min(time-12, 10);
			mob.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 30*60*20, absorptionLevel));
		}
		
		if (time >= 15) {
			mob.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 30*60*20, 0));
		}
	}
	
	private static void applyStrength(int level, MobEntity mob) {
		level = Math.min(level, 10);
		mob.addPotionEffect(new EffectInstance(Effects.STRENGTH, 30*60*20, level));
	}
    
    private static void zeroAllDropChances(MobEntity mob) {
    	mob.setDropChance(EquipmentSlotType.MAINHAND, 0f);
    	mob.setDropChance(EquipmentSlotType.OFFHAND, 0f);
    	mob.setDropChance(EquipmentSlotType.HEAD, 0f);
    	mob.setDropChance(EquipmentSlotType.CHEST, 0f);
    	mob.setDropChance(EquipmentSlotType.LEGS, 0f);
    	mob.setDropChance(EquipmentSlotType.FEET, 0f);
    }
    
    
}
