package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.LavaWalkerEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.OilFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.SilverFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.FeySteelArmorItem;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.LongFallBoots;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.PowerArmor;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.SpaceHelmet;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
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
    
    @SubscribeEvent
    public static void onLivingSpawn(LivingSpawnEvent event) {
    	// long time = ((World) event.getWorld()).getGameTime(); // net.minecraft.world.gen.WorldGenRegion cannot be cast to net.minecraft.world.World
    	EntityType entityType = event.getEntityLiving().getType();
    	if (entityType == EntityType.ZOMBIE) {
    		event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemInit.CARBIDE_AXE.get()));
    		event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
    		event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.IRON_LEGGINGS));
    		event.getEntityLiving().setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.IRON_BOOTS));
    	}
    }
    
    
}