package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

public class HandHeldRocket extends Item{
	private static final DimensionType feywildDim = DimensionType.byName(ProgressiveTechnicalities.FEY_DIM_TYPE);
	private static final DimensionType oilDim = DimensionType.byName(ProgressiveTechnicalities.OIL_DIM_TYPE);
	
	DimensionType destination;
	public HandHeldRocket(DimensionType destinationIn, Properties properties) {
		super(properties);
		this.destination = destinationIn;
	}
	
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		DimensionType currentDim = worldIn.getDimension().getType();
		if (currentDim == feywildDim || currentDim == oilDim) {
			return;
		}
		
		entityIn.setMotion(0, 1.0D, 0);
		
		// Blows up and probably kills you if you hit something
		if (!worldIn.getBlockState(entityIn.getPosition().up().up()).isAir(null, null)) {
			entityIn.setMotion(0, 0, 0);
			worldIn.createExplosion(entityIn, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), 10, Explosion.Mode.DESTROY);
			entityIn.attackEntityFrom(DamageSource.FLY_INTO_WALL, 30); // deals 15 hearts as though you hit wall with elytra
			entityIn.replaceItemInInventory(itemSlot, ItemStack.EMPTY);
			return;
		}
		
		if (entityIn.getPosY() > 250) {
			MinecraftServer minecraftserver = entityIn.getServer();
			ServerWorld serverworld = minecraftserver.getWorld(this.destination);

			((ServerPlayerEntity)entityIn).teleport(serverworld, entityIn.getPosX(), 150, entityIn.getPosZ(), entityIn.getYaw(0), entityIn.getPitch(0));
			
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 300, 0));
			
			entityIn.replaceItemInInventory(itemSlot, ItemStack.EMPTY);
			
		}
		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("A handheld rocket"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
