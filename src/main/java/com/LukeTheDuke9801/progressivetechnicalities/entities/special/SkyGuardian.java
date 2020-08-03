package com.LukeTheDuke9801.progressivetechnicalities.entities.special;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class SkyGuardian implements SpawnableSpecialMob{
    public void create(World world, BlockPos pos) {
        SkeletonEntity skeleton = (SkeletonEntity) EntityType.SKELETON.create(world);
        skeleton.setPosition(pos.getX(), pos.getY(), pos.getZ());
        skeleton.enablePersistence();  // don't despawn
        ItemStack bowStack = new ItemStack(Items.BOW);
        bowStack.addEnchantment(Enchantments.PUNCH, 2);
        skeleton.setHeldItem(Hand.MAIN_HAND, bowStack);
        ItemStack helmetStack = new ItemStack(ItemInit.SKY_GUARDIAN_HELM.get());  // knock back resistance, no fall damage, no burn in day
        skeleton.setItemStackToSlot(EquipmentSlotType.HEAD, helmetStack);
        skeleton.setCustomName(new StringTextComponent("Sky Guardian"));
        world.addEntity(skeleton);
    }
}
