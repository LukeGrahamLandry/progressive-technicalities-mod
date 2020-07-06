package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities.ProgtechItemGroup;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.Battery;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.FeywildPortalKey;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.MagicEggShell;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.MobEgg;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.OilPortalKey;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.TinyCoal;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.BoostLeggings;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.FeySteelArmorItem;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.Flippers;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.HealthChestplate;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.JetPack1;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.JetPack2;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.LongFallBoots;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.NightVisionGoggles;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.ScubaGear;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.SlowFallBoots;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor.SpaceHelmet;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.charms.BoostCharm;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.charms.FireResCharm;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.BlazeStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.CreativeStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.EnderStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.MomentumStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.NetherStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.RecallStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.SnowStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.TNTStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.TimeStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.WeatherStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.XPStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools.TitaniumAIOT;
import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModArmorMaterial;
import com.LukeTheDuke9801.progressivetechnicalities.util.enums.ModItemTier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
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
	
	public static final RegistryObject<Item> WEALTH_INGOT = ITEMS.register("wealth_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SKY_GEM = ITEMS.register("sky_gem",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SPACE_INGOT = ITEMS.register("space_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> POTION_CORE = ITEMS.register("potion_core",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MACHINE_BASE = ITEMS.register("machine_base",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> TINY_COAL = ITEMS.register("tiny_coal",
			() -> new TinyCoal(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MAGIC_EGGSHELL = ITEMS.register("magic_eggshell",
			() -> new MagicEggShell(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MOB_EGG = ITEMS.register("mob_egg",
			() -> new MobEgg(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> PORTAL_KEY = ITEMS.register("portal_key",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OIL_PORTAL_KEY = ITEMS.register("oil_portal_key",
			() -> new OilPortalKey(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FEYWILD_PORTAL_KEY = ITEMS.register("feywild_portal_key",
			() -> new FeywildPortalKey(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FEYSTEEL_INGOT = ITEMS.register("feysteel_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> OBSIDIAN_INGOT = ITEMS.register("obsidian_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> ALUMINUM = ITEMS.register("aluminum",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> MELANGE_CLUMP = ITEMS.register("melange_clump",
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
	
	// Charms
	public static final RegistryObject<Item> BOOST_CHARM = ITEMS.register("boost_charm",
			() -> new BoostCharm(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
	public static final RegistryObject<Item> FIRERES_CHARM = ITEMS.register("fireres_charm",
			() -> new FireResCharm(new Item.Properties().group(ProgtechItemGroup.instance).maxStackSize(1)));
	
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
	 
	// Armour
	public static final RegistryObject<Item> NIGHTVISION_GOGGLES = ITEMS.register("nightvision_goggles",
			() -> new NightVisionGoggles(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> FLIPPERS = ITEMS.register("flippers",
			() -> new Flippers(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SCUBA_GEAR = ITEMS.register("scuba_gear",
			() -> new ScubaGear(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SLOWFALL_BOOTS = ITEMS.register("slowfall_boots",
			() -> new SlowFallBoots(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> BOOST_LEGGINGS = ITEMS.register("boost_leggings",
			() -> new BoostLeggings(EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> HEALTH_CHESTPLATE = ITEMS.register("health_chestplate",
			() -> new HealthChestplate(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> JETPACK_ONE = ITEMS.register("jetpack_1",
			() -> new JetPack1(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> JETPACK_TWO = ITEMS.register("jetpack_2",
			() -> new JetPack2(EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> SPACE_HELMET = ITEMS.register("space_helmet",
			() -> new SpaceHelmet(EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final RegistryObject<Item> LONGFALL_BOOTS = ITEMS.register("longfall_boots",
			() -> new LongFallBoots(EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)));

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

}
