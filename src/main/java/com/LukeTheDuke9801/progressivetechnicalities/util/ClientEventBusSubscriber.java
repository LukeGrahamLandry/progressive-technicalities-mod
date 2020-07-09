package com.LukeTheDuke9801.progressivetechnicalities.util;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.client.gui.LargeChestScreen;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModContainerTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.alloy_table.AlloyTableScreen;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.electric_furnace.ElectricFurnaceScreen;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.enchantment_forge.EnchantmentForgeScreen;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.rocket.RocketScreen;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.tinker_table.TinkerTableScreen;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid=ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainerTypes.LARGE_CHEST.get(), LargeChestScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.ALLOY_TABLE.get(), AlloyTableScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.TINKER_TABLE.get(), TinkerTableScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.ROCKET.get(), RocketScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.ENCHANTMENT_FORGE.get(), EnchantmentForgeScreen::new);
	}
}
