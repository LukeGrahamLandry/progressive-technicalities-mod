package com.LukeTheDuke9801.progressivetechnicalities.events;

import java.util.UUID;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
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
		boolean killedByPlayer = event.getSource().getTrueSource() instanceof PlayerEntity;
		if (!mob.isNonBoss() || !killedByPlayer || !(mob instanceof MonsterEntity)) {
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
    	
    	if (entity instanceof MonsterEntity && entity.isNonBoss()) {
    		MonsterEntity mob = (MonsterEntity) entity;
    		
    		/* Works perfectly for spawning new mobs but
    		 * throws java.lang.IllegalArgumentException: Modifier is already applied on this attribute!
    		 * when loading saved world because the entities are technicly joining the world but 
    		 * already have a modifier with this UUID from when they spawned
    		 */
    		try {
    			double val = Math.min(hours, 200);
        		AttributeModifier modifier = new AttributeModifier(UUID.fromString("131fa735-ff9a-45c8-8197-fa8e561829e0"), 
        				"ProgTechScalingMobs_attack", val, AttributeModifier.Operation.ADDITION);
        		mob.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(modifier);
        		
        		val = Math.min(hours, 300);
        		modifier = new AttributeModifier(UUID.fromString("131fa735-ff9a-45c8-8197-fa8e561829e1"), 
        				"ProgTechScalingMobs_health", val, AttributeModifier.Operation.ADDITION);
        		mob.getAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(modifier);
        		
        		val = Math.min(hours/5, 5); // 5% faster each hour caped at 5x 
        		modifier = new AttributeModifier(UUID.fromString("131fa735-ff9a-45c8-8197-fa8e561829e2"), 
        				"ProgTechScalingMobs_speed", val, AttributeModifier.Operation.MULTIPLY_BASE);
        		mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(modifier);
        		
        		val = Math.min(hours, 30); 
        		modifier = new AttributeModifier(UUID.fromString("131fa735-ff9a-45c8-8197-fa8e561829e3"), 
        				"ProgTechScalingMobs_armor", val, AttributeModifier.Operation.ADDITION);
        		mob.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(modifier);
        	
    	    	val = Math.max(0, Math.min(hours-10, 20)); 
    			modifier = new AttributeModifier(UUID.fromString("131fa735-ff9a-45c8-8197-fa8e561829e4"), 
    					"ProgTechScalingMobs_toughness", val, AttributeModifier.Operation.ADDITION);
    			mob.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).applyModifier(modifier);
    			

        		EntityType entityType = mob.getType();
        		
        		if (entityType == EntityType.CREEPER && hours >= 25) {
            		mob.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 1*60*20, 1));
        		}
        		
        		if (isInOilDim(mob)) {
        			mob.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 59*60*20, 1));
        		}
    		} catch (IllegalArgumentException e) {
    			// loading from save
    		}
    	}
    }
	
	private static boolean isOnPandora(MobEntity mob) {
		DimensionType pandoraDim = DimensionType.byName(ProgressiveTechnicalities.PANDORA_DIM_TYPE);
		DimensionType currentDim = mob.getEntityWorld().getDimension().getType();
		return currentDim == pandoraDim;
	}
	
	private static boolean isInOilDim(MobEntity mob) {
		DimensionType oilDim = DimensionType.byName(ProgressiveTechnicalities.OIL_DIM_TYPE);
		DimensionType currentDim = mob.getEntityWorld().getDimension().getType();
		return currentDim == oilDim;
	}
	
	public static String buffsAsString(World worldIn) {
		long ticks = worldIn.getGameTime(); 
    	long minutes = ticks/20/60;
    	int hours = (int) (minutes / 60);
    	
		String s = "Hours: " + String.valueOf(hours);
		return s;
	}
}
