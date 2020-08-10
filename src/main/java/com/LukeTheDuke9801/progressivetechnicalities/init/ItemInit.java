package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities.ProgtechItemGroup;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.*;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.*;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.charms.*;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.*;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools.*;
import com.LukeTheDuke9801.progressivetechnicalities.util.books.BasicHelp;
import com.LukeTheDuke9801.progressivetechnicalities.util.books.RitualHelp;
import com.LukeTheDuke9801.progressivetechnicalities.util.books.RocketHelp;
import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModArmorMaterial;
import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModItemTier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<Item> CHROMIUM_ROD = ITEMS.register("chromium_rod",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> CHROMIUM_INGOT = ITEMS.register("chromium_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> CARBIDE_INGOT = ITEMS.register("carbide_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> AIR_GEM = ITEMS.register("air_gem",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SPACE_INGOT = ITEMS.register("space_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> POTION_CORE = ITEMS.register("potion_core",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> BASIC_POTION_CORE = ITEMS.register("basic_potion_core",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MACHINE_BASE = ITEMS.register("machine_base",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> TINY_COAL = ITEMS.register("tiny_coal",
			() -> new TinyCoal(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MAGIC_EGGSHELL = ITEMS.register("magic_eggshell",
			() -> new MagicEggShell(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> PORTAL_KEY = ITEMS.register("portal_key",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OIL_PORTAL_KEY = ITEMS.register("oil_portal_key",
			() -> new OilPortalKey(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FEYSTEEL_INGOT = ITEMS.register("feysteel_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OBSIDIAN_INGOT = ITEMS.register("obsidian_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> NYMPHARIUM_INGOT = ITEMS.register("nympharium_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> VARIDIUM_INGOT = ITEMS.register("varidium_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MELANGE_CLUMP = ITEMS.register("melange_clump",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> UNOBTANIUM_INGOT = ITEMS.register("unobtanium_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> UNOBTANIUM_SHARD = ITEMS.register("unobtanium_shard",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> BEDROCKIUM_SHARD = ITEMS.register("bedrockium_shard",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> BEDROCKIUM_INGOT = ITEMS.register("bedrockium_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> XP_CHARGE = ITEMS.register("xp_charge",
			() -> new XPCharge(160, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> GREATER_XP_CHARGE = ITEMS.register("greater_xp_charge",
			() -> new XPCharge(1400, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OVERWORLD_ROCKET = ITEMS.register("overworld_rocket",
			() -> new HandHeldRocket("overworld", false, new Item.Properties().group(ProgtechItemGroup.instance)));;
			
	public static final RegistryObject<Item> LUNA_ROCKET = ITEMS.register("luna_rocket",
			() -> new HandHeldRocket("luna", false, new Item.Properties().group(ProgtechItemGroup.instance)));;
			
	public static final RegistryObject<Item> ADVANCED_OVERWORLD_ROCKET = ITEMS.register("advanced_overworld_rocket",
			() -> new HandHeldRocket("overworld", true, new Item.Properties().group(ProgtechItemGroup.instance)));;

	public static final RegistryObject<Item> ARRAKIS_ROCKET = ITEMS.register("arrakis_rocket",
			() -> new HandHeldRocket("arrakis", true, new Item.Properties().group(ProgtechItemGroup.instance)));;
	
	public static final RegistryObject<Item> PANDORA_ROCKET = ITEMS.register("pandora_rocket",
			() -> new HandHeldRocket("pandora", true, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> FLIGHT_COMPONENT = ITEMS.register("flight_component",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> BASIC_ROCKET_CASING = ITEMS.register("basic_rocket_casing",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> ADVANCED_ROCKET_CASING = ITEMS.register("advanced_rocket_casing",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FIRE_GEM = ITEMS.register("fire_gem",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> WATER_GEM = ITEMS.register("water_gem",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> EARTH_GEM = ITEMS.register("earth_gem",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> END_GEM = ITEMS.register("end_gem",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> DILITHIUM = ITEMS.register("dilithium",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> CREPUSCULUM = ITEMS.register("crepusculum",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> LIGHTNING_BOTTLE = ITEMS.register("lightning_bottle",
			() -> new LightningBottle(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(16)));


	// Compasses
	public static final RegistryObject<Item> SKY_ISLAND_COMPASS = ITEMS.register("sky_island_compass",
			() -> new StructureCompass(ProgressiveTechnicalities.MOD_ID + ":sky_island", new Item.Properties().group(ProgtechItemGroup.instance)));

	// Ritual Catalysts
	public static final RegistryObject<Item> FEYWILD_PORTAL_KEY = ITEMS.register("feywild_portal_key",
			() -> new FeywildPortalKey(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> SMELTING_RITUAL_CATALYST = ITEMS.register("smelting_ritual_catalyst",
			() -> new SmeltingRitualCatalyst(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> ARMOR_HEALTH_BOOST_CATALYST = ITEMS.register("armor_health_boost_catalyst",
			() -> new ArmorHealthBoostCatalyst(new Item.Properties().group(ProgtechItemGroup.instance)));

	// Books
	public static final RegistryObject<Item> HELP_BOOK = ITEMS.register("help_book",
			() -> new PreWrittenBook(new BasicHelp(), new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> RITUAL_HELP_BOOK = ITEMS.register("ritual_help_book",
			() -> new PreWrittenBook(new RitualHelp(), new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> ROCKET_HELP_BOOK = ITEMS.register("rocket_help_book",
			() -> new PreWrittenBook(new RocketHelp(), new Item.Properties().group(ProgtechItemGroup.instance)));

	// Dusts
	public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> CHROMIUM_DUST = ITEMS.register("chromium_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> CARBIDE_DUST = ITEMS.register("carbide_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> TITANIUM_DUST = ITEMS.register("titanium_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> FEYSTEEL_DUST = ITEMS.register("feysteel_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> STEEL_DUST = ITEMS.register("steel_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> NYMPHARIUM_DUST = ITEMS.register("nympharium_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> ELECTRUM_DUST = ITEMS.register("electrum_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> VARIDIUM_DUST = ITEMS.register("varidium_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> OBSIDIAN_DUST = ITEMS.register("obsidian_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> DIASTIMA_DUST = ITEMS.register("diastima_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> UNOBTANIUM_DUST = ITEMS.register("unobtanium_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> BEDROCKIUM_DUST = ITEMS.register("bedrockium_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	public static final RegistryObject<Item> AIR_GEM_DUST = ITEMS.register("air_gem_dust",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	// Staffs

	public static final RegistryObject<Item> STAFF = ITEMS.register("staff",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> WEATHER_STAFF = ITEMS.register("weather_staff",
			() -> new WeatherStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> TIME_STAFF = ITEMS.register("time_staff",
			() -> new TimeStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ENDER_STAFF = ITEMS.register("ender_staff",
			() -> new EnderStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> SNOW_STAFF = ITEMS.register("snow_staff",
			() -> new SnowStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> RECALL_STAFF = ITEMS.register("recall_staff",
			() -> new RecallStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> NETHER_STAFF = ITEMS.register("nether_staff",
			() -> new NetherStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> XP_STAFF = ITEMS.register("xp_staff",
			() -> new XPStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> BLAZE_STAFF = ITEMS.register("blaze_staff",
			() -> new BlazeStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> TNT_STAFF = ITEMS.register("tnt_staff",
			() -> new TNTStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> CREATIVE_STAFF = ITEMS.register("creative_staff",
			() -> new CreativeStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> MOMENTUM_STAFF = ITEMS.register("momentum_staff",
			() -> new MomentumStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> LIGHTNING_STAFF = ITEMS.register("lightning_staff",
			() -> new LightningStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> SHULKING_STAFF = ITEMS.register("shulking_staff",
			() -> new ShulkingStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> TEST_STAFF = ITEMS.register("test_staff",
			() -> new TestStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> LOOKING_GLASS = ITEMS.register("looking_glass",
			() -> new LookingGlass(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> AMMETER = ITEMS.register("ammeter",
			() -> new Ammeter(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	public static final RegistryObject<Item> HAND_HELD_TELEPORTER = ITEMS.register("hand_held_teleporter",
			() -> new HandHeldTeleporter(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	public static final RegistryObject<Item> ZHONYAS_HOURGLASS = ITEMS.register("zhonyas_hourglass",
			() -> new ZhonyasHourglass(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	// Charms
	public static final RegistryObject<Item> BOOST_CHARM = ITEMS.register("boost_charm",
			() -> new BoostCharm(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ANTI_FIRE_CHARM = ITEMS.register("anti_fire_charm",
			() -> new AntiFireCharm(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> BASIC_LIFESTEAL_CHARM = ITEMS.register("basic_lifesteal_charm",
			() -> new LifeStealCharmBasic(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ADVANCED_LIFESTEAL_CHARM = ITEMS.register("advanced_lifesteal_charm",
			() -> new LifeStealCharmAdvanced(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> MINERS_CHARM = ITEMS.register("miners_charm",
			() -> new MinersCharm(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ANTI_POISON_CHARM = ITEMS.register("anti_poison_charm",
			() -> new AntiEffectCharm(Effects.POISON, new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ANTI_SLOWNESS_CHARM = ITEMS.register("anti_slowness_charm",
			() -> new AntiEffectCharm(Effects.SLOWNESS, new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ANTI_WITHER_CHARM = ITEMS.register("anti_wither_charm",
			() -> new AntiEffectCharm(Effects.WITHER, new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ANTI_MINING_FATIGUE_CHARM = ITEMS.register("anti_mining_fatigue_charm",
			() -> new AntiEffectCharm(Effects.MINING_FATIGUE, new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ANTI_LEVITATION_CHARM = ITEMS.register("anti_levitaion_charm",
			() -> new AntiEffectCharm(Effects.LEVITATION, new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ANTI_BLINDNESS_CHARM = ITEMS.register("anti_blindness_charm",
			() -> new AntiEffectCharm(Effects.BLINDNESS, new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	public static final RegistryObject<Item> WATERGEM_CHARM = ITEMS.register("watergem_charm",
			() -> new WaterGemCharm(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	// Tools
	public static final RegistryObject<Item> CARBIDE_SWORD = ITEMS.register("carbide_sword",
			() -> new SwordItem(ModItemTier.CARBIDE, 3, -2.0f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> CARBIDE_PICKAXE = ITEMS.register("carbide_pickaxe",
			() -> new PickaxeItem(ModItemTier.CARBIDE, -2, -1.0f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> CARBIDE_SHOVEL = ITEMS.register("carbide_shovel",
			() -> new ShovelItem(ModItemTier.CARBIDE, -2, -1.0f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> CARBIDE_AXE = ITEMS.register("carbide_axe",
			() -> new AxeItem(ModItemTier.CARBIDE, 5, -2.8f, new Item.Properties().group(ProgtechItemGroup.instance)));
	 
	public static final RegistryObject<Item> CARBIDE_HOE = ITEMS.register("carbide_hoe",
			() -> new HoeItem(ModItemTier.CARBIDE, 3.0f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword",
			() -> new SwordItem(ModItemTier.OBSIDIAN, 3, -2.4f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OBSIDIAN_PICKAXE = ITEMS.register("obsidian_pickaxe",
			() -> new PickaxeItem(ModItemTier.OBSIDIAN, 1, -2.8f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OBSIDIAN_SHOVEL = ITEMS.register("obsidian_shovel",
			() -> new ShovelItem(ModItemTier.OBSIDIAN, 1.5f, -3.0f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OBSIDIAN_AXE = ITEMS.register("obsidian_axe",
			() -> new AxeItem(ModItemTier.OBSIDIAN, 6.0f, -3.1f, new Item.Properties().group(ProgtechItemGroup.instance)));
	 
	public static final RegistryObject<Item> OBSIDIAN_HOE = ITEMS.register("obsidian_hoe",
			() -> new HoeItem(ModItemTier.OBSIDIAN, -1.0f, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_AIOT = ITEMS.register("titanium_aiot",
			() -> new TitaniumAIOT(ModItemTier.TITANIUM, 2, -3.1f, new Item.Properties().group(ProgtechItemGroup.instance)));
	// TODO: make it not look like shit
	public static final RegistryObject<Item> TITANIUM_TRIDENT = ITEMS.register("titanium_trident",
			() -> new TridentItem(new Item.Properties().maxDamage(250).group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> UNOBTANIUM_DRILL = ITEMS.register("unobtanium_drill",
			() -> new UnobtaniumDrill(ModItemTier.UNOBTANIUM, 7, -3f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> UNOBTANIUM_SWORD = ITEMS.register("unobtanium_sword",
			() -> new UnobtaniumSword(ModItemTier.UNOBTANIUM, 0, -2f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FIREGEM_SWORD = ITEMS.register("firegem_sword",
			() -> new FireGemSword(ModItemTier.FIREGEM, 3, -2.4f, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FIREGEM_BOW = ITEMS.register("firegem_bow",
			() -> new FireBowItem(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SHULKER_BOW = ITEMS.register("shulker_bow",
			() -> new ShulkerBowItem(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> AIRGEM_SWORD = ITEMS.register("airgem_sword",
			() -> new AirGemSword(ModItemTier.AIRGEM, 3, -2.4f, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> TORCH_BOW = ITEMS.register("torch_bow",
			() -> new TorchBowItem(new Item.Properties().group(ProgtechItemGroup.instance)));

	// Armour
	public static final RegistryObject<Item> NIGHTVISION_GOGGLES = ITEMS.register("nightvision_goggles",
			() -> new NightVisionGoggles(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FLIPPERS = ITEMS.register("flippers",
			() -> new Flippers(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SCUBA_GEAR = ITEMS.register("scuba_gear",
			() -> new ScubaGear(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SLOWFALL_BOOTS = ITEMS.register("slowfall_boots",
			() -> new SlowFallBoots(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> HEALTH_CHESTPLATE = ITEMS.register("health_chestplate",
			() -> new HealthChestplate(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> JETPACK_ONE = ITEMS.register("jetpack_1",
			() -> new JetPack1(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> JETPACK_TWO = ITEMS.register("jetpack_2",
			() -> new JetPack2(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> JETPACK_ARMORED = ITEMS.register("jetpack_armored",
			() -> new JetPackArmored(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SPACE_HELMET = ITEMS.register("space_helmet",
			() -> new SpaceHelmet(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> LONGFALL_BOOTS = ITEMS.register("longfall_boots",
			() -> new LongFallBoots(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> VOID_STRIDER_LEGGINGS = ITEMS.register("void_strider_leggings",
			() -> new VoidStriderLeggings(EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> SKY_GUARDIAN_HELM = ITEMS.register("sky_guardian_helm",
			() -> new SkyGuardianHelm(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> NINJA_ROBE = ITEMS.register("ninja_robe",
			() -> new NinjaRobe(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> CARBIDE_HELMET = ITEMS.register("carbide_helmet",
			() -> new ArmorItem(ModArmorMaterial.CARBIDE, EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> CARBIDE_CHESTPLATE = ITEMS.register("carbide_chestplate",
			() -> new ArmorItem(ModArmorMaterial.CARBIDE, EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> CARBIDE_LEGGINGS = ITEMS.register("carbide_leggings",
			() -> new ArmorItem(ModArmorMaterial.CARBIDE, EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> CARBIDE_BOOTS = ITEMS.register("carbide_boots",
			() -> new ArmorItem(ModArmorMaterial.CARBIDE, EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet",
			() -> new ArmorItem(ModArmorMaterial.OBSIDIAN, EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate",
			() -> new ArmorItem(ModArmorMaterial.OBSIDIAN, EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings",
			() -> new ArmorItem(ModArmorMaterial.OBSIDIAN, EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots",
			() -> new ArmorItem(ModArmorMaterial.OBSIDIAN, EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> FEYSTEEL_HELMET = ITEMS.register("feysteel_helmet",
			() -> new FeySteelArmorItem(ModArmorMaterial.FEYSTEEL, EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FEYSTEEL_CHESTPLATE = ITEMS.register("feysteel_chestplate",
			() -> new FeySteelArmorItem(ModArmorMaterial.FEYSTEEL, EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> FEYSTEEL_LEGGINGS = ITEMS.register("feysteel_leggings",
			() -> new FeySteelArmorItem(ModArmorMaterial.FEYSTEEL, EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> FEYSTEEL_BOOTS = ITEMS.register("feysteel_boots",
			() -> new FeySteelArmorItem(ModArmorMaterial.FEYSTEEL, EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MODULAR_HELMET = ITEMS.register("modular_helmet",
			() -> new ArmorItem(new ModularArmor.Material(), EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MODULAR_CHESTPLATE = ITEMS.register("modular_chestplate",
			() -> new ModularArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> MODULAR_LEGGINGS = ITEMS.register("modular_leggings",
			() -> new ArmorItem(new ModularArmor.Material(), EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> MODULAR_BOOTS = ITEMS.register("modular_boots",
			() -> new ArmorItem(new ModularArmor.Material(), EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> BEDROCKIUM_HELMET = ITEMS.register("bedrockium_helmet",
			() -> new ArmorItem(new BedrockiumArmor.Material(), EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> BEDROCKIUM_CHESTPLATE = ITEMS.register("bedrockium_chestplate",
			() -> new BedrockiumArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> BEDROCKIUM_LEGGINGS = ITEMS.register("bedrockium_leggings",
			() -> new ArmorItem(new BedrockiumArmor.Material(), EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> BEDROCKIUM_BOOTS = ITEMS.register("bedrockium_boots",
			() -> new ArmorItem(new BedrockiumArmor.Material(), EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
			() -> new ArmorItem(new TitaniumArmor.Material(), EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
			() -> new TitaniumArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
			() -> new ArmorItem(new TitaniumArmor.Material(), EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
			() -> new ArmorItem(new TitaniumArmor.Material(), EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
			() -> new ArmorItem(ModArmorMaterial.STEEL, EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
			() -> new ArmorItem(ModArmorMaterial.STEEL, EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
			() -> new ArmorItem(ModArmorMaterial.STEEL, EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
			() -> new ArmorItem(ModArmorMaterial.STEEL, EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FIREGEM_HELMET = ITEMS.register("firegem_helmet",
			() -> new FireGemArmor(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FIREGEM_CHESTPLATE = ITEMS.register("firegem_chestplate",
			() -> new FireGemArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> FIREGEM_LEGGINGS = ITEMS.register("firegem_leggings",
			() -> new FireGemArmor(EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> FIREGEM_BOOTS = ITEMS.register("firegem_boots",
			() -> new FireGemArmor(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> AIRGEM_HELMET = ITEMS.register("airgem_helmet",
			() -> new AirGemArmor(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> AIRGEM_CHESTPLATE = ITEMS.register("airgem_chestplate",
			() -> new AirGemArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> AIRGEM_LEGGINGS = ITEMS.register("airgem_leggings",
			() -> new AirGemArmor(EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> AIRGEM_BOOTS = ITEMS.register("airgem_boots",
			() -> new AirGemArmor(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> WATERGEM_HELMET = ITEMS.register("watergem_helmet",
			() -> new ArmorItem(ModArmorMaterial.WATERGEM, EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> WATERGEM_CHESTPLATE = ITEMS.register("watergem_chestplate",
			() -> new WaterGemArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> WATERGEM_LEGGINGS = ITEMS.register("watergem_leggings",
			() -> new ArmorItem(ModArmorMaterial.WATERGEM, EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> WATERGEM_BOOTS = ITEMS.register("watergem_boots",
			() -> new ArmorItem(ModArmorMaterial.WATERGEM, EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> EARTHGEM_HELMET = ITEMS.register("earthgem_helmet",
			() -> new EarthGemArmor(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> EARTHGEM_CHESTPLATE = ITEMS.register("earthgem_chestplate",
			() -> new EarthGemArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> EARTHGEM_LEGGINGS = ITEMS.register("earthgem_leggings",
			() -> new EarthGemArmor(EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> EARTHGEM_BOOTS = ITEMS.register("earthgem_boots",
			() -> new EarthGemArmor(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> NYMPHARIUM_HELMET = ITEMS.register("nympharium_helmet",
			() -> new NymphariumArmor(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> NYMPHARIUM_CHESTPLATE = ITEMS.register("nympharium_chestplate",
			() -> new NymphariumArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> NYMPHARIUM_LEGGINGS = ITEMS.register("nympharium_leggings",
			() -> new NymphariumArmor(EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> NYMPHARIUM_BOOTS = ITEMS.register("nympharium_boots",
			() -> new NymphariumArmor(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	
	// Fey Food 
	public static final RegistryObject<Item> COFFEE_BEAN = ITEMS.register("coffee_beans",
			() -> new FeyFood(new Item.Properties().group(ProgtechItemGroup.instance).food(
					new Food.Builder().hunger(1).saturation(2f).fastToEat().build())));
	
	public static final RegistryObject<Item> COFFEE = ITEMS.register("coffee",
			() -> new FeyFood(new Item.Properties().group(ProgtechItemGroup.instance).food(
					new Food.Builder().hunger(3).saturation(5f).setAlwaysEdible()
					.effect(new EffectInstance(Effects.SPEED, 100, 49), 0.9f).build())));
}
