package com.LukeTheDuke9801.progressivetechnicalities.entities;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import com.LukeTheDuke9801.progressivetechnicalities.util.ModVillagerTrades;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class WanderingAstronomer extends AbstractWanderer {
    public WanderingAstronomer(EntityType<? extends WanderingAstronomer> type, World worldIn) {
        super(type, worldIn);
        this.setCustomName(new StringTextComponent("Spaceman Spiff"));

        this.sells = new VillagerTrades.ITrade[]{
                // item, num emeralds to input, num items to output, max uses, priceMultiplierIn
                new ModVillagerTrades.ItemsForEmeraldsTrade(ItemInit.SPACE_HELMET.get(), 24, 1, Integer.MAX_VALUE, 1)
        };

        this.buys = new VillagerTrades.ITrade[]{
                // item, num items to input, num emeralds to output, max uses, ex given
                new ModVillagerTrades.EmeraldsForItemsTrade(ItemInit.DILITHIUM.get(), 8, 2, Integer.MAX_VALUE, 5),
                new ModVillagerTrades.EmeraldsForItemsTrade(ItemInit.MELANGE_CLUMP.get(), 8, 2, Integer.MAX_VALUE, 5),
                new ModVillagerTrades.EmeraldsForItemsTrade(ItemInit.UNOBTANIUM_SHARD.get(), 1, 16, Integer.MAX_VALUE, 5),
                new ModVillagerTrades.EmeraldsForItemsTrade(BlockInit.LUNA_STONE.get().asItem(), 64, 1, Integer.MAX_VALUE, 5)
        };
    }
}
