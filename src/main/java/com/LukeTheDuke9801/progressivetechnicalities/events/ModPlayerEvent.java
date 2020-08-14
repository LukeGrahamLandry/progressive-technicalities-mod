package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;

import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.HitEventListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD)
public class ModPlayerEvent {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
    	LivingEntity entity = event.getEntityLiving();

    	handleArmorOnHitEffects(entity, event);

		Entity trueSource = event.getSource().getTrueSource();

    	// Handle advanced life steal charm
    	if (trueSource instanceof PlayerEntity) {
    		boolean hasLifeStealCharm = ((PlayerEntity) trueSource).inventory.hasItemStack(new ItemStack(ItemInit.ADVANCED_LIFESTEAL_CHARM.get()));
    		if (hasLifeStealCharm) {
    			int toHeal = (int) (event.getAmount() / 4);
    			((PlayerEntity) trueSource).heal(toHeal);
    		}
    	}
    }

	private static void handleArmorOnHitEffects(LivingEntity entity, LivingHurtEvent event) {
		for (ItemStack stack : entity.getArmorInventoryList()){
			Item item = stack.getItem();
			if (item instanceof HitEventListener){
				HitEventListener listener = (HitEventListener) item;
				listener.onWearerHit(event);
			}
		}
	}

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
	}
}