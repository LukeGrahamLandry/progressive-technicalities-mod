package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities.ProgtechItemGroup;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.ModIceBlock;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.OilFluid;
import com.LukeTheDuke9801.progressivetechnicalities.objects.fluids.NymphariumFluid;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {
	
	public static final ResourceLocation OIL_STILL_RL = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "blocks/oil_still");
	public static final ResourceLocation OIL_FLOWING_RL = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "blocks/oil_flowing");
	public static final ResourceLocation OIL_OVERLAY_RL = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "blocks/oil_overlay");
	
	public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<Fluid>(ForgeRegistries.FLUIDS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<FlowingFluid> OIL_FLUID = FLUIDS.register("oil_fluid",
			() -> new OilFluid.Source());
			
	public static final RegistryObject<FlowingFluid> OIL_FLOWING = FLUIDS.register("oil_flowing",
			() -> new OilFluid.Flowing());
	
	public static final RegistryObject<FlowingFluidBlock> OIL_BLOCK = BlockInit.BLOCKS.register("oil",
			() -> new FlowingFluidBlock(() -> FluidInit.OIL_FLUID.get(), Block.Properties.create(Material.WATER)
					.doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));
	
	public static final RegistryObject<Item> OIL_BUCKET = ItemInit.ITEMS.register("oil_bucket",
			() -> new BucketItem(() -> FluidInit.OIL_FLUID.get(), new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ProgtechItemGroup.instance)));
	
	
	public static final ResourceLocation NYMPHARIUM_STILL_RL = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "blocks/nympharium_still");
	public static final ResourceLocation NYMPHARIUM_FLOWING_RL = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "blocks/nympharium_flowing");
	public static final ResourceLocation NYMPHARIUM_OVERLAY_RL = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "blocks/oil_overlay");
	
	public static final RegistryObject<FlowingFluid> NYMPHARIUM_FLUID = FLUIDS.register("nympharium_fluid",
			() -> new NymphariumFluid.Source());
			
	public static final RegistryObject<FlowingFluid> NYMPHARIUM_FLOWING = FLUIDS.register("nympharium_flowing",
			() -> new NymphariumFluid.Flowing());
	
	public static final RegistryObject<FlowingFluidBlock> NYMPHARIUM_FLUID_BLOCK = BlockInit.BLOCKS.register("nympharium",
			() -> new FlowingFluidBlock(() -> FluidInit.NYMPHARIUM_FLUID.get(), Block.Properties.create(Material.WATER)
					.doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));
	
	public static final RegistryObject<Item> NYMPHARIUM_FLUID_BUCKET = ItemInit.ITEMS.register("nympharium_fluid_bucket",
			() -> new BucketItem(() -> FluidInit.NYMPHARIUM_FLUID.get(), new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ProgtechItemGroup.instance)));
	
	
	// Ice
	public static final RegistryObject<Block> LAVA_ICE = BlockInit.BLOCKS.register("lava_ice",
			() -> new ModIceBlock(() -> Blocks.LAVA, Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 2f).sound(SoundType.WOOD)));
	
	public static final RegistryObject<Block> OIL_ICE = BlockInit.BLOCKS.register("oil_ice",
			() -> new ModIceBlock(() -> FluidInit.OIL_BLOCK.get(), Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 2f).slipperiness(1.05f).sound(SoundType.WOOD)));
	
	public static final RegistryObject<Block> NYMPHARIUM_ICE = BlockInit.BLOCKS.register("nympharium_ice",
			() -> new ModIceBlock(() -> FluidInit.NYMPHARIUM_FLUID_BLOCK.get(), Block.Properties.create(Material.ROCK).hardnessAndResistance(1f, 2f).slipperiness(1.05f).sound(SoundType.WOOD)));
	
}
