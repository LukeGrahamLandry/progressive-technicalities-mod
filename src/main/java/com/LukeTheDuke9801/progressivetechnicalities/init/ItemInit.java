package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities.ProgtechItemGroup;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.DragonBreathBall;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.Fireball;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.Slowball;
import com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff.TNTBall;
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
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class ItemInit {
	
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ProgressiveTechnicalities.MOD_ID);

	public static final RegistryObject<Item> CHROMIUM_ROD = createSimpleItem("chromium_rod");
	public static final RegistryObject<Item> POTION_CORE = createSimpleItem("potion_core");
	public static final RegistryObject<Item> BASIC_POTION_CORE = createSimpleItem("basic_potion_core");
	public static final RegistryObject<Item> MACHINE_BASE = createSimpleItem("machine_base");
	public static final RegistryObject<Item> PORTAL_KEY = createSimpleItem("portal_key");
	public static final RegistryObject<Item> FLIGHT_COMPONENT = createSimpleItem("flight_component");
	public static final RegistryObject<Item> BASIC_ROCKET_CASING = createSimpleItem("basic_rocket_casing");
	public static final RegistryObject<Item> ADVANCED_ROCKET_CASING = createSimpleItem("advanced_rocket_casing");
	public static final RegistryObject<Item> AIR_GEM = createSimpleItem("air_gem");
	public static final RegistryObject<Item> FIRE_GEM = createSimpleItem("fire_gem");
	public static final RegistryObject<Item> WATER_GEM = createSimpleItem("water_gem");
	public static final RegistryObject<Item> EARTH_GEM = createSimpleItem("earth_gem");
	public static final RegistryObject<Item> END_GEM = createSimpleItem("end_gem");
	public static final RegistryObject<Item> DILITHIUM = createSimpleItem("dilithium");
	public static final RegistryObject<Item> CREPUSCULUM =createSimpleItem("crepusculum");
	public static final RegistryObject<Item> CHROMIUM_INGOT = createSimpleItem("chromium_ingot");
	public static final RegistryObject<Item> CARBIDE_INGOT = createSimpleItem("carbide_ingot");
	public static final RegistryObject<Item> TITANIUM_INGOT = createSimpleItem("titanium_ingot");
	public static final RegistryObject<Item> SPACE_INGOT = createSimpleItem("space_ingot");
	public static final RegistryObject<Item> FEYSTEEL_INGOT = createSimpleItem("feysteel_ingot");
	public static final RegistryObject<Item> OBSIDIAN_INGOT = createSimpleItem("obsidian_ingot");
	public static final RegistryObject<Item> NYMPHARIUM_INGOT = createSimpleItem("nympharium_ingot");
	public static final RegistryObject<Item> STEEL_INGOT = createSimpleItem("steel_ingot");
	public static final RegistryObject<Item> VARIDIUM_INGOT = createSimpleItem("varidium_ingot");
	public static final RegistryObject<Item> MELANGE_CLUMP = createSimpleItem("melange_clump");
	public static final RegistryObject<Item> UNOBTANIUM_INGOT = createSimpleItem("unobtanium_ingot");
	public static final RegistryObject<Item> UNOBTANIUM_SHARD = createSimpleItem("unobtanium_shard");
	public static final RegistryObject<Item> BEDROCKIUM_SHARD = createSimpleItem("bedrockium_shard");
	public static final RegistryObject<Item> BEDROCKIUM_INGOT = createSimpleItem("bedrockium_ingot");
	public static final RegistryObject<Item> IRON_DUST = createSimpleItem("iron_dust");
	public static final RegistryObject<Item> GOLD_DUST = createSimpleItem("gold_dust");
	public static final RegistryObject<Item> CHROMIUM_DUST = createSimpleItem("chromium_dust");
	public static final RegistryObject<Item> CARBIDE_DUST = createSimpleItem("carbide_dust");
	public static final RegistryObject<Item> TITANIUM_DUST = createSimpleItem("titanium_dust");
	public static final RegistryObject<Item> FEYSTEEL_DUST = createSimpleItem("feysteel_dust");
	public static final RegistryObject<Item> STEEL_DUST = createSimpleItem("steel_dust");
	public static final RegistryObject<Item> NYMPHARIUM_DUST = createSimpleItem("nympharium_dust");
	public static final RegistryObject<Item> VARIDIUM_DUST = createSimpleItem("varidium_dust");
	public static final RegistryObject<Item> OBSIDIAN_DUST = createSimpleItem("obsidian_dust");
	public static final RegistryObject<Item> DIASTIMA_DUST = createSimpleItem("diastima_dust");
	public static final RegistryObject<Item> UNOBTANIUM_DUST = createSimpleItem("unobtanium_dust");
	public static final RegistryObject<Item> BEDROCKIUM_DUST = createSimpleItem("bedrockium_dust");

	public static final RegistryObject<Item> TINY_COAL = ITEMS.register("tiny_coal",
			() -> new TinyCoal(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MAGIC_EGGSHELL = ITEMS.register("magic_eggshell",
			() -> new MagicEggShell(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OIL_PORTAL_KEY = ITEMS.register("oil_portal_key",
			() -> new OilPortalKey(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> MELANGE = ITEMS.register("melange",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance).food(
					new Food.Builder().hunger(20).saturation(20.0F).build())));
	
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

	public static final RegistryObject<Item> LIGHTNING_BOTTLE = ITEMS.register("lightning_bottle",
			() -> new LightningBottle(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(16)));

	public static final RegistryObject<Item> CHROMIUM_FUEL = ITEMS.register("chromium_fuel",
			() -> new ChromiumFuel(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> PROGRESSIVE_LOOT_BOX = ITEMS.register("progressive_loot_box",
			() -> new LootBox(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

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

	// Staffs
	public static final RegistryObject<Item> STAFF = createSimpleItem("staff");
	
	public static final RegistryObject<Item> WEATHER_STAFF = ITEMS.register("weather_staff",
			() -> new WeatherStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> TIME_STAFF = ITEMS.register("time_staff",
			() -> new TimeStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ENDER_STAFF = ITEMS.register("ender_staff",
			() -> new EnderStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	public static final RegistryObject<Item> RECALL_STAFF = ITEMS.register("recall_staff",
			() -> new RecallStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> NETHER_STAFF = ITEMS.register("nether_staff",
			() -> new NetherStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> XP_STAFF = ITEMS.register("xp_staff",
			() -> new XPStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

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
	
	public static final RegistryObject<Item> AMMETER = ITEMS.register("ammeter",
			() -> new Ammeter(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	public static final RegistryObject<Item> HAND_HELD_TELEPORTER = ITEMS.register("hand_held_teleporter",
			() -> new HandHeldTeleporter(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	public static final RegistryObject<Item> UNDO_STAFF = ITEMS.register("undo_staff",
			() -> new UndoStaff(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	// Wizard Staffs
	public static final RegistryObject<Item> FIREBALL_STAFF = ITEMS.register("blaze_staff",
			() -> new ProjectileStaff(Fireball::new, 500, 10));

	public static final RegistryObject<Item> SLOWBALL_STAFF = ITEMS.register("snow_staff",
			() -> new ProjectileStaff(Slowball::new, 2000, 0));

	public static final RegistryObject<Item> DRAGONBREATH_STAFF = ITEMS.register("dragonbreath_staff",
			() -> new ProjectileStaff(DragonBreathBall::new, 200, 100));

	public static final RegistryObject<Item> TNT_STAFF = ITEMS.register("tnt_staff",
			() -> new ProjectileStaff(TNTBall::new, 500, 15));

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
	
	public static final RegistryObject<Item> ANTI_LEVITATION_CHARM = ITEMS.register("anti_levitation_charm",
			() -> new AntiEffectCharm(Effects.LEVITATION, new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> ANTI_BLINDNESS_CHARM = ITEMS.register("anti_blindness_charm",
			() -> new AntiEffectCharm(Effects.BLINDNESS, new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	public static final RegistryObject<Item> WATERGEM_CHARM = ITEMS.register("watergem_charm",
			() -> new WaterGemCharm(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));

	// Tools
	public static final int OBSIDIAN_TOOLS = createSimpleToolSet(ModItemTier.OBSIDIAN, "obsidian");
	public static final int CARBIDE_TOOLS = createSimpleToolSet(ModItemTier.CARBIDE, "carbide");
	public static final int FEYSTEEL_TOOLS = createSimpleToolSet(ModItemTier.FEYSTEEL, "feysteel");

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

	public static final RegistryObject<Item> TORCH_BOW = ITEMS .register("torch_bow",
			() -> new TorchBowItem(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> MULTIBOW = ITEMS.register("multibow",
			() -> new MultiShotBow(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> FIRE_BALL_BOW = ITEMS.register("fireballbow",
			() -> new FireChargeBow(new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> ARRAKIS_KNIFE = ITEMS.register("arrakis_knife",
			() -> new SwordItem(ModItemTier.FIREGEM, 5, -2f, new Item.Properties().group(ProgtechItemGroup.instance)));

	// Armour
	public static final ArmorSet OBSIDIAN_ARMOR_SET = createSimpleArmorSet(ModArmorMaterial.OBSIDIAN, "obsidian");
	public static final ArmorSet CARBIDE_ARMOR_SET = createSimpleArmorSet(ModArmorMaterial.CARBIDE, "carbide");
	public static final ArmorSet STEEL_ARMOR_SET = createSimpleArmorSet(ModArmorMaterial.STEEL, "steel");

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

	public static final RegistryObject<Item> MODULAR_HELMET = ITEMS.register("modular_helmet",
			() -> new ArmorItem(new ModularArmor.Material(), EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MODULAR_CHESTPLATE = ITEMS.register("modular_chestplate",
			() -> new ModularArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> MODULAR_LEGGINGS = ITEMS.register("modular_leggings",
			() -> new ArmorItem(new ModularArmor.Material(), EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final RegistryObject<Item> MODULAR_BOOTS = ITEMS.register("modular_boots",
			() -> new ArmorItem(new ModularArmor.Material(), EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

	public static final ArmorSet FEYSTEEL_ARMOR_SET = createComplexArmorSet("feysteel", (slot) -> {
		return new FeySteelArmorItem(slot, new Item.Properties().group(ProgtechItemGroup.instance));
	});

	public static final ArmorSet BEDROCKIUM_ARMOR_SET = createComplexArmorSet("bedrockium", (slot) -> {
		return new BedrockiumArmor(slot, new Item.Properties().group(ProgtechItemGroup.instance));
	});

	public static final ArmorSet TITANIUM_ARMOR_SET = createComplexArmorSet("titanium", (slot) -> {
		return new TitaniumArmor(slot, new Item.Properties().group(ProgtechItemGroup.instance));
	});

	public static final ArmorSet AIRGEM_ARMOR_SET = createComplexArmorSet("airgem", (slot) -> {
		return new AirGemArmor(slot, new Item.Properties().group(ProgtechItemGroup.instance));
	});

	public static final ArmorSet FIREGEM_ARMOR_SET = createComplexArmorSet("firegem", (slot) -> {
		return new FireGemArmor(slot, new Item.Properties().group(ProgtechItemGroup.instance));
	});

	public static final ArmorSet WATERGEM_ARMOR_SET = createComplexArmorSet("watergem", (slot) -> {
		return new WaterGemArmor(slot, new Item.Properties().group(ProgtechItemGroup.instance));
	});

	public static final ArmorSet EARTHGEM_ARMOR_SET = createComplexArmorSet("earthgem", (slot) -> {
		return new EarthGemArmor(slot, new Item.Properties().group(ProgtechItemGroup.instance));
	});

	public static final ArmorSet NYMPHARIUM_ARMOR_SET = createComplexArmorSet("nympharium", (slot) -> {
		return new NymphariumArmor(slot, new Item.Properties().group(ProgtechItemGroup.instance));
	});
	
	// Fey Food 
	public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry",
			() -> new FeyFood(new Item.Properties().group(ProgtechItemGroup.instance).food(
					new Food.Builder().hunger(1).saturation(2f).build())));
	
	public static final RegistryObject<Item> COFFEE = ITEMS.register("coffee",
			() -> new FeyFood(new Item.Properties().group(ProgtechItemGroup.instance).food(
					new Food.Builder().hunger(3).saturation(5f).setAlwaysEdible()
					.effect(new EffectInstance(Effects.SPEED, 100, 49), 0.9f).build())));

	public static final RegistryObject<Item> GOLDEN_PLUM = ITEMS.register("golden_plum",
			() -> new FeyFood(new Item.Properties().group(ProgtechItemGroup.instance).food(
					new Food.Builder().hunger(8).saturation(0f)
							.effect(() -> new EffectInstance(Effects.WITHER, 600, 2), 0.5F)
							.effect(() -> new EffectInstance(Effects.REGENERATION, 600, 2), 0.5F).build())));

	public static final RegistryObject<Item> RAW_FEY_MEAT = ITEMS.register("raw_fey_meat",
			() -> new FeyFood(new Item.Properties().group(ProgtechItemGroup.instance).food(
					new Food.Builder().hunger(2).saturation(2f)
							.effect(() -> new EffectInstance(Effects.HUNGER, 200, 0), 0.5f).build())));

	public static final RegistryObject<Item> FEY_STEAK = ITEMS.register("fey_steak",
			() -> new FeyFood(new Item.Properties().group(ProgtechItemGroup.instance).food(
					new Food.Builder().hunger(10).saturation(7f).setAlwaysEdible()
							.effect(() -> new EffectInstance(Effects.STRENGTH, 1200, 1), 0.2f).build())));

	private static RegistryObject<Item> createSimpleItem(String name){
		return ITEMS.register(name, () -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	}

	private static int createSimpleToolSet(ModItemTier tier, String name){
		ITEMS.register(name + "_sword",
				() -> new SwordItem(tier, 3, -2.4f, new Item.Properties().group(ProgtechItemGroup.instance)));

		ITEMS.register(name + "_pickaxe",
				() -> new PickaxeItem(tier, 1, -2.8f, new Item.Properties().group(ProgtechItemGroup.instance)));

		ITEMS.register(name + "_shovel",
				() -> new ShovelItem(tier, 1.5f, -3.0f, new Item.Properties().group(ProgtechItemGroup.instance)));

		ITEMS.register(name + "_axe",
				() -> new AxeItem(tier, 6.0f, -3.1f, new Item.Properties().group(ProgtechItemGroup.instance)));

		ITEMS.register(name + "_hoe",
				() -> new HoeItem(tier, -1.0f, new Item.Properties().group(ProgtechItemGroup.instance)));

		return 1;
	}

	private static ArmorSet createSimpleArmorSet(ModArmorMaterial tier, String name){
		RegistryObject<Item> head = ITEMS.register(name + "_helmet",
				() -> new ArmorItem(tier, EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));

		RegistryObject<Item> chest = ITEMS.register(name + "_chestplate",
				() -> new ArmorItem(tier, EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));

		RegistryObject<Item> legs = ITEMS.register(name + "_leggings",
				() -> new ArmorItem(tier, EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));

		RegistryObject<Item> feet = ITEMS.register(name + "_boots",
				() -> new ArmorItem(tier, EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

		return new ArmorSet(head, chest, legs, feet);
	}

	private static ArmorSet createComplexArmorSet(String name, Function<EquipmentSlotType, ArmorItem> armorConstructer){
		RegistryObject<Item> head = ITEMS.register(name + "_helmet",
				() -> armorConstructer.apply(EquipmentSlotType.HEAD));

		RegistryObject<Item> chest = ITEMS.register(name + "_chestplate",
				() -> armorConstructer.apply(EquipmentSlotType.CHEST));

		RegistryObject<Item> legs = ITEMS.register(name + "_leggings",
				() -> armorConstructer.apply(EquipmentSlotType.LEGS));

		RegistryObject<Item> feet = ITEMS.register(name + "_boots",
				() -> armorConstructer.apply(EquipmentSlotType.FEET));

		return new ArmorSet(head, chest, legs, feet);
	}

	// Useage example: ItemInit.SteelArmorSet.HELMET.get() instead of ItemInit.STEEL_HELMET.get()
	public static class ArmorSet {
		public RegistryObject<Item> HELMET, CHESTPLATE, LEGGINGS, BOOTS;
		public ArmorSet(RegistryObject<Item> head, RegistryObject<Item> chest, RegistryObject<Item> legs, RegistryObject<Item> feet){
			this.HELMET = head;
			this.CHESTPLATE = chest;
			this.LEGGINGS = legs;
			this.BOOTS = feet;
		}
	}

}
