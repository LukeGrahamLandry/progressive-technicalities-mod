package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.util.books.BookContents;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class PreWrittenBook extends Item {
    private final BookContents bookContents;
    public PreWrittenBook(BookContents bookContentsIn, Item.Properties builder) {
        super(builder);
        this.bookContents = bookContentsIn;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (KeyboardHelper.isHoldingShift()) {
            tooltip.add(new StringTextComponent("Right click to transform into a readable book"));
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = new ItemStack(Items.WRITTEN_BOOK);
        this.setContents(stack);
        playerIn.setHeldItem(handIn, stack);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void setContents(ItemStack stack){
        CompoundNBT tag = stack.getTag();
        if (tag == null) tag = new CompoundNBT();

        tag.putString("author", this.bookContents.getAuthor());
        tag.putString("title", this.bookContents.getTitle());
        tag.putBoolean("resolved", true);

        ListNBT pages = this.bookContents.getPages();
        tag.put("pages", pages);

        stack.setTag(tag);
    }
}
