package com.LukeTheDuke9801.progressivetechnicalities.entities.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class MoveToItemGoal extends Goal {
    protected final CreatureEntity creature;
    private final double speed;
    private ItemEntity target;
    private boolean isRunning;

    public MoveToItemGoal(CreatureEntity creatureIn, double speedIn) {
        this.creature = creatureIn;
        this.speed = speedIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(creatureIn.getNavigator() instanceof GroundPathNavigator) && !(creatureIn.getNavigator() instanceof FlyingPathNavigator)) {
            throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
        }
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        ItemEntity t = getClosestItemEntity();
        return t != null;
    }

    @Nullable
    protected ItemEntity getClosestItemEntity(){
        List<Entity> entities = this.creature.world.getEntitiesWithinAABB(ItemEntity.class, getAABB());

        if (entities.size() == 0) return null;

        double d0 = -1.0D;
        ItemEntity t = null;

        for(Entity t1 : entities) {
            if (t1 instanceof ItemEntity) {
                double d1 = t1.getDistanceSq(this.creature.getPosX(), this.creature.getPosY(), this.creature.getPosZ());
                if (d0 == -1.0D || d1 < d0) {
                    d0 = d1;
                    t = (ItemEntity) t1;
                }
            }
        }

        return t;
    }

    private AxisAlignedBB getAABB() {
        int range = 8;
        double x = this.creature.getPosX();
        double y = this.creature.getPosY();
        double z = this.creature.getPosZ();
        return new AxisAlignedBB(x-range,y-range,z-range,x+range,y+range,z+range);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return this.shouldExecute();
    }

    protected boolean isScaredByPlayerMovement() {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.target = getClosestItemEntity();
        this.isRunning = true;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.creature.getNavigator().clearPath();
        this.isRunning = false;
        this.target = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.target == null) return;
        this.creature.getLookController().setLookPosition(this.target.getPosX(), this.target.getPosY(), this.target.getPosZ());
        if (!this.target.isAlive()) {
            this.creature.getNavigator().clearPath();
            this.target = null;
        } else {
            this.creature.getNavigator().tryMoveToXYZ(this.target.getPosX(), this.target.getPosY(), this.target.getPosZ(), this.speed);
        }

    }

    /**
     * @see #isRunning
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}