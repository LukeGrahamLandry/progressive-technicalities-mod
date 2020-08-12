package com.LukeTheDuke9801.progressivetechnicalities.entities;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.ModVillagerTrades;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class WanderingAstronomer extends AbstractWanderer {
    public WanderingAstronomer(EntityType<? extends WanderingAstronomer> type, World worldIn) {
        super(type, worldIn);
        this.setCustomName(new StringTextComponent("Spaceman Spiff"));

        this.sells = new VillagerTrades.ITrade[]{
                new ModVillagerTrades.SellTrade(new ItemStack(ItemInit.SPACE_HELMET.get(), 1), 32)
        };

        this.buys = new VillagerTrades.ITrade[]{
                new ModVillagerTrades.BuyTrade(new ItemStack(ItemInit.DILITHIUM.get(), 4), 8),
                new ModVillagerTrades.BuyTrade(new ItemStack(ItemInit.MELANGE_CLUMP.get(), 8), 5),
                new ModVillagerTrades.BuyTrade(new ItemStack(ItemInit.UNOBTANIUM_SHARD.get(), 1), 12),
                new ModVillagerTrades.BuyTrade(new ItemStack(BlockInit.LUNA_STONE.get().asItem(), 64), 5),
        };
    }
}
