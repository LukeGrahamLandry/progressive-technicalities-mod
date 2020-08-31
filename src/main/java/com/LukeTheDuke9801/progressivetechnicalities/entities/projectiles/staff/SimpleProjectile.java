package com.LukeTheDuke9801.progressivetechnicalities.entities.projectiles.staff;

import com.LukeTheDuke9801.progressivetechnicalities.entities.WizardEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SimpleProjectile extends ProjectileItemEntity {
    private ProjectileHitAction hitAction;

    public int force;

    public SimpleProjectile(World worldIn, LivingEntity throwerIn, ProjectileHitAction hitActionIn, int forceIn) {
        super(EntityType.SNOWBALL, throwerIn, worldIn);
        this.hitAction = hitActionIn;
        this.force = forceIn; // increases damage, explosion size, etc.
    }

    // logic from GhastEntity
    public void shootTowardEntity(LivingEntity target){
        LivingEntity shooter = this.getThrower();

        Vec3d vec3d = shooter.getLook(1.0F);
        double xAccel = target.getPosX() - (shooter.getPosX() + vec3d.x * 4.0D);
        double yAccel = target.getPosYHeight(0.5D) - (0.5D + shooter.getPosYHeight(0.5D));
        double zAccel = target.getPosZ() - (shooter.getPosZ() + vec3d.z * 4.0D);

        this.setLocationAndAngles(shooter.getPosX(), shooter.getPosY(), shooter.getPosZ(), shooter.rotationYaw, shooter.rotationPitch);
        this.recenterBoundingBox();
        this.setMotion(Vec3d.ZERO);
        xAccel = xAccel + this.rand.nextGaussian() * 0.4D;
        yAccel = yAccel + this.rand.nextGaussian() * 0.4D;
        zAccel = zAccel + this.rand.nextGaussian() * 0.4D;
        double d0 = (double) MathHelper.sqrt(xAccel * xAccel + yAccel * yAccel + zAccel * zAccel);
        this.shoot( xAccel / d0 * 0.1D, yAccel / d0 * 0.1D,zAccel / d0 * 0.1D, 1.5F, 0.5F);

        // so it doesnt hit the person shooting it
        this.setPosition(shooter.getPosX() + vec3d.x, shooter.getPosYHeight(0.5D) + 0.5D, this.getPosZ() + vec3d.z);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            this.hitAction.onHit(result, this);
            this.remove();
        }

    }

    // BORING
    protected Item getDefaultItem() {
        return Items.FIRE_CHARGE;
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData makeParticle() {
        ItemStack itemstack = this.func_213882_k();
        return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.makeParticle();

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }
}