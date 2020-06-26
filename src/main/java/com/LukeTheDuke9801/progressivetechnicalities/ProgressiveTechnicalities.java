package com.LukeTheDuke9801.progressivetechnicalities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInitNew;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.world.gen.ChromiumOreGen;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod("progressivetechnicalities")
@Mod.EventBusSubscriber(modid = ProgressiveTechnicalities.MOD_ID, bus=Bus.MOD)
public class ProgressiveTechnicalities
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "progressivetechnicalities";
    public static ProgressiveTechnicalities instance;

    public ProgressiveTechnicalities() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
    	
    	ItemInitNew.ITEMS.register(modEventBus);
    	BlockInit.BLOCKS.register(modEventBus);
    	ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        
        instance = this;
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    	final IForgeRegistry<Item> registry = event.getRegistry();
    	BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
    		final Item.Properties properties = new Item.Properties().group(ProgtechItemGroup.instance);
    		final BlockItem blockItem = new BlockItem(block, properties);
    		blockItem.setRegistryName(block.getRegistryName());
    		registry.register(blockItem);
    	});
    	LOGGER.debug("Registered BlockItems");
    }

    private void setup(final FMLCommonSetupEvent event){
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event)  {
    	
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    	ChromiumOreGen.generateOre();
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
