package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.WanderingAstronomer;
import com.LukeTheDuke9801.progressivetechnicalities.entities.WanderingGemSmith;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			ProgressiveTechnicalities.MOD_ID);
	

	public static final RegistryObject<EntityType<WanderingGemSmith>> WANDERING_GEM_SMITH = ENTITY_TYPES
			.register("wandering_gem_smith",
					() -> EntityType.Builder.<WanderingGemSmith>create(WanderingGemSmith::new, EntityClassification.CREATURE)
							.size(0.6F, 1.95F).build("progressivetechnicalities:wandering_gem_smith"));

	public static final RegistryObject<EntityType<WanderingAstronomer>> WANDERING_ASTRONOMER = ENTITY_TYPES
			.register("wandering_astronomer",
					() -> EntityType.Builder.<WanderingAstronomer>create(WanderingAstronomer::new, EntityClassification.CREATURE)
							.size(0.6F, 1.95F).build("progressivetechnicalities:wandering_astronomer"));
}
