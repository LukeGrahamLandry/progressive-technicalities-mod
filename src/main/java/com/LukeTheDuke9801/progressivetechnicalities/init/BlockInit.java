package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.ArmorDisassembler;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.BlockMultiTNT;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.BlockQuarry;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.CastingTableBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.FeyPortalBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.LargeChestBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.LightningRod;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.OilPortalBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.alloy_table.AlloyTableBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.electric_furnace.ElectricFurnaceBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.oil_generator.OilGeneratorBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.rocket.RocketBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.tinker_table.TinkerTableBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Block> CHROMIUM_BLOCK = BLOCKS.register("chromium_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> CARBIDE_BLOCK = BLOCKS.register("carbide_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> FEYSTEEL_BLOCK = BLOCKS.register("feysteel_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> CHROMIUM_ORE = BLOCKS.register("chromium_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> SKY_GEM_ORE = BLOCKS.register("sky_gem_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DARK_STONE = BLOCKS.register("dark_stone",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5f, 2f).sound(SoundType.STONE).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> FEY_STONE = BLOCKS.register("fey_stone",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(15f, 0.1f).sound(SoundType.STONE).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> FEYSTEEL_ORE = BLOCKS.register("feysteel_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 50f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> MELANGE_DEPOSIT = BLOCKS.register("melange_deposit",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 0.001f).sound(SoundType.SAND).harvestLevel(4).harvestTool(ToolType.SHOVEL)));
	
	public static final RegistryObject<Block> UNOBTANIUM_ORE = BLOCKS.register("unobtanium_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	// Advanced / Tile Entities
	
	static final RegistryObject<Block> LIGHTNING_ROD = BLOCKS.register("lightning_rod",
			() -> new LightningRod(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 10f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> QUARRY = BLOCKS.register("quarry",
			() -> new BlockQuarry(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> MULTI_TNT = BLOCKS.register("multi_tnt",
			() -> new BlockMultiTNT(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1f).sound(SoundType.SAND).harvestLevel(0)));
	
	public static final RegistryObject<Block> LARGE_CHEST = BLOCKS.register("large_chest",
			() -> new LargeChestBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1f).sound(SoundType.SAND).harvestLevel(0)));
	
	public static final RegistryObject<Block> FEYWILD_PORTAL = BLOCKS.register("feywild_portal",
			() -> new FeyPortalBlock(Block.Properties.create(Material.PORTAL).hardnessAndResistance(2f, 1).sound(SoundType.SAND).harvestLevel(0)));
	
	public static final RegistryObject<Block> OIL_PORTAL = BLOCKS.register("oil_portal",
			() -> new OilPortalBlock(Block.Properties.create(Material.PORTAL).hardnessAndResistance(2f, 1).sound(SoundType.SAND).harvestLevel(0)));
	
	// Machines
	
	public static final RegistryObject<Block> ELECTRIC_FURNACE = BLOCKS.register("electric_furnace",
			() -> new ElectricFurnaceBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> OIL_GENERATOR = BLOCKS.register("oil_generator",
			() -> new OilGeneratorBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> ALLOY_TABLE = BLOCKS.register("alloy_table",
			() -> new AlloyTableBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> CASTING_TABLE = BLOCKS.register("casting_table",
			() -> new CastingTableBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> ARMOR_DISASSEMBLER = BLOCKS.register("armor_disassembler",
			() -> new ArmorDisassembler(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> TINKER_TABLE = BLOCKS.register("tinker_table",
			() -> new TinkerTableBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> ROCKET = BLOCKS.register("rocket",
			() -> new RocketBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

}
