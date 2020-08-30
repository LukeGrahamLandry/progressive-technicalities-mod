package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.*;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Random;

import static net.minecraft.entity.MobEntity.canSpawnOn;

@Mod.EventBusSubscriber(modid = ProgressiveTechnicalities.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

	/*
	public static final RegistryObject<EntityType<FairyEntity>> FAIRY = ENTITY_TYPES
			.register("fairy",
					() -> EntityType.Builder.<FairyEntity>create(FairyEntity::new, EntityClassification.CREATURE)
							.size(0.6F, 1.95F).build("progressivetechnicalities:fairy"));

	 */

	@ObjectHolder(ProgressiveTechnicalities.MOD_ID + ":fairy")
	public static final EntityType<FairyEntity> FAIRY = EntityType.Builder.create(FairyEntity::new, EntityClassification.MONSTER)
			.immuneToFire().size(0.4F, 0.8F)
			.build(ProgressiveTechnicalities.MOD_ID + ":fairy");

	@ObjectHolder(ProgressiveTechnicalities.MOD_ID + ":fey_fox")
	public static final EntityType<FeyFoxEntity> FEY_FOX = EntityType.Builder.create(FeyFoxEntity::new, EntityClassification.CREATURE)
			.immuneToFire().size(0.6F, 0.7F)
			.build(ProgressiveTechnicalities.MOD_ID + ":fey_fox");

	@ObjectHolder(ProgressiveTechnicalities.MOD_ID + ":freman")
	public static final EntityType<FremanEntity> FREMAN = EntityType.Builder.create(FremanEntity::new, EntityClassification.CREATURE)
			.immuneToFire().size(0.6F, 1.95F)
			.build(ProgressiveTechnicalities.MOD_ID + ":freman");

	@ObjectHolder(ProgressiveTechnicalities.MOD_ID + ":fey_cow")
	public static final EntityType<FeyCowEntity> FEY_COW = EntityType.Builder.create(FeyCowEntity::new, EntityClassification.CREATURE)
			.immuneToFire().size(0.9F, 1.4F)
			.build(ProgressiveTechnicalities.MOD_ID + ":fey_cow");

	@ObjectHolder(ProgressiveTechnicalities.MOD_ID + ":wizard")
	public static final EntityType<WizardEntity> WIZARD = EntityType.Builder.create(WizardEntity::new, EntityClassification.MONSTER)
			.immuneToFire().size(0.6F, 1.95F)
			.build(ProgressiveTechnicalities.MOD_ID + ":wizard");

	@SubscribeEvent
	public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
		FAIRY.setRegistryName(ProgressiveTechnicalities.MOD_ID, "fairy");
		event.getRegistry().register(FAIRY);
		EntitySpawnPlacementRegistry.register(FAIRY, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModEntityTypes::canFairySpawn);

		FEY_FOX.setRegistryName(ProgressiveTechnicalities.MOD_ID, "fey_fox");
		event.getRegistry().register(FEY_FOX);
		EntitySpawnPlacementRegistry.register(FEY_FOX, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModEntityTypes::canFoxSpawn);

		FREMAN.setRegistryName(ProgressiveTechnicalities.MOD_ID, "freman");
		event.getRegistry().register(FREMAN);
		EntitySpawnPlacementRegistry.register(FREMAN, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModEntityTypes::canFremanSpawn);

		FEY_COW.setRegistryName(ProgressiveTechnicalities.MOD_ID, "fey_cow");
		event.getRegistry().register(FEY_COW);
		EntitySpawnPlacementRegistry.register(FEY_COW, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModEntityTypes::canFoxSpawn);

		WIZARD.setRegistryName(ProgressiveTechnicalities.MOD_ID, "wizard");
		event.getRegistry().register(WIZARD);
		EntitySpawnPlacementRegistry.register(WIZARD, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModEntityTypes::canWizardSpawn);
	}


	public static boolean canFairySpawn(EntityType<? extends MonsterEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
		return worldIn.getDifficulty() != Difficulty.PEACEFUL;
	}

	public static boolean canFoxSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		return random.nextInt(10) == 0 && worldIn.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK && worldIn.getLightSubtracted(pos, 0) > 8;
	}

	public static boolean canFremanSpawn(EntityType<? extends FremanEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		return random.nextInt(20) == 0 && worldIn.getBlockState(pos.down()).getBlock() == Blocks.SAND;
	}

	public static boolean canWizardSpawn(EntityType<? extends WizardEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		return random.nextInt(20) == 0 && worldIn.getBlockState(pos.down()).getBlock() == Blocks.OBSIDIAN;
	}
}
