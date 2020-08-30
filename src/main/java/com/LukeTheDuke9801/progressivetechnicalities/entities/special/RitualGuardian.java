package com.LukeTheDuke9801.progressivetechnicalities.entities.special;

import com.LukeTheDuke9801.progressivetechnicalities.init.ItemInit;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class RitualGuardian implements SpawnableSpecialMob{
    public void create(World world, BlockPos pos) {
        ZombieEntity zombie = (ZombieEntity) EntityType.ZOMBIE.create(world);
        zombie.setPosition(pos.getX(), pos.getY(), pos.getZ());
        zombie.enablePersistence();  // don't despawn
        ItemStack swordStack = new ItemStack(Items.DIAMOND_SWORD);
        swordStack.addEnchantment(Enchantments.FIRE_ASPECT, 2);
        swordStack.addEnchantment(Enchantments.KNOCKBACK, 2);
        zombie.setHeldItem(Hand.MAIN_HAND, swordStack);
        zombie.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ItemInit.EARTHGEM_ARMOR_SET.HELMET.get()));
        zombie.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ItemInit.EARTHGEM_ARMOR_SET.CHESTPLATE.get()));
        zombie.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(ItemInit.EARTHGEM_ARMOR_SET.LEGGINGS.get()));
        zombie.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(ItemInit.EARTHGEM_ARMOR_SET.BOOTS.get()));
        zombie.setCustomName(new StringTextComponent("Ritual Guardian"));
        world.addEntity(zombie);
    }
}
