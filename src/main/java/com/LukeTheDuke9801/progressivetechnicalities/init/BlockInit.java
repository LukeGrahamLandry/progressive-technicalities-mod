package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.BlockMultiTNT;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.BlockQuarry;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.LightningRod;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Block> CHROMIUM_BLOCK = BLOCKS.register("chromium_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 36000000f).sound(SoundType.METAL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> CARBIE_BLOCK = BLOCKS.register("carbide_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> CHROMIUM_ORE = BLOCKS.register("chromium_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	// Blood wood
	public static final RegistryObject<Block> BLOODWOOD_PLANKS = BLOCKS.register("bloodwood_planks",
			() -> new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(5f, 20f).sound(SoundType.WOOD).harvestLevel(4).harvestTool(ToolType.AXE)));
	
	public static final RegistryObject<Block> BLOODWOOD_STAIRS = BLOCKS.register("bloodwood_stairs",
			() -> new StairsBlock(() -> BlockInit.BLOODWOOD_PLANKS.get().getDefaultState(), Block.Properties.create(Material.WOOD)));
	
	public static final RegistryObject<Block> BLOODWOOD_FENCE = BLOCKS.register("bloodwood_fence",
			() -> new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.RED)));
	
	// Advanced / Tile Entities
	
	static final RegistryObject<Block> LIGHTNING_ROD = BLOCKS.register("lightning_rod",
			() -> new LightningRod(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 10f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> QUARRY = BLOCKS.register("quarry",
			() -> new BlockQuarry(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> MULTI_TNT = BLOCKS.register("multi_tnt",
			() -> new BlockMultiTNT(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1f).sound(SoundType.SAND).harvestLevel(0)));
	
}
