package com.LukeTheDuke9801.progressivetechnicalities.util.books;

import jdk.nashorn.internal.runtime.JSONFunctions;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;

public class BasicHelp implements BookContents{
    public String getTitle(){
        return "Prog-Tech Help";
    }

    @Override
    public String getContents() {
        return "";
    }

    public ListNBT getPages(){
        ListNBT pages = new ListNBT();

        // make these with https://minecraftjson.com/
        String linksPage = "[\"\",{\"text\":\"Online Documentation \n\n\",\"underlined\":true,\"color\":\"dark_blue\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://github.com/LukeGrahamLandry/progressive_technicalities/blob/master/Documentation/overview.md\"}},{\"text\":\"Contact Developer on Discord \n\n\",\"underlined\":true,\"color\":\"dark_blue\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://discord.gg/VbZVnRd\"}},{\"text\":\"Most items have a description that will be displayed if you hover over them and hold shift. Use JEI (another mod) to see recipes\"}]";
        INBT page = (INBT) StringNBT.valueOf(linksPage);
        pages.add(page);

        addPage("The first dimension this mod adds is the Oil Dimension. Craft a portal key (with netherrack, " +
                "end stone and chromium) and upgrade it to an Oil Portal Key with an obsidian. Right click " +
                "the middle of a plus sign of obsidian and jump on the central block.", pages);

        return pages;
    }
}
