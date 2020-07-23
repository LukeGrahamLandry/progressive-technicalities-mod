package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.LavaWalkerEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.SoulBoundEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.OilFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.SilverFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.FeyFood;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.EarthGemArmor;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.FeySteelArmorItem;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.FireGemArmor;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.LongFallBoots;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.ModularArmor;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.SpaceHelmet;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.VoidStriderLeggings;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
        }
        
        int frostWalkerLevel = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FROST_WALKER, player);
        if (frostWalkerLevel > 0) {
        	OilFluid.solidifyNearby(player, world, player.getPosition(), frostWalkerLevel);
        }
        
        /* How air works:
         * Full bar is 300, each tick while under water it deceases by one
         * if its -20, you get damages and set to 0 (so damage once a second)
         * regain 4 each tick while in air
         * because reasons if you want it to go down while in air you have to reduce by 3 per tick
         */
        
        boolean isInSpace = player.getPosY() >= 300
        		|| world.getDimension().getType() == DimensionType.byName(ProgressiveTechnicalities.PANDORA_DIM_TYPE)
        		|| world.getDimension().getType() == DimensionType.byName(ProgressiveTechnicalities.ARRAKIS_DIM_TYPE);
        if (isInSpace){
        	boolean wearingSpaceHelmet = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof SpaceHelmet || (ModularArmor.getModuleLevel(player, "spacehelmet") == 1);
        	if (!wearingSpaceHelmet && !player.isCreative()) {
        		player.setAir(player.getAir() - 3);
        		if (player.getAir() <= -20) {
    	        	player.setAir(0);
    	        	player.attackEntityFrom(DamageSource.DROWN, 4);
    	        } 
        	}
        }
    }
    
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
    	LivingEntity entity = event.getEntityLiving();
    	
    	// Handle long fall boots
    	boolean isFallDamage = event.getSource() == DamageSource.FALL;
    	boolean wearingLongFallBoots = entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() instanceof LongFallBoots || (ModularArmor.getModuleLevel(entity, "longfall") == 1);
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
    	if (ModularArmor.hasFullSet(entity)) {
    		numFeysteel = ModularArmor.getModuleLevel(entity, "thorns");
    	}
    	Entity trueSource = event.getSource().getTrueSource();
    	if (trueSource instanceof LivingEntity && numFeysteel > 0) {
    		((LivingEntity)trueSource).attackEntityFrom(DamageSource.causeThornsDamage(entity), numFeysteel * 2);
    	}
    	
    	// Handle fire thorns from fire armor
    	boolean hasFireArmor = FireGemArmor.hasFullSet(entity);
    	if (hasFireArmor) {
    		if (trueSource instanceof LivingEntity) {
        		((LivingEntity)trueSource).setFire(5);
        	}
    	}
    	
    	// Handle slowness thorns from earth armor
    	boolean hasEarthArmor = EarthGemArmor.hasFullSet(entity);
    	if (hasEarthArmor) {
    		if (trueSource instanceof LivingEntity) {
        		((LivingEntity)trueSource).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1));
        	}
    	}
    	
    	// Handle advanced life steal charm
    	if (trueSource instanceof PlayerEntity) {
    		boolean hasLifeStealCharm = ((PlayerEntity) trueSource).inventory.hasItemStack(new ItemStack(ItemInit.ADVANCED_LIFESTEAL_CHARM.get()));
    		if (hasLifeStealCharm) {
    			int toHeal = (int) (event.getAmount() / 4);
    			((PlayerEntity) trueSource).heal(toHeal);
    		}
    	}
    }
    
    // Makes vanilla food give no saturation
    @SubscribeEvent
    public static void onPlayerEatFood(LivingEntityUseItemEvent.Finish event) {
    	if (event.getItem().getItem().isFood() && event.getEntityLiving() instanceof PlayerEntity) {
    		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
    		boolean isFeyFood = FeyFood.isFeyFood(event.getItem().getItem());
    		if (!isFeyFood) {
    			player.getFoodStats().setFoodSaturationLevel(0);
    		}
    	}
    }
    
    
    // holds items for soulbound // TODO: make it player specific
    private static NonNullList<ItemStack> soulboundItems = NonNullList.create();
    
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
    	// Handle basic life steal charm
    	Entity source = event.getSource().getTrueSource();
    	if (source instanceof PlayerEntity) {
    		boolean hasLifeStealCharm = ((PlayerEntity) source).inventory.hasItemStack(new ItemStack(ItemInit.BASIC_LIFESTEAL_CHARM.get()));
    		if (hasLifeStealCharm) {
    			((PlayerEntity) source).heal(4);
    		}
    	}
    	
    	// handle soulbound enchant
    	if (event.getEntityLiving() instanceof PlayerEntity) {
    		PlayerEntity player = (PlayerEntity)event.getEntityLiving();
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
    	if (soulboundItems.size() == 0) return;
    	
    	if (event.getEntity() instanceof PlayerEntity) {
    		for (ItemStack stack : soulboundItems) {
    			((PlayerEntity) event.getEntity()).addItemStackToInventory(stack);
    		}
    		soulboundItems = NonNullList.create();
    	}
    }
}