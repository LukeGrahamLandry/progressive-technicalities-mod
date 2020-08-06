package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class ResistanceArmorTest extends ArmorItem implements HitEventListener{

	public ResistanceArmorTest(EquipmentSlotType slot, Properties builder) {
		super(new Material(), slot, builder);
	}


	@Override
	public void onWearerHit(LivingHurtEvent event) {
		// event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.RESISTANCE, 10, 4));
		event.setAmount(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Grants damage immunity"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public static class Material extends AdvancedSpecialArmorMaterial {
        @Override
        public String getName() {
            return ProgressiveTechnicalities.MOD_ID + ":special";
        }
    }
}
