package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;
import java.util.function.Consumer;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class PowerArmor extends ArmorItem {
	int tick = 0;

	public PowerArmor(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			CompoundNBT nbtTagCompound = stack.getTag();
			tooltip.add(new StringTextComponent("Protection of diamond. Many modules can be added in the Tinker Table. " + nbtTagCompound.toString()));
		}

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		this.tick++;
		if (this.tick == 300) {
			this.tick = 0;
			
			if (!hasPowerArmor(player)) {
				return;
			}
			
			if (getModuleLevel(stack, "saturation") == 1) {
				player.addPotionEffect(new EffectInstance(Effects.SATURATION, 500, 0));
			}
			
			if (getModuleLevel(stack, "nightvision") == 1) {
				player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 500, 0));
			}
			
			if (getModuleLevel(stack, "fireres") == 1) {
				player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 500, 0));
			}
			
			if (getModuleLevel(stack, "waterbreathing") == 1) {
				player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 500, 0));
			}
			
			if (getModuleLevel(stack, "dolphin") == 1) {
				player.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 500, 0));
			}
			
			if (getModuleLevel(stack, "regen") > 0) {
				player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 500, getModuleLevel(stack, "regen")-1));
			}
			
			if (getModuleLevel(stack, "speed") > 0) {
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 500, getModuleLevel(stack, "speed")-1));
			}
			
			if (getModuleLevel(stack, "jumpboost") > 0) {
				player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 500, getModuleLevel(stack, "jumpboost")-1));
			}
			
			if (getModuleLevel(stack, "strength") > 0) {
				player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 500, getModuleLevel(stack, "strength")-1));
			}
			
			if (getModuleLevel(stack, "haste") > 0) {
				player.addPotionEffect(new EffectInstance(Effects.HASTE, 500, getModuleLevel(stack, "haste")-1));
			}
			
			if (getModuleLevel(stack, "resistance") > 0) {
				player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 500, getModuleLevel(stack, "resistance")-1));
			}
		}
		
		super.onArmorTick(stack, world, player);
	}
	
	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		if (hasPowerArmor(entity) && getModuleLevel(stack, "unbreakable") == 1) {
			entity.getItemStackFromSlot(EquipmentSlotType.HEAD).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.CHEST).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.LEGS).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.FEET).setDamage(0);
			return 0;
		}
		return super.damageItem(stack, amount, entity, onBroken);
	}
	
	public static boolean hasPowerArmor(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.POWERARMOR_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.POWERARMOR_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.POWERARMOR_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.POWERARMOR_BOOTS.get());
	}
	
	public static int getModuleLevel(ItemStack stack, String key) {
		CompoundNBT nbtTagCompound = stack.getTag();
		return nbtTagCompound.getInt(key);
	}
	
	public static void setModuleLevel(ItemStack stack, String key, int level) {
		CompoundNBT nbtTagCompound = stack.getTag();
		nbtTagCompound.putInt(key,level);
		stack.setTag(nbtTagCompound);
	}

	public static void increaseModuleLevel(ItemStack stack, String key) {
		int targetLevel = getModuleLevel(stack, key) + 1;
		if (targetLevel <= getMaxModuleLevel(key)) {
			setModuleLevel(stack, key, targetLevel);
		}
	}
	
	public static boolean canIncreaseModuleLevel(ItemStack stack, String key) {
		int targetLevel = getModuleLevel(stack, key) + 1;
		if (targetLevel <= getMaxModuleLevel(key)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getModuleLevel(LivingEntity entity, String key) {
		if (!PowerArmor.hasPowerArmor(entity)) {
			return 0;
		}
		return PowerArmor.getModuleLevel(entity.getItemStackFromSlot(EquipmentSlotType.CHEST), key);
	}
	
	public static int getMaxModuleLevel(String key) {
		if (key == "saturation" || key == "nightvision" || key == "fireres" || key == "waterbreathing"
				|| key == "dolphin" || key == "longfall" || key == "spacehelmet" || key == "unbreakable") {
			return 1;
		}
		switch (key) {
			case "regen":
				return 3;
			case "speed": // + 20% / level
				return 5;
			case "jumpboost": // + 50% height / level
				return 10;
			case "strength": // + 3 * level damage
				return 5; 
			case "haste":  // 20% increase / level
				return 10;
			case "resistance": // 20% damage reduction / level
				return 4;
			case "jetpack":
				return 2;
			case "thorns": // 1 heart / level
				return 5;
			default:
				return 0;
		}
	}
	
	public static class Material extends BaseSpecialArmorMaterial {
        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotType) {
            return 5;
        }

        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":powerarmor";
        }

        @Override
        public float getToughness() {
            return 4;
        }
        
        @Override
        public int getDurability(EquipmentSlotType slotType) {
            return 1000;
        }

        @Override
        public int getEnchantability() {
            return 5;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(ItemInit.STEEL_INGOT.get());
        }
    }
}
