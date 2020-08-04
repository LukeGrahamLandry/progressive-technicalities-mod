package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
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

public class SmeltingRitualCatalyst extends Item implements RitualCatalyst {
   public SmeltingRitualCatalyst(Properties builder) {
      super(builder);
   }
   
   @Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Activates a basic ritual that smelts all items (without durability) in your inventory"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public void doRitual(World world, BlockPos pos, PlayerEntity player){
   		for (int i=0;i<36;i++){
   			ItemStack stack = player.inventory.mainInventory.get(i);
   			if (!stack.isDamageable()){
				player.inventory.mainInventory.set(i, smelt(stack, world));
			}
		}
	}

	private static ItemStack smelt(ItemStack stack, World world) {
		return world.getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(stack), world)
				.map(FurnaceRecipe::getRecipeOutput)
				.filter(itemStack -> !itemStack.isEmpty())
				.map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
				.orElse(stack);
	}

}