package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import java.util.List;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class MagicEggShell extends Item {
	public MagicEggShell(Properties properties) {
		super(properties);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Hit a creature to trap it in the egg. "));
			tooltip.add(new StringTextComponent("Can be used to spawn the creature or set an auto spawner. "));
			if (getType(stack) != null) {
				tooltip.add(new StringTextComponent("Currently holding: <" + getType(stack).getName() + ">"));
			}	
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		if (entity.world.isRemote || getType(stack) != null || !entity.isNonBoss()) return false;
		
		if (entity instanceof LivingEntity) {
			setType(stack, entity.getType());
			entity.remove();
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	public ActionResultType onItemUse(ItemUseContext context) {
		ItemStack stack = context.getItem();
		EntityType type = getType(stack);
		if (type == null || context.getWorld().isRemote) return ActionResultType.PASS;
		
		BlockPos spawnPos = context.getPos().offset(context.getFace());
		
		type.spawn(context.getWorld(), ItemStack.EMPTY, null, spawnPos, SpawnReason.SPAWN_EGG, false, false);
		
		setEmpty(stack);
		return ActionResultType.SUCCESS;
	}
	
	public static EntityType getType(ItemStack stack) {
		CompoundNBT nbtTagCompound = stack.getTag();
		
		if (nbtTagCompound == null) return null;
		
		String typeStr = nbtTagCompound.getString("type");
		if (typeStr == "") {
			return null;
		}
		
		return EntityType.byKey(typeStr).get();
	}
	
	private static void setType(ItemStack stack, EntityType typeIn) {
		CompoundNBT nbtTagCompound = new CompoundNBT();

		String typeStr = EntityType.getKey(typeIn).toString();
		nbtTagCompound.putString("type", typeStr);
		
		stack.setTag(nbtTagCompound);
	}
	
	public static void setEmpty(ItemStack stack) {
		CompoundNBT nbtTagCompound = new CompoundNBT();
		nbtTagCompound.putString("type", "");
		stack.setTag(nbtTagCompound);
	}
	
}