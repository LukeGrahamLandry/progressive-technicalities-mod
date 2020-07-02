package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.LavaWalkerEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.OilFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.SilverFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.FeySteelArmorItem;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
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
    }
    
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
    	// deal thorns damage from feysteel armour
    	Iterable<ItemStack> armor = event.getEntityLiving().getArmorInventoryList();
    	int numFeysteel = 0;
    	for (ItemStack armorPiece : armor){ 
    		if (armorPiece.getItem() instanceof FeySteelArmorItem) {
    			numFeysteel += 1;
    		}
    	}
    	
    	Entity trueSource = event.getSource().getTrueSource();
    	if (trueSource instanceof LivingEntity) {
    		((LivingEntity)trueSource).attackEntityFrom(DamageSource.MAGIC, numFeysteel * 2);
    	}
    }
    
    
}