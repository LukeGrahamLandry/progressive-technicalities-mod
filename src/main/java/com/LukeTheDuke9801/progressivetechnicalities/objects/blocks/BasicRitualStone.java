package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks;

import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.RitualCatalyst;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class BasicRitualStone extends Block {

    public BasicRitualStone(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Item item = player.getHeldItem(handIn).getItem();
        boolean isValidCatalyst = item instanceof RitualCatalyst && ((RitualCatalyst)item).ritualLevel() == 0;
        if (validRitualSetup(worldIn, pos) && isValidCatalyst){
            player.getHeldItem(handIn).shrink(1);

            doRitual(worldIn, pos);
            ((RitualCatalyst)item).doRitual(worldIn, pos, player);

            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.FAIL;
        }
    }

    private boolean validRitualSetup(World world, BlockPos pos) {
        return isRuneStone(world, pos.north(3)) && isRuneStone(world, pos.south(3)) &&
                isRuneStone(world, pos.east(3)) && isRuneStone(world, pos.west(3)) &&
                world.getBlockState(pos.down()).getBlock().equals(Blocks.DIAMOND_BLOCK) &&
                findClosestMob(world, pos) != null;
    }

    private boolean isRuneStone(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock().equals(BlockInit.RUNE_STONE.get())
                && world.getBlockState(pos.down()).getBlock().equals(Blocks.GOLD_BLOCK);
    }

    private void doRitual(World world, BlockPos pos) {
        doLightning(world, pos.north(3));
        doLightning(world, pos.south(3));
        doLightning(world, pos.east(3));
        doLightning(world, pos.west(3));

        MobEntity mob = findClosestMob(world, pos);
        mob.onKillCommand();
    }

    private void doLightning(World world, BlockPos pos) {
        if (!world.isRemote()) {
            ServerWorld serverWorld = (ServerWorld)world;
            LightningBoltEntity lightning = new LightningBoltEntity(world, pos.getX(), pos.getY(), pos.getZ(), false);
            serverWorld.addLightningBolt(lightning);
        }
    }

    private MobEntity findClosestMob(World world, BlockPos pos){
        int range = 4;
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        AxisAlignedBB aabb = new AxisAlignedBB(x-range,y-range,z-range,x+range,y+range,z+range);

        List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(null, aabb);
        NonNullList<MobEntity> targets = NonNullList.create();
        for (Entity e : entities) {
            if (e instanceof MobEntity) {
                targets.add((MobEntity) e);

            }
        }

        if (targets.size() > 0) {
            return world.getClosestEntity(targets, EntityPredicate.DEFAULT.setDistance(range), null, x, y, z);
        } else {
            return null;
        }
    }

}
	   
	
