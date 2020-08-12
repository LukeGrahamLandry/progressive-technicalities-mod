package com.LukeTheDuke9801.progressivetechnicalities.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;

public class ModVillagerTrades {
    public static class SellTrade implements VillagerTrades.ITrade {
        private final ItemStack sold;
        private final int price;
        public SellTrade(ItemStack soldStack, int priceIn){
            this.sold = soldStack;  // the vilager sells this to you
            this.price = priceIn;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.price), this.sold, Integer.MAX_VALUE, 3, 1);
        }
    }

    public static class BuyTrade implements VillagerTrades.ITrade {
        private final ItemStack bought;
        private final int price;
        public BuyTrade(ItemStack boughtStack, int priceIn){
            this.bought = boughtStack;  // the villager buys this from you
            this.price = priceIn;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(this.bought, new ItemStack(Items.EMERALD, this.price), Integer.MAX_VALUE, 3, 1);
        }
    }
}
