package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class FeyFood extends Item{
	public FeyFood(Properties properties) {
		super(properties);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			String data = String.format("(%d, %.1f). ", this.getFood().getHealing(), this.getFood().getSaturation());
			List<Pair<EffectInstance, Float>> effects = this.getFood().getEffects();
			for (Pair<EffectInstance, Float> effect : effects) {
				int chance = (int) (effect.getRight() * 100);
				String name = effect.getLeft().getEffectName().replace("effect.minecraft.", "") + " " + (effect.getLeft().getAmplifier()+1);
				data += name + " [" + chance + "%]";
			}
			tooltip.add(new StringTextComponent("This food seems extra filling " + data));
			
			
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public static boolean isFeyFood(Item item) {
		return item instanceof FeyFood;
	}
}
