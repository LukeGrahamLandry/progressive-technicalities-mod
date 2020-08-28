package com.LukeTheDuke9801.progressivetechnicalities.entities;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.NymphariumFluidBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.FeyFood;
import com.LukeTheDuke9801.progressivetechnicalities.util.interfaces.BreathesInSpace;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class FeyCowEntity extends CowEntity {
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(ItemInit.GOLDEN_PLUM.get());

    public FeyCowEntity(EntityType<? extends FeyCowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == Items.BUCKET && !player.abilities.isCreativeMode && !this.isChild()) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            itemstack.shrink(1);
            if (itemstack.isEmpty()) {
                player.setHeldItem(hand, new ItemStack(FluidInit.NYMPHARIUM_FLUID_BUCKET.get()));
            } else if (!player.inventory.addItemStackToInventory(new ItemStack(FluidInit.NYMPHARIUM_FLUID_BUCKET.get()))) {
                player.dropItem(new ItemStack(FluidInit.NYMPHARIUM_FLUID_BUCKET.get()), false);
            }

            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return FeyFood.isFeyFood(stack.getItem());
    }

    public FeyCowEntity createChild(AgeableEntity ageable) {
        return ModEntityTypes.FEY_COW.create(this.world);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == NymphariumFluidBlock.DAMAGE_SOURCE) this.heal(amount);
        return super.attackEntityFrom(source, amount);
    }

    public boolean isInvulnerableTo(DamageSource source) {
        if (source == NymphariumFluidBlock.DAMAGE_SOURCE) return true;
        return super.isInvulnerableTo(source);
    }
}
