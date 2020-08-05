package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.world.structures.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Locale;

import static com.LukeTheDuke9801.progressivetechnicalities.world.gen.OreGen.isVanillaOverworldBiome;

public class FeatureInit {
    // a structure cannot be closer than its min chunks or farther apart than its max chunks
    public static StructureDistance defaultDistance = new StructureDistance(16, 32);

    public static Structure<NoFeatureConfig> WANDERER_VILLAGE = new WandererVillageStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType WANDERER_VILLAGE_PIECE = WandererVillagePieces.Piece::new;
    public static StructureDistance wandererVillageDistance = defaultDistance;

    public static Structure<NoFeatureConfig> NETHER_DUNGEON = new NetherDungeonStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType NETHER_DUNGEON_PIECE = NetherDungeonPieces.Piece::new;
    public static StructureDistance netherDungeonDistance = defaultDistance;

    public static Structure<NoFeatureConfig> FEY_HENGE = new FeyHengeStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType FEY_HENGE_PIECE = FeyHengePieces.Piece::new;
    public static StructureDistance feyHengeDistance = defaultDistance;

    public static Structure<NoFeatureConfig> SKY_ISLAND = new SkyIslandStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType SKY_ISLAND_PIECE = SkyIslandPieces.Piece::new;
    public static StructureDistance skyIslandDistance = defaultDistance;

    public static Structure<NoFeatureConfig> RITUAL = new RitualStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType RITUAL_PIECE = RitualPieces.Piece::new;
    public static StructureDistance ritualDistance = defaultDistance;

    public static Structure<NoFeatureConfig> METEOR = new MeteorStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType METEOR_PIECE = MeteorPieces.Piece::new;
    public static StructureDistance meteorDistance = defaultDistance;


    /*
     * Registers the features and structures. Normal Features will be registered here too.
     */
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        WANDERER_VILLAGE.setRegistryName(new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "wanderer_village"));
        registry.register(WANDERER_VILLAGE);
        Registry.register(Registry.STRUCTURE_PIECE, "wanderer_village_piece", WANDERER_VILLAGE_PIECE);

        NETHER_DUNGEON.setRegistryName(new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "nether_dungeon"));
        registry.register(NETHER_DUNGEON);
        Registry.register(Registry.STRUCTURE_PIECE, "nether_dungeon_piece", NETHER_DUNGEON_PIECE);

        FEY_HENGE.setRegistryName(new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "fey_henge"));
        registry.register(FEY_HENGE);
        Registry.register(Registry.STRUCTURE_PIECE, "fey_henge_piece", FEY_HENGE_PIECE);

        SKY_ISLAND.setRegistryName(new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "sky_island"));
        registry.register(SKY_ISLAND);
        Registry.register(Registry.STRUCTURE_PIECE, "sky_island_piece", SKY_ISLAND_PIECE);

        RITUAL.setRegistryName(new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "ritual"));
        registry.register(RITUAL);
        Registry.register(Registry.STRUCTURE_PIECE, "ritual_piece", RITUAL_PIECE);

        METEOR.setRegistryName(new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "meteor"));
        registry.register(METEOR);
        Registry.register(Registry.STRUCTURE_PIECE, "meteor_piece", METEOR_PIECE);
    }

    public static void addStructuresToVanillaBiomes(){
        for (Biome biome : ForgeRegistries.BIOMES){
            if (isVanillaOverworldBiome(biome)){
                generate(biome, WANDERER_VILLAGE);
                generate(biome, SKY_ISLAND);
            }

            if (biome.getCategory() == Biome.Category.NETHER){
                generate(biome, NETHER_DUNGEON);
            }
        }
    }

    public static void generate(Biome biome, Structure<NoFeatureConfig> structure){
        biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
                .withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
    }


    /*
     * Registers the structures pieces themselves. If you don't do this part, Forge will complain to you in the Console.
     */
    static IStructurePieceType register(IStructurePieceType structurePiece, String key) {
        return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), structurePiece);
    }

    public static class StructureDistance {
        public int min, max;

        StructureDistance(int minIn, int maxIn) {
            this.min = minIn;
            this.max = maxIn;
        }

        StructureDistance add(int add){
            return new StructureDistance(this.min + add, this.max + add);
        }

        StructureDistance add(int minAdd, int maxAdd){
            return new StructureDistance(this.min + minAdd, this.max + maxAdd);
        }
    }
}
