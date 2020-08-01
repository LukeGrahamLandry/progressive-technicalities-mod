package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.LavaWalkerEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.SoulBoundEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.OilFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.NymphariumFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.FeyFood;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.*;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
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