package com.LukeTheDuke9801.progressivetechnicalities;

import com.LukeTheDuke9801.progressivetechnicalities.init.*;
import com.LukeTheDuke9801.progressivetechnicalities.world.biomes.FeyBiome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LukeTheDuke9801.progressivetechnicalities.events.AddToLootTables;
import com.LukeTheDuke9801.progressivetechnicalities.events.ModPlayerEvent;
import com.LukeTheDuke9801.progressivetechnicalities.events.ScalingMobs;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.ModIceBlock;
import com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import static com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen.isVanillaOverworldBiome;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("progressivetechnicalities")
@Mod.EventBusSubscriber(modid = ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD)
public class ProgressiveTechnicalities
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "progressivetechnicalities";
    public static ProgressiveTechnicalities instance;
    public static final ResourceLocation OIL_DIM_TYPE = new ResourceLocation(MOD_ID, "oil");
    public static final ResourceLocation FEY_DIM_TYPE = new ResourceLocation(MOD_ID, "feywild");
    public static final ResourceLocation ARRAKIS_DIM_TYPE = new ResourceLocation(MOD_ID, "arrakis");
    public static final ResourceLocation PANDORA_DIM_TYPE = new ResourceLocation(MOD_ID, "pandora");
    public static final ResourceLocation LUNA_DIM_TYPE = new ResourceLocation(MOD_ID, "luna");

    public ProgressiveTechnicalities() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
    	
    	EnchantmentInit.ENCHANTMENTS.register(modEventBus);
    	ItemInit.ITEMS.register(modEventBus);
    	FluidInit.FLUIDS.register(modEventBus);
    	BlockInit.BLOCKS.register(modEventBus);
    	ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
    	ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
    	ModEntityTypes.ENTITY_TYPES.register(modEventBus);
    	BiomeInit.BIOMES.register(modEventBus);
    	DimensionInit.MOD_DIMENSIONS.register(modEventBus);
    	
    	MinecraftForge.EVENT_BUS.register(ModPlayerEvent.class);
    	MinecraftForge.EVENT_BUS.register(ScalingMobs.class);
    	MinecraftForge.EVENT_BUS.addListener(AddToLootTables::lootLoad);

		// MinecraftForge.EVENT_BUS.register(ModEventTypes.class);
        
        instance = this;
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    	final IForgeRegistry<Item> registry = event.getRegistry();
    	BlockInit.BLOCKS.getEntries().stream().filter(block -> !(block.get() instanceof FlowingFluidBlock || block.get() instanceof ModIceBlock)).map(RegistryObject::get).forEach(block -> {
    		final Item.Properties properties = new Item.Properties().group(ProgtechItemGroup.instance);
    		final BlockItem blockItem = new BlockItem(block, properties);
    		blockItem.setRegistryName(block.getRegistryName());
    		registry.register(blockItem);
    	});
    	LOGGER.debug("Registered BlockItems");
    }
    
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
    	BiomeInit.registerBiomes();
    }

	private void setup(final FMLCommonSetupEvent event){
		// .addStructure tells Minecraft that this biome can start the generation of the structure.
		// .addFeature tells Minecraft that the pieces of the structure can be made in this biome.
		//
		// Thus it is best practice to do .addFeature for all biomes and do .addStructure as well for
		// the biome you want the structure to spawn in. That way, the structure will only spawn in the
		// biomes you want but will not get cut off when generating if part of it goes into a non-valid biome.
		for (Biome biome : ForgeRegistries.BIOMES){
			if (isVanillaOverworldBiome(biome)){
				biome.addStructure(FeatureInit.WANDERER_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
				biome.addStructure(FeatureInit.NETHER_DUNGEON.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			}

			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureInit.WANDERER_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
					.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureInit.NETHER_DUNGEON.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
					.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

			if (biome == BiomeInit.FEY_PLAINS.get()){
				biome.addStructure(FeatureInit.FEY_HENGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
				biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureInit.FEY_HENGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
						.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
			}
		}
    }

    private void doClientStuff(final FMLClientSetupEvent event)  {
    	
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    	OreGen.generateAllOres();
    }

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		/**
		 * This method will be called by Forge when it is time for the mod to register features.
		 */
		@SubscribeEvent
		public static void onRegisterFeatures(final RegistryEvent.Register<Feature<?>> event) {
			//registers the structures/features.
			//If you don't do this, you'll crash.
			FeatureInit.registerFeatures(event);

			LOGGER.debug("features/structures registered.");
		}
	}

		public static class ProgtechItemGroup extends ItemGroup {
    	public static final ProgtechItemGroup instance = new ProgtechItemGroup(ItemGroup.GROUPS.length, "progtechtab");
    	private ProgtechItemGroup(int index, String label) {
    		super(index, label);
    	}

    	@Override
    	public ItemStack createIcon() {
    		return new ItemStack(BlockInit.CHROMIUM_BLOCK.get());
    	}
    }

    
}
