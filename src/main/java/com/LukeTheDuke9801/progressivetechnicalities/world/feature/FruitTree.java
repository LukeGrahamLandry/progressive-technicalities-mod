package com.LukeTheDuke9801.progressivetechnicalities.world.feature;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.treedecorator.CocoaTreeDecorator;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class FruitTree extends Tree {
    public static final TreeFeatureConfig CHERRY_TREE_CONFIG = getTreeConfigBuilder(BlockInit.CHERRY_LEAVES.get(), BlockInit.CHERRY_SAPLING.get()).build();

    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean somethingAboutHivesMaybe) {
        return Feature.NORMAL_TREE.withConfiguration(CHERRY_TREE_CONFIG);
    }

    private static TreeFeatureConfig.Builder getTreeConfigBuilder(Block leaves, Block sappling){
        return (new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockInit.FEY_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(leaves.getDefaultState()),
                new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines()
                .setSapling((IPlantable) sappling);

        // .decorators(ImmutableList.of(new CocoaTreeDecorator(0.2F)))  // for coffee beans
    }
}
