package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.container.LargeChestContainer;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.enchantment_forge.EnchantmentForgeContainer;
import com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.tinker_table.TinkerTableContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, ProgressiveTechnicalities.MOD_ID);
	
	public static final RegistryObject<ContainerType<LargeChestContainer>> LARGE_CHEST = CONTAINER_TYPES.register("large_chest",
			() -> IForgeContainerType.create(LargeChestContainer::new));
	
	public static final RegistryObject<ContainerType<TinkerTableContainer>> TINKER_TABLE = CONTAINER_TYPES.register("tinker_table",
			() -> IForgeContainerType.create(TinkerTableContainer::new));
	
	public static final RegistryObject<ContainerType<EnchantmentForgeContainer>> ENCHANTMENT_FORGE = CONTAINER_TYPES.register("enchantment_forge",
			() -> IForgeContainerType.create(EnchantmentForgeContainer::new));
	
}
