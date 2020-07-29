package com.LukeTheDuke9801.progressivetechnicalities.init;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.world.structures.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Locale;

public class FeatureInit {
    // a structure cannot be closer than its min chunks or farther apart than its max chunks
    public static structureDistance defaultDistance = new structureDistance(16, 48);

    public static Structure<NoFeatureConfig> WANDERER_VILLAGE = new WandererVillageStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType WANDERER_VILLAGE_PIECE = WandererVillagePieces.Piece::new;
    public static structureDistance wandererVillageDistance = defaultDistance;

    public static Structure<NoFeatureConfig> NETHER_DUNGEON = new NetherDungeonStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType NETHER_DUNGEON_PIECE = NetherDungeonPieces.Piece::new;
    public static structureDistance netherDungeonDistance = defaultDistance;

    public static Structure<NoFeatureConfig> FEY_HENGE = new FeyHengeStructure(NoFeatureConfig::deserialize);
    public static IStructurePieceType FEY_HENGE_PIECE = FeyHengePieces.Piece::new;
    public static structureDistance feyHengeDistance = defaultDistance;


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
    }


    /*
     * Registers the structures pieces themselves. If you don't do this part, Forge will complain to you in the Console.
     */
    static IStructurePieceType register(IStructurePieceType structurePiece, String key) {
        return Registry.register(Registry.STRUCTURE_PIECE, key.toLowerCase(Locale.ROOT), structurePiece);
    }

    public static class structureDistance {
        public int min, max;

        structureDistance(int minIn, int maxIn) {
            this.min = minIn;
            this.max = maxIn;
        }
    }
}
