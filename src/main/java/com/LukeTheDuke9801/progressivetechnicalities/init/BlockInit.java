package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities.ProgtechItemGroup;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.LightningRod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid= ProgressiveTechnicalities.MOD_ID, bus= Bus.MOD)
@ObjectHolder(ProgressiveTechnicalities.MOD_ID)
public class BlockInit 
{
	public static final Block chromium_block = null;
	public static final Block chromium_ore = null;
	public static final Block titanium_block = null;
	public static final Block titanium_ore = null;
	public static final Block carbide_block = null;
	
	public static final Block lightning_rod = null;

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 36000000f).sound(SoundType.METAL).harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName("chromium_block"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(3f, 2f).sound(SoundType.METAL).harvestLevel(0).harvestTool(ToolType.PICKAXE)).setRegistryName("chromium_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 3f).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName("titanium_block"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(3f, 2f).sound(SoundType.METAL).harvestLevel(0).harvestTool(ToolType.PICKAXE)).setRegistryName("titanium_ore"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 2f).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)).setRegistryName("carbide_block"));
		
		event.getRegistry().register(new LightningRod(Block.Properties.create(Material.IRON).hardnessAndResistance(0.5f, 10.0f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)).setRegistryName("lightning_rod"));
	}
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new BlockItem(chromium_block, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("chromium_block"));
		event.getRegistry().register(new BlockItem(chromium_ore, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("chromium_ore"));
		event.getRegistry().register(new BlockItem(titanium_block, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("titanium_block"));
		event.getRegistry().register(new BlockItem(titanium_ore, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("titanium_ore"));
		event.getRegistry().register(new BlockItem(carbide_block, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_block"));
		
		event.getRegistry().register(new BlockItem(lightning_rod, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("lightning_rod"));
	}
}