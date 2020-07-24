package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ModularArmor extends ArmorItem {
	int tick = 0;

	public ModularArmor(EquipmentSlotType slot, Properties builder) {
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
			
			if (!hasFullSet(player)) {
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
		if (hasFullSet(entity) && getModuleLevel(stack, "unbreakable") == 1) {
			entity.getItemStackFromSlot(EquipmentSlotType.HEAD).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.CHEST).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.LEGS).setDamage(0);
			entity.getItemStackFromSlot(EquipmentSlotType.FEET).setDamage(0);
			return 0;
		}
		return super.damageItem(stack, amount, entity, onBroken);
	}
	
	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().equals(ItemInit.MODULAR_HELMET.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem().equals(ItemInit.MODULAR_CHESTPLATE.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem().equals(ItemInit.MODULAR_LEGGINGS.get())
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem().equals(ItemInit.MODULAR_BOOTS.get());
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
		if (!ModularArmor.hasFullSet(entity)) {
			return 0;
		}
		return ModularArmor.getModuleLevel(entity.getItemStackFromSlot(EquipmentSlotType.CHEST), key);
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
				return 10;
			case "jumpboost": // + 50% height / level
				return 10;
			case "strength": // + 3 * level damage
				return 20; 
			case "haste":  // 20% increase / level
				return 10;
			case "resistance": // 20% damage reduction / level
				return 4;
			case "jetpack":
				return 2;
			case "thorns": // 1 heart / level
				return 10;
			default:
				return 0;
		}
	}
	
	@Nullable
	public static String getModuleKeyFromItem(Item item2) {
		if (item2.equals(Items.RABBIT_STEW)){
  		  return "saturation";
  	  }
  	  
  	  if (item2.equals(ItemInit.NIGHTVISION_GOGGLES.get())){
  		  return "nightvision";
  	  }
  	  
  	  if (item2.equals(ItemInit.ANTI_FIRE_CHARM.get())){
  		  return "fireres";
  	  }
  	  
  	  if (item2.equals(ItemInit.SCUBA_GEAR.get())){
  		  return "waterbreathing";
  	  }
  	  
  	  if (item2.equals(ItemInit.SPACE_HELMET.get())){
  		  return "spacehelmet";
  	  }
  	  
  	  if (item2.equals(ItemInit.FLIPPERS.get())){
  		  return "dolphin";
  	  }
  	  
  	  if (item2.equals(ItemInit.LONGFALL_BOOTS.get())){
  		  return "longfall";
  	  }
  	  
  	  if (item2.equals(Items.ENCHANTED_GOLDEN_APPLE)){
  		  return "regen";
  	  }
  	  
  	  if (item2.equals(ItemInit.VARIDIUM_INGOT.get())){
  		  return "speed";
  	  }
  	  
  	  if (item2.equals(Items.RABBIT_FOOT)){
  		  return "jumpboost";
  	  }
  	  
  	  if (item2.equals(BlockInit.CARBIDE_BLOCK.get().asItem())){
  		  return "strength";
  	  }
  	  
  	  if (item2.equals(ItemInit.CARBIDE_PICKAXE.get())){
  		  return "haste";
  	  }
  	  
  	  if (item2.equals(BlockInit.TITANIUM_BLOCK.get().asItem())){
  		  return "resistance";
  	  }
  	  
  	  if (item2.equals(BlockInit.FEYSTEEL_BLOCK.get().asItem())){
  		  return "thorns";
  	  }
  	  
  	  if (item2.equals(ItemInit.JETPACK_TWO.get().asItem())){
  		  return "jetpack";
  	  }
  	  
  	  if (item2.equals(ItemInit.BEDROCKIUM_INGOT.get().asItem())){
  		  return "unbreakable";
  	  }
  	  
  	  return null;
	}
	
	public static class Material implements IArmorMaterial {
		@Override
	    public SoundEvent getSoundEvent() {
	        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
	    }
		 
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
            return 1;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(ItemInit.STEEL_INGOT.get());
        }
    }
}
