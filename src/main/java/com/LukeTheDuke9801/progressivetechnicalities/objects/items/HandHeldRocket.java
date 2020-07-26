package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class HandHeldRocket extends Item{
	private static final DimensionType feywildDim = DimensionType.byName(ProgressiveTechnicalities.FEY_DIM_TYPE);
	private static final DimensionType oilDim = DimensionType.byName(ProgressiveTechnicalities.OIL_DIM_TYPE);
	
	String destination;
	boolean isAdvanced;
	public HandHeldRocket(String destinationIn, boolean isAdvancedIn, Properties properties) {
		super(properties);
		this.destination = destinationIn;
		this.isAdvanced = isAdvancedIn;
	}
	
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		DimensionType currentDim = worldIn.getDimension().getType();
		if (!shouldFly(stack) || currentDim == feywildDim || currentDim == oilDim || currentDim == DimensionType.THE_END) {
			return;
		}
		
		entityIn.setMotion(0, 1.0D, 0);

		if (shouldExplode(worldIn, entityIn)){
			explode(worldIn, entityIn);
			entityIn.replaceItemInInventory(itemSlot, ItemStack.EMPTY);
			return;
		}

		if (entityIn.getPosY() > 250) {
			sendToTargetDimension(entityIn);

			ItemStack resultRocket = getRocketCasing();
			entityIn.replaceItemInInventory(itemSlot, resultRocket);
		}
		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	private boolean shouldExplode(World worldIn, Entity entityIn) {
		return !worldIn.getBlockState(entityIn.getPosition().up().up()).isAir(null, null);
	}

	private void explode(World worldIn, Entity entityIn) {
		entityIn.setMotion(0, 0, 0);  // so you don't keep flying up
		worldIn.createExplosion(entityIn, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), 10, Explosion.Mode.DESTROY);
		entityIn.attackEntityFrom(DamageSource.FLY_INTO_WALL, 19); // deals 9.5 hearts as though you hit wall with elytra
	}

	private void sendToTargetDimension(Entity entityIn) {
		MinecraftServer minecraftserver = entityIn.getServer();
		if (minecraftserver == null) {
			return;
		}
		ServerWorld serverworld = minecraftserver.getWorld(this.getDestination());

		((ServerPlayerEntity)entityIn).teleport(serverworld, 0, 150, 0, entityIn.getYaw(0), entityIn.getPitch(0));

		((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 300, 0)); // so you dont go splat
	}

	private ItemStack getRocketCasing() {
		if (this.isAdvanced) {
			return new ItemStack(ItemInit.ADVANCED_ROCKET_CASING.get());
		} else {
			return new ItemStack(ItemInit.BASIC_ROCKET_CASING.get());
		}
	}

	private DimensionType getDestination() {
		switch (this.destination) {
		case "overworld":
			return DimensionType.OVERWORLD;
		case "arrakis":
			return DimensionType.byName(ProgressiveTechnicalities.ARRAKIS_DIM_TYPE);
		case "pandora":
			return DimensionType.byName(ProgressiveTechnicalities.PANDORA_DIM_TYPE);
		case "luna":
			return DimensionType.byName(ProgressiveTechnicalities.LUNA_DIM_TYPE);
		default:
			throw new NullPointerException("INVALID DESTINATION STRING IN HandHeldRocket");
		}
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Rightclick to activate"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		activate(stack);
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	private static boolean shouldFly(ItemStack stack) {
		if (!stack.hasTag()) return false;

		CompoundNBT nbtTagCompound = stack.getTag();
		return nbtTagCompound.getBoolean("active");
	}
	
	private static void activate(ItemStack stack) {
		CompoundNBT nbtTagCompound = new CompoundNBT();
		nbtTagCompound.putBoolean("active", true);
		stack.setTag(nbtTagCompound);
	}
	
}
