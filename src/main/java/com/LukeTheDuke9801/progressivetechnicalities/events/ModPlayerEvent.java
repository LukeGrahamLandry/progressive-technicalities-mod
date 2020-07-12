package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.LavaWalkerEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.OilFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.SilverFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.FeyFood;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.FeySteelArmorItem;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.LongFallBoots;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.PowerArmor;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.SpaceHelmet;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.VoidStriderLeggings;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD)
public class ModPlayerEvent {
	// Used for potion effects in liquids and enchantments 
    @SubscribeEvent
    public static void onTick(PlayerTickEvent event) {
    	PlayerEntity player = event.player;
    	World world = player.world;
    	
    	if (player.isInWater()) {
    		if (OilFluid.isInFluid(player)) {
    			OilFluid.applyFluidPotionEffects(event.player);
    		}
    		if (SilverFluid.isInFluid(player)) {
    			SilverFluid.applyFluidPotionEffects(event.player);
    		}
    	}
    	
    	int lavaWalkerLevel = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), player);
        if (lavaWalkerLevel > 0) {
        	LavaWalkerEnchantment.solidifyNearby(player, world, player.getPosition(), lavaWalkerLevel);
        	SilverFluid.solidifyNearby(player, world, player.getPosition(), lavaWalkerLevel);
        }
        
        int frostWalkerLevel = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FROST_WALKER, player);
        if (frostWalkerLevel > 0) {
        	OilFluid.solidifyNearby(player, world, player.getPosition(), frostWalkerLevel);
        }
        
        if (world.getDimension().getType() == DimensionType.byName(ProgressiveTechnicalities.ARRAKIS_DIM_TYPE)
        		|| world.getDimension().getType() == DimensionType.byName(ProgressiveTechnicalities.PANDORA_DIM_TYPE)){
        	boolean wearingSpaceHelmet = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof SpaceHelmet || (PowerArmor.getModuleLevel(player, "spacehelmet") == 1);
        	if (!wearingSpaceHelmet && !player.isCreative()) {
        		player.attackEntityFrom(DamageSource.DROWN, 6);
        	}
        }
    }
    
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
    	LivingEntity entity = event.getEntityLiving();
    	// Handle long fall boots
    	boolean isFallDamage = event.getSource() == DamageSource.FALL;
    	boolean wearingLongFallBoots = entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() instanceof LongFallBoots || (PowerArmor.getModuleLevel(entity, "longfall") == 1);
    	if (isFallDamage && wearingLongFallBoots) {
    		event.setCanceled(true);
    	}
    	
    	// Handle void strider
    	boolean belowBedrock = entity.getPosY() <= 0;
    	boolean wearingVoidStrider = entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() instanceof VoidStriderLeggings;
    	boolean isVoidDamage = event.getSource() == DamageSource.OUT_OF_WORLD; 
    	if (belowBedrock && wearingVoidStrider && isVoidDamage) {
    		event.setCanceled(true);
    	}
    	
    	// deal thorns damage from feysteel armour
    	Iterable<ItemStack> armor = entity.getArmorInventoryList();
    	int numFeysteel = 0;
    	for (ItemStack armorPiece : armor){ 
    		if (armorPiece.getItem() instanceof FeySteelArmorItem) {
    			numFeysteel += 1;
    		}
    	}
    	
    	if (PowerArmor.hasPowerArmor(entity)) {
    		numFeysteel = PowerArmor.getModuleLevel(entity, "thorns");
    	}
    	
    	Entity trueSource = event.getSource().getTrueSource();
    	if (trueSource instanceof LivingEntity) {
    		((LivingEntity)trueSource).attackEntityFrom(DamageSource.MAGIC, numFeysteel * 2);
    	}
    }
    
    // Makes vanilla food give no saturation
    @SubscribeEvent
    public static void onPlayerEatFood(LivingEntityUseItemEvent.Finish event) {
    	if (event.getItem().getItem().isFood() && event.getEntityLiving() instanceof PlayerEntity) {
    		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
    		boolean isFeyFood = event.getItem().getItem() instanceof FeyFood;
    		if (!isFeyFood) {
    			player.getFoodStats().setFoodSaturationLevel(0);
    			/* supposed to make it give two thirds of the food they're meant to 
    			 * but doesnt work properly doesnt let you get to full hunger cause it 
    			 * removes hunger after the hunger cap is applied
    			int itemFoodAmount = event.getItem().getItem().getFood().getHealing();
    			int foodToRemove = (itemFoodAmount / 3);
    			int newFoodLevel = player.getFoodStats().getFoodLevel() - foodToRemove;
    			player.getFoodStats().setFoodLevel(newFoodLevel);
    			*/
    		}
    	}
    }
    
    /*
     * supposed to make soulbound enchant work but doesnt work cause inventory is cleared before event fired
    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
    	if (event.isWasDeath()) {
    		NonNullList<ItemStack> mainInventory = event.getOriginal().inventory.mainInventory;
    		for (ItemStack stack : mainInventory) {
    			ProgressiveTechnicalities.LOGGER.debug(stack.toString());
    			if (SoulBoundEnchantment.hasSoulBound(stack)) {
    				event.getPlayer().addItemStackToInventory(stack);
    			}
    		}
    	}
    }
    
    @SubscribeEvent
    public static void onPlayerBreakBlock(PlayerEvent.HarvestCheck event) {
    	if (event.getTargetBlock().getBlock().equals(Blocks.STONE)) {
    		ItemStack held = event.getPlayer().getHeldItemMainhand();
    		if (held.getToolTypes().contains(ToolType.PICKAXE)) {
    			int harvestLevel = held.getHarvestLevel(ToolType.PICKAXE,  event.getPlayer(), event.getTargetBlock());
        		if (harvestLevel <= 3) {
        			// not carbide / titanium / etc.
        			held.damageItem(25, event.getPlayer(), null);
        		}
    		}
    	}
    }
    */
}