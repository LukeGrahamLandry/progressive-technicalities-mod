package com.LukeTheDuke9801.progressivetechnicalities.init;

import java.util.function.Supplier;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities.ProgtechItemGroup;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.TinyCoal;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.BlazeStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.CreativeStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.EnderStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.NetherStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.RecallStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.SnowStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.TNTStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.TimeStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.WeatherStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.staffs.XPStaff;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.tools.TitaniumSword;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid= ProgressiveTechnicalities.MOD_ID, bus= Bus.MOD)
@ObjectHolder(ProgressiveTechnicalities.MOD_ID)
public class ItemInit 
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
			ProgressiveTechnicalities.MOD_ID);

	public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("chromium_rod",
			() -> new Item(new Item.Properties().group(ProgtechItemGroup.instance)));
	
	public static final Item example_item = null;
	// public static final Item chromium_rod = null;
	public static final Item chromium_ingot = null;
	public static final Item carbide_ingot = null;
	public static final Item titanium_ingot = null;
	public static final Item wealth_ingot = null;
	public static final Item tiny_coal = null;
	
	// Staffs
	public static final Item staff = null;
	public static final Item weather_staff = null;
	public static final Item time_staff = null;
	public static final Item ender_staff = null;
	public static final Item snow_staff = null;
	public static final Item recall_staff = null;
	public static final Item nether_staff = null;
	public static final Item xp_staff = null;
	public static final Item blaze_staff = null;
	public static final Item tnt_staff = null;
	public static final Item creative_staff = null;
	
	// Tools
	public static final Item carbide_sword = null;
	public static final Item carbide_pickaxe = null;
	public static final Item carbide_shovel = null;
	public static final Item carbide_axe = null;
	public static final Item carbide_hoe = null;
	public static final Item titanium_sword = null;
	
	// Armour
	public static final Item carbide_helmet = null;
	public static final Item carbide_chestplate = null;
	public static final Item carbide_leggings = null;
	public static final Item carbide_boots = null;
	public static final Item titanium_helmet = null;
	public static final Item titanium_chestplate = null;
	public static final Item titanium_leggings = null;
	public static final Item titanium_boots = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		// event.getRegistry().register(new Item(new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("chromium_rod"));
		event.getRegistry().register(new Item(new Item.Properties().group(ProgtechItemGroup.instance).food(new Food.Builder().hunger(2).saturation(0f).setAlwaysEdible().fastToEat().effect(new EffectInstance(Effects.SPEED, 100, 2), 1).effect(new EffectInstance(Effects.HUNGER, 100, 1), 1).build())).setRegistryName("example_item"));
		event.getRegistry().register(new Item(new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("chromium_ingot"));
		event.getRegistry().register(new Item(new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_ingot"));
		event.getRegistry().register(new Item(new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("titanium_ingot"));
		event.getRegistry().register(new Item(new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("wealth_ingot"));
		event.getRegistry().register(new TinyCoal(new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("tiny_coal"));
		
		// Staffs
		event.getRegistry().register(new Item(new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("staff"));
		event.getRegistry().register(new WeatherStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("weather_staff"));
		event.getRegistry().register(new TimeStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("time_staff"));
		event.getRegistry().register(new EnderStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("ender_staff"));
		event.getRegistry().register(new SnowStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("snow_staff"));
		event.getRegistry().register(new RecallStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("recall_staff"));
		event.getRegistry().register(new NetherStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("nether_staff"));
		event.getRegistry().register(new XPStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("xp_staff"));
		event.getRegistry().register(new BlazeStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("blaze_staff"));
		event.getRegistry().register(new TNTStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("tnt_staff"));
		event.getRegistry().register(new CreativeStaff(new Item.Properties().maxStackSize(1).group(ProgtechItemGroup.instance)).setRegistryName("creative_staff"));
		
		// Tools
		event.getRegistry().register(new SwordItem(ModItemTier.CARBIDE, 3, -2.0f, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.CARBIDE, -2, -1.0f, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_pickaxe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.CARBIDE, -2, -1.0f, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_shovel"));
		event.getRegistry().register(new AxeItem(ModItemTier.CARBIDE, 5, -2.8f, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.CARBIDE, 3.0f, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_hoe"));
		event.getRegistry().register(new TitaniumSword(ModItemTier.TITANIUM, Integer.MAX_VALUE, -2.0f, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("titanium_sword"));
	
		// Armour
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.CARBIDE, EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_helmet"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.CARBIDE, EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_chestplate"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.CARBIDE, EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_leggings"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.CARBIDE, EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("carbide_boots"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlotType.HEAD, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("titanium_helmet"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlotType.CHEST, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("titanium_chestplate"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlotType.LEGS, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("titanium_leggings"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlotType.FEET, new Item.Properties().group(ProgtechItemGroup.instance)).setRegistryName("titanium_boots"));
	}
	
	public enum ModItemTier implements IItemTier{
		// tier, durability, efficency, attack damage, enchantability, repair material
		CARBIDE(4, 2500, 15.0f, 4.0f, 30, () -> {
			return Ingredient.fromItems(ItemInit.carbide_ingot); 
		}),
		TITANIUM(4, 2500, 10.0f, 0.0f, 50, () -> {
			return Ingredient.fromItems(ItemInit.titanium_ingot);
		});
		
		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final LazyValue<Ingredient> repairMaterial;
		
		private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = new LazyValue<>(repairMaterial);
		}
		
		@Override
		public int getHarvestLevel(){
			return harvestLevel;
		}
		@Override
		public int getMaxUses(){
			return this.maxUses;
		}
		@Override
		public float getEfficiency(){
			return this.efficiency;
		}
		@Override
		public float getAttackDamage(){
			return this.attackDamage;
		}
		@Override
		public int getEnchantability(){
			return this.enchantability;
		}
		@Override
		public Ingredient getRepairMaterial(){
			return this.repairMaterial.getValue();
		}
		
	}
	
	public enum ModArmorMaterial implements IArmorMaterial {
		   CARBIDE(ProgressiveTechnicalities.MOD_ID + ":carbide", 100, new int[]{4, 7, 9, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F, () -> {
		      return Ingredient.fromItems(ItemInit.carbide_ingot);}), 
		   TITANIUM(ProgressiveTechnicalities.MOD_ID + ":titanium", 150, new int[]{2, 4, 20, 2}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.0F, () -> {
			      return Ingredient.fromItems(ItemInit.titanium_ingot);});
		
		   private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
		   private final String name;
		   private final int maxDamageFactor;
		   private final int[] damageReductionAmountArray;
		   private final int enchantability;
		   private final SoundEvent soundEvent;
		   private final float toughness;
		   private final LazyValue<Ingredient> repairMaterial;

		   private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float toughnessIn, Supplier<Ingredient> repairMaterialSupplier) {
		      this.name = nameIn;
		      this.maxDamageFactor = maxDamageFactorIn;
		      this.damageReductionAmountArray = damageReductionAmountsIn;
		      this.enchantability = enchantabilityIn;
		      this.soundEvent = equipSoundIn;
		      this.toughness = toughnessIn;
		      this.repairMaterial = new LazyValue<>(repairMaterialSupplier);
		   }

		   public int getDurability(EquipmentSlotType slotIn) {
		      return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
		   }

		   public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		      return this.damageReductionAmountArray[slotIn.getIndex()];
		   }

		   public int getEnchantability() {
		      return this.enchantability;
		   }

		   public SoundEvent getSoundEvent() {
		      return this.soundEvent;
		   }

		   public Ingredient getRepairMaterial() {
		      return this.repairMaterial.getValue();
		   }

		   @OnlyIn(Dist.CLIENT)
		   public String getName() {
		      return this.name;
		   }

		   public float getToughness() {
		      return this.toughness;
		   }
		}
}
