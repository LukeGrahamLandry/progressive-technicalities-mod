package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities.ProgtechItemGroup;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInitNew {
	
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Item> DEF_ITEM = ITEMS.register("def_item",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
}
