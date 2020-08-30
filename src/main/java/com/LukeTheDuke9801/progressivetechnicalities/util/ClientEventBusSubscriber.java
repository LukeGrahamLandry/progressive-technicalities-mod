package com.LukeTheDuke9801.progressivetechnicalities.util;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.client.entity.*;
import com.LukeTheDuke9801.progressivetechnicalities.client.gui.LargeChestScreen;
import com.LukeTheDuke9801.progressivetechnicalities.entities.FairyEntity;
import com.LukeTheDuke9801.progressivetechnicalities.entities.WanderingAstronomer;
import com.LukeTheDuke9801.progressivetechnicalities.entities.WanderingGemSmith;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModContainerTypes;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.enchantment_forge.EnchantmentForgeScreen;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.tinker_table.TinkerTableScreen;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid=ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEventBusSubscriber {

	// Registers container and entity types

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainerTypes.LARGE_CHEST.get(), LargeChestScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.TINKER_TABLE.get(), TinkerTableScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.ENCHANTMENT_FORGE.get(), EnchantmentForgeScreen::new);
		
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WANDERING_GEM_SMITH.get(), WanderingGemSmithRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WANDERING_ASTRONOMER.get(), WanderingAstronomerRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FAIRY, FairyRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FEY_FOX, FeyFoxRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FREMAN, FremanRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FEY_COW, FeyCowRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WIZARD, EvokerRenderer::new);

		RenderTypeLookup.setRenderLayer(BlockInit.CHERRY_SAPLING.get(), RenderType.getCutout());
	}
}
