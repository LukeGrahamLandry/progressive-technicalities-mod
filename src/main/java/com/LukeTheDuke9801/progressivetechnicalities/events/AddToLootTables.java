package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;

public class AddToLootTables  {
	public static void lootLoad(LootTableLoadEvent evt) {
		String prefix = "minecraft:chests/";
		String name = evt.getName().toString();

		if (name.startsWith(prefix)) {
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			
			if (file == "underwater_ruin_small" || file == "underwater_ruin_big" || file == "buried_treasure" || file == "shipwreck_treasure") {
				file = "ocean_chest";
			}
			
			switch (file) {
			case "abandoned_mineshaft":
			case "ocean_chest": 
			case "nether_bridge":
			case "end_city_treasure":
			case "simple_dungeon":
			case "stronghold_corridor":
				evt.getTable().addPool(getInjectPool(file));
				break;
			default:
				break;
			}
		}
	}

	public static LootPool getInjectPool(String entryName) {
		return LootPool.builder()
				.addEntry(getInjectEntry(entryName, 1))
				.bonusRolls(0, 1)
				.name("botania_inject")
				.build();
	}

	private static LootEntry.Builder<?> getInjectEntry(String name, int weight) {
		ResourceLocation table = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "inject/" + name);
		return TableLootEntry.builder(table)
				.weight(weight);
	}

}