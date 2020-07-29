package com.LukeTheDuke9801.progressivetechnicalities.entities;

import java.util.EnumSet;

import javax.annotation.Nullable;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.ModVillagerTrades;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtCustomerGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookAtWithoutMovingGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.TradeWithPlayerGoal;
import net.minecraft.entity.ai.goal.UseItemGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.entity.monster.IllusionerEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class WanderingGemSmith extends AbstractWanderer {
    public WanderingGemSmith(EntityType<? extends WanderingGemSmith> type, World worldIn) {
        super(type, worldIn);
        this.setCustomName(new StringTextComponent("Gem Smith"));

        this.sells = new VillagerTrades.ITrade[]{
                // item, num emeralds to input, num items to output, max uses, priceMultiplierIn
                new ModVillagerTrades.ItemsForEmeraldsTrade(Items.DIAMOND, 24, 1, Integer.MAX_VALUE, 1)
        };

        this.buys = new VillagerTrades.ITrade[]{
                // item, num items to input, num emeralds to output, max uses, ex given
                new ModVillagerTrades.EmeraldsForItemsTrade(ItemInit.FIRE_GEM.get(), 1, 8, Integer.MAX_VALUE, 5),
                new ModVillagerTrades.EmeraldsForItemsTrade(ItemInit.EARTH_GEM.get(), 1, 8, Integer.MAX_VALUE, 5),
                new ModVillagerTrades.EmeraldsForItemsTrade(ItemInit.WATER_GEM.get(), 1, 8, Integer.MAX_VALUE, 5),
                new ModVillagerTrades.EmeraldsForItemsTrade(ItemInit.AIR_GEM.get(), 1, 8, Integer.MAX_VALUE, 5)
        };
    }
}
