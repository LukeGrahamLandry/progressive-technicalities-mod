package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.special.RitualGuardian;
import com.LukeTheDuke9801.progressivetechnicalities.entities.special.SkyGuardian;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.*;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.coal.CoalGeneratorBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.lava.LavaGeneratorBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.generators.oil.OilGeneratorBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.enchantment_forge.EnchantmentForgeBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.mob_slayer.MobSlayerBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.mob_spawner.MobSpawnerBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.tinker_table.TinkerTableBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.xp_tank.XPTankBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines.ArmorDisassembler;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines.CastingTableBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines.DisenchanterBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines.InductionSmelterBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines.ItemRepairerBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines.PulverizerBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.simple_machines.SimpleMachineBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Block> CHROMIUM_BLOCK = BLOCKS.register("chromium_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 6f).sound(SoundType.METAL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> CARBIDE_BLOCK = BLOCKS.register("carbide_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> FEYSTEEL_BLOCK = BLOCKS.register("feysteel_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> CHROMIUM_ORE = BLOCKS.register("chromium_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 6f).sound(SoundType.METAL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> AIR_GEM_ORE = BLOCKS.register("air_gem_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> AIR_GEM_BLOCK = BLOCKS.register("air_gem_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DARK_STONE = BLOCKS.register("dark_stone",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5f, 2f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> FEY_STONE = BLOCKS.register("fey_stone",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(15f, 0.1f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> FEYSTEEL_ORE = BLOCKS.register("feysteel_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 50f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> MELANGE_DEPOSIT = BLOCKS.register("melange_deposit",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 0.001f).sound(SoundType.SAND).harvestLevel(4).harvestTool(ToolType.SHOVEL)));
	
	public static final RegistryObject<Block> UNOBTANIUM_ORE = BLOCKS.register("unobtanium_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> FIRE_GEM_ORE = BLOCKS.register("fire_gem_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> SANDSTONE_FIRE_GEM_ORE = BLOCKS.register("sandstone_fire_gem_ore",
			() -> new Block(Block.Properties.from(FIRE_GEM_ORE.get())));

	public static final RegistryObject<Block> WATER_GEM_ORE = BLOCKS.register("water_gem_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> EARTH_GEM_ORE = BLOCKS.register("earth_gem_ore",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 6f).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> LUNA_DUST = BLOCKS.register("luna_dust",
			() -> new FallingBlock(Block.Properties.from(Blocks.SAND)));
	
	public static final RegistryObject<Block> LUNA_STONE = BLOCKS.register("luna_stone",
			() -> new Block(Block.Properties.from(Blocks.STONE)));
	
	public static final RegistryObject<Block> DILITHIUM_ORE = BLOCKS.register("dilithium_ore",
			() -> new Block(Block.Properties.from(Blocks.STONE)));

	public static final RegistryObject<Block> END_AIR_GEM_ORE = BLOCKS.register("end_air_gem_ore",
			() -> new Block(Block.Properties.from(AIR_GEM_ORE.get())));

	public static final RegistryObject<Block> FEY_BRICK = BLOCKS.register("fey_bricks",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(15f, 15f).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> RUNE_STONE = BLOCKS.register("rune_stone",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f, 50f).sound(SoundType.STONE).harvestLevel(4).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> RITUAL_STONE = BLOCKS.register("ritual_stone",
			() -> new BasicRitualStone(Block.Properties.create(Material.ROCK).hardnessAndResistance(3f, 50f).sound(SoundType.STONE).harvestLevel(4).harvestTool(ToolType.PICKAXE)));

	/* Mobs for structures
	It hangs when I try to add the mob directly from the dataMarker so instead
	the data marker makes these blocks which become a mob after a random tick
	 */
	public static final RegistryObject<Block> RITUAL_GUARDIAN = BLOCKS.register("ritual_guardian",
			() -> new SingleMobSpawner(new RitualGuardian(), Block.Properties.create(Material.AIR).tickRandomly().doesNotBlockMovement().notSolid()));

	public static final RegistryObject<Block> SKY_GUARDIAN = BLOCKS.register("sky_guardian",
			() -> new SingleMobSpawner(new SkyGuardian(), Block.Properties.create(Material.AIR).tickRandomly().doesNotBlockMovement().notSolid()));

	// Advanced / Tile Entities
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
	
	public static final RegistryObject<Block> OIL_GENERATOR = BLOCKS.register("oil_generator",
			() -> new OilGeneratorBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> LAVA_GENERATOR = BLOCKS.register("lava_generator",
			() -> new LavaGeneratorBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> COAL_GENERATOR = BLOCKS.register("coal_generator",
			() -> new CoalGeneratorBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> CASTING_TABLE = BLOCKS.register("casting_table",
			() -> new CastingTableBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> ARMOR_DISASSEMBLER = BLOCKS.register("armor_disassembler",
			() -> new ArmorDisassembler(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> TINKER_TABLE = BLOCKS.register("tinker_table",
			() -> new TinkerTableBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> ENCHANTMENT_FORGE = BLOCKS.register("enchantment_forge",
			() -> new EnchantmentForgeBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DISENCHANTER = BLOCKS.register("disenchanter",
			() -> new DisenchanterBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> ITEM_REPAIRER = BLOCKS.register("item_repairer",
			() -> new ItemRepairerBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> MOB_SLAYER = BLOCKS.register("mob_slayer",
			() -> new MobSlayerBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> MOB_SPAWNER = BLOCKS.register("mob_spawner",
			() -> new MobSpawnerBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> PULVERIZER = BLOCKS.register("pulverizer",
			() -> new PulverizerBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> INDUCTION_SMELTER = BLOCKS.register("induction_smelter",
			() -> new InductionSmelterBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> XP_TANK = BLOCKS.register("xp_tank",
			() -> new XPTankBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	
	public static final RegistryObject<Block> SIMPLE_MACHINE = BLOCKS.register("simple_machine",
			() -> new SimpleMachineBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2f, 1000f).sound(SoundType.ANVIL).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
}
