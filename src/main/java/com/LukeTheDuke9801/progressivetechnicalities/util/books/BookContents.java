package com.LukeTheDuke9801.progressivetechnicalities.util.books;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponentUtils;

import java.util.List;

public interface BookContents {
    default String getAuthor(){
        return "Luke Graham Landry";
    }

    String getTitle();

    String getContents();

    default void addPage(String content, ListNBT pages){
        INBT page = (INBT) StringNBT.valueOf("{\"text\": \"" + content + "\"}");
        // INBT page = (INBT) StringNBT.valueOf(ITextComponent.Serializer.toJson(itextcomponent));
        pages.add(page);
    }

    default ListNBT getPages(){
        ListNBT pages = new ListNBT();

        String contents = getContents();
        int size = 256;
        for (int start = 0; start < contents.length(); start += size) {
            String pageText = contents.substring(start, Math.min(contents.length(), start + size));
            addPage(pageText, pages);
        }

        return pages;
    }
}
