package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.client.entity.BreathesInSpace;
import com.LukeTheDuke9801.progressivetechnicalities.enchantments.LavaWalkerEnchantment;
import com.LukeTheDuke9801.progressivetechnicalities.init.EnchantmentInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.NymphariumFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.OilFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.ModularArmor;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.SpaceHelmet;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD)
public class ModLivingTickEvent {
	// Used for dying in space, potion effects in liquids and enchantments
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
			LivingEntity entity = event.getEntityLiving();
			World world = entity.getEntityWorld();

			if (entity.isInWater()) {
				if (OilFluid.isInFluid(entity)) {
					OilFluid.applyFluidPotionEffects(entity);
				}
				if (NymphariumFluid.isInFluid(entity)) {
					NymphariumFluid.applyFluidPotionEffects(entity);
				}
			}

			int lavaWalkerLevel = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentInit.LAVA_WALKER.get(), entity);
			if (lavaWalkerLevel > 0) {
				LavaWalkerEnchantment.solidifyNearby(entity, world, entity.getPosition(), lavaWalkerLevel);
			}

			int frostWalkerLevel = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FROST_WALKER, entity);
			if (frostWalkerLevel > 0) {
				OilFluid.solidifyNearby(entity, world, entity.getPosition(), frostWalkerLevel);
			}

			/* How air works:
			 * Full bar is 300, each tick while under water it deceases by one
			 * if its -20, you get damages and set to 0 (so damage once a second)
			 * regain 4 each tick while in air
			 */

			boolean isInSpace = entity.getPosY() >= 300
					|| world.getDimension().getType() == DimensionType.byName(ProgressiveTechnicalities.PANDORA_DIM_TYPE)
					|| world.getDimension().getType() == DimensionType.byName(ProgressiveTechnicalities.ARRAKIS_DIM_TYPE)
					|| world.getDimension().getType() == DimensionType.byName(ProgressiveTechnicalities.LUNA_DIM_TYPE);
			if (isInSpace){
				boolean wearingSpaceHelmet = entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof SpaceHelmet || (ModularArmor.getModuleLevel(entity, "spacehelmet") == 1);
				boolean isCreative = entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative();
				if (!wearingSpaceHelmet && !isCreative && !(entity instanceof BreathesInSpace)) {
					entity.setAir(entity.getAir() - 5);
					if (entity.getAir() <= -20) {
						entity.setAir(0);
						entity.attackEntityFrom(DamageSource.DROWN, 4);
					}
				}
			}
    }

}