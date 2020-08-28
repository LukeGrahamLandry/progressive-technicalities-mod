package com.LukeTheDuke9801.progressivetechnicalities.entities;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.goals.MoveToItemGoal;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.ModVillagerTrades;
import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.BreathesInSpace;
import com.google.common.collect.Sets;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class FremanEntity extends VindicatorEntity implements BreathesInSpace, IMerchant {
    private NonNullList<ItemStack> lootInventory;
    private PlayerEntity customer;
    protected VillagerTrades.ITrade[] sells;
    private MerchantOffers offers;

    public FremanEntity(EntityType<? extends FremanEntity> type, World worldIn) {
        super(type, worldIn);
        this.lootInventory = NonNullList.create();
        this.offers = getOffers();
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemInit.ARRAKIS_KNIFE.get()));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (entityIn instanceof LivingEntity){
            // todo: add posion to sword when droped and used by player
            ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
        }

        return super.attackEntityAsMob(entityIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(3, new AbstractRaiderEntity.FindTargetGoal(this, 10.0F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, true));
        this.goalSelector.addGoal(5, new MoveToItemGoal(this, 0.8D));
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, AnimalEntity.class, 3.0F));
    }

    @Override
    public void livingTick() {
        super.livingTick();

        // pickup everything it can
        if (!this.world.isRemote && this.isAlive() && !this.dead) {
            for(ItemEntity itementity : this.world.getEntitiesWithinAABB(ItemEntity.class, this.getBoundingBox().grow(1.0D, 0.0D, 1.0D))) {
                if (!itementity.removed && !itementity.getItem().isEmpty() && !itementity.cannotPickup()) {
                    this.lootInventory.add(itementity.getItem());
                    itementity.remove();
                }
            }
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        for (ItemStack item : this.lootInventory){
            ItemEntity entity = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), item);
            entity.setDefaultPickupDelay();
            this.world.addEntity(entity);
        }
        super.onDeath(cause);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        ListNBT items = new ListNBT();
        for (ItemStack item : this.lootInventory){
            items.add(item.write(new CompoundNBT()));
        }
        compound.put("lootInventory", items);

        MerchantOffers merchantoffers = this.getOffers();
        if (!merchantoffers.isEmpty()) {
            compound.put("Offers", merchantoffers.write());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        ListNBT items = compound.getList("lootInventory", getTypeIntThingy());
        for (INBT nbt : items){
            this.lootInventory.add(ItemStack.read((CompoundNBT) nbt));
        }

        if (compound.contains("Offers", 10)) {
            this.offers = new MerchantOffers(compound.getCompound("Offers"));
        }
    }

    // I REALLY SHOULDNT NEED THIS SHIT
    private int getTypeIntThingy() {
        NonNullList<ItemStack> thingy = NonNullList.create();
        thingy.add(new ItemStack(Items.DIAMOND, 5));
        thingy.add(new ItemStack(Items.DIAMOND, 5));

        ListNBT items = new ListNBT();
        for (ItemStack item : thingy){
            items.add(item.write(new CompoundNBT()));
        }

        int i = items.getTagType();
        ProgressiveTechnicalities.LOGGER.debug("TAG TYPE: " + String.valueOf(i));
        return i;
    }

    // VILLAGER STUFF
    public boolean processInteract(PlayerEntity player, Hand hand) {
        if (this.isAlive() && !this.hasCustomer() && !this.isChild()) {
            if (hand == Hand.MAIN_HAND) {
                player.addStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return super.processInteract(player, hand);
            } else {
                if (!this.world.isRemote) {
                    this.setCustomer(player);
                    this.openMerchantContainer(player, this.getDisplayName(), 1);
                }

                return true;
            }
        } else {
            return super.processInteract(player, hand);
        }
    }

    private boolean hasCustomer() {
        return this.customer != null;
    }

    protected void populateTradeData() {
        this.offers = new MerchantOffers();

        this.sells = new VillagerTrades.ITrade[lootInventory.size()];
        for (int i=0;i<lootInventory.size();i++){
            this.sells[i] = new ModVillagerTrades.FremanSellTrade(lootInventory.get(i));
        }

        this.addTrades(this.offers, this.sells, Integer.MAX_VALUE);  // last arg is max displayed trades
    }

    public void setCustomer(@Nullable PlayerEntity player) {
        this.customer = player;
    }

    @Nullable
    @Override
    public PlayerEntity getCustomer() {
        return this.customer;
    }

    @Override
    public MerchantOffers getOffers() {
        this.populateTradeData();

        return this.offers;
    }

    @Override
    public void setClientSideOffers(@Nullable MerchantOffers offers) {

    }

    @Override
    public void onTrade(MerchantOffer offer) {
        if (this.world.isRemote) return;
        ItemStack stack = offer.getSellingStack(); // what it is giving
        int i = lootInventory.lastIndexOf(stack);
        if (i != -1) lootInventory.remove(i);  // should always be true
    }

    @Override
    public void verifySellingItem(ItemStack stack) {
        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20) {
            this.livingSoundTime = -this.getTalkInterval();
            this.playSound(this.getVillagerYesNoSound(!stack.isEmpty()), this.getSoundVolume(), this.getSoundPitch());
        }
    }

    public SoundEvent getYesSound() {
        return SoundEvents.ENTITY_VILLAGER_YES;
    }

    protected SoundEvent getVillagerYesNoSound(boolean getYesSound) {
        return getYesSound ? SoundEvents.ENTITY_VILLAGER_YES : SoundEvents.ENTITY_VILLAGER_NO;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public int getXp() {
        return 0;
    }

    @Override
    public void setXP(int xpIn) {

    }

    @Override
    public boolean func_213705_dZ() {
        return true;
    }

    protected void addTrades(MerchantOffers givenMerchantOffers, VillagerTrades.ITrade[] newTrades, int maxNumbers) {
        Set<Integer> set = Sets.newHashSet();
        if (newTrades.length > maxNumbers) {
            while(set.size() < maxNumbers) {
                set.add(this.rand.nextInt(newTrades.length));
            }
        } else {
            for(int i = 0; i < newTrades.length; ++i) {
                set.add(i);
            }
        }

        for(Integer integer : set) {
            VillagerTrades.ITrade villagertrades$itrade = newTrades[integer];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.rand);
            if (merchantoffer != null) {
                givenMerchantOffers.add(merchantoffer);
            }
        }

    }
}
