package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class LootBox extends Item{
	public LootBox(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Right click to get a random item"));
		} 
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		if (worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, handIn);

		final Item[] chosenItem = new Item[1];
		final int[] i = {0};
		Collection<RegistryObject<Item>> items = ItemInit.ITEMS.getEntries();
		int chosenIndex = random.nextInt(items.size());
		items.forEach((item) -> {
			if (i[0] == chosenIndex){
				chosenItem[0] = item.get();
			}
			i[0]++;
		});

		playerIn.setHeldItem(handIn, new ItemStack(chosenItem[0], 1));

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
