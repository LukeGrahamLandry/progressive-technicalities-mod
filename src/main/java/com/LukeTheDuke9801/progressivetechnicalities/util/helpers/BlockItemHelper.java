package com.LukeTheDuke9801.progressivetechnicalities.util.helpers;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class BlockItemHelper {
    public static void registerIfNeeded(Block block, IForgeRegistry<Item> registry){
        if (!shouldRegister(block)) return;

        final Item.Properties properties = new Item.Properties().group(ProgressiveTechnicalities.ProgtechItemGroup.instance);
        BlockItem blockItem;
        if (block instanceof DescribableBlock){
            blockItem = new DescribableBlockItem(block, properties);
        } else {
            blockItem = new BlockItem(block, properties);
        }
        blockItem.setRegistryName(block.getRegistryName());
        registry.register(blockItem);
    }

    private static boolean shouldRegister(Block block){
        return block instanceof FlowingFluidBlock;
    }


    static class DescribableBlockItem extends BlockItem{
        private String description;
        public DescribableBlockItem(Block blockIn, Properties builder) {
            super(blockIn, builder);
            this.description = ((DescribableBlock)blockIn).getBlockDescription();
        }

        @Override
        public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            if (KeyboardHelper.isHoldingShift()) {
                tooltip.add(new StringTextComponent(this.description));
            }

            super.addInformation(stack, worldIn, tooltip, flagIn);
        }
    }


}
