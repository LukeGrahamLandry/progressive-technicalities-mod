package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;
import java.util.UUID;

public class ArmorHealthBoostCatalyst extends Item implements RitualCatalyst {
   public ArmorHealthBoostCatalyst(Properties builder) {
      super(builder);
   }
   
   @Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Activates an advanced ritual upgrades your currently worn armor to grant extra health"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public void doRitual(World world, BlockPos pos, PlayerEntity player){
   		String modifierName = "ProgTechArmorUpgrade_health";
		for (ItemStack stack : player.getArmorInventoryList()){
			if (!(stack.getItem() instanceof ArmorItem)) continue;

			EquipmentSlotType slot = ((ArmorItem)stack.getItem()).getEquipmentSlot();
			boolean itemAlreadyUpgraded = stack.getAttributeModifiers(slot).containsKey(SharedMonsterAttributes.MAX_HEALTH.getName());
			ProgressiveTechnicalities.LOGGER.debug(stack.getAttributeModifiers(slot).entries());
			if (itemAlreadyUpgraded) continue;

			Multimap<String, AttributeModifier> oldModifiers = stack.getAttributeModifiers(slot);

			AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(),
					modifierName, 5, AttributeModifier.Operation.ADDITION);
			stack.addAttributeModifier(SharedMonsterAttributes.MAX_HEALTH.getName(), modifier, slot);

			// adding a modifier to nbt overrides the ArmorItem.getArrtibuteModifiers so
			// we have to add those to nbt aswell so the armor still gives protection
			oldModifiers.forEach((String name, AttributeModifier oldmodifier) -> {
				stack.addAttributeModifier(name, oldmodifier, slot);
			});

		}
	}

	@Override
	public int getRitualLevel() {
		return 2;
	}
}