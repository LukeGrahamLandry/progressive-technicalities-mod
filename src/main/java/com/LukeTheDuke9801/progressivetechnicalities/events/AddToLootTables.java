package com.LukeTheDuke9801.progressivetechnicalities.events;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AddToLootTables  {
	public static void lootLoad(LootTableLoadEvent evt) {
		String prefix = "minecraft:chests/";
		String name = evt.getName().toString();

		if (name.startsWith(prefix)) {
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			
			if (file == "underwater_ruin_small" || file == "underwater_ruin_big" || file == "buried_treasure" || file == "shipwreck_treasure") {
				file = "ocean_chest";
			}

			Set<String> fileNamesToInjectTo = new HashSet<String>();
			fileNamesToInjectTo.add("abandoned_mineshaft");
			fileNamesToInjectTo.add("ocean_chest");
			fileNamesToInjectTo.add("nether_bridge");
			fileNamesToInjectTo.add("simple_dungeon");
			fileNamesToInjectTo.add("end_city_treasure");
			fileNamesToInjectTo.add("stronghold_corridor");
			fileNamesToInjectTo.add("stronghold_library");

			if (fileNamesToInjectTo.contains(file)){
				evt.getTable().addPool(getInjectPool(file));
			}
		}
	}

	public static LootPool getInjectPool(String entryName) {
		return LootPool.builder()
				.addEntry(getInjectEntry(entryName, 1))
				.bonusRolls(0, 1)
				.name("progtech_inject")
				.build();
	}

	private static LootEntry.Builder<?> getInjectEntry(String name, int weight) {
		ResourceLocation table = new ResourceLocation(ProgressiveTechnicalities.MOD_ID, "inject/" + name);
		return TableLootEntry.builder(table)
				.weight(weight);
	}

}