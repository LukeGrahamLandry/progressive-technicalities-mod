package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines.rocket;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.ModTileEntityTypes;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.world.Explosion;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

public class RocketTileEntity extends TileEntity implements ITickableTileEntity{
	public boolean active = true;
	int tick = 0;
	public RocketTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public RocketTileEntity() {
		this(ModTileEntityTypes.ROCKET.get());
	}
	
	@Override
	public void tick() {
		if (!this.active) {
			return;
		} 
		
		tick++;
		if (tick == 10) {
			tick = 0;
			execute();
		}
	}
	
	private void execute(){
		if (world.isRemote) return;
		
		if (!world.getBlockState(pos.up()).isAir(null, null)) {
			world.createExplosion((Entity)null, pos.getX(), pos.getY(), pos.getZ(), 10f, true, Explosion.Mode.DESTROY);
			this.remove();
			return;
		} 
		
		PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ());
		
		if (pos.getY() > 250) {
			DimensionType destination = DimensionType.byName(ProgressiveTechnicalities.ARRAKIS_DIM_TYPE);
			MinecraftServer minecraftserver = player.getServer();
			ServerWorld serverworld = minecraftserver.getWorld(destination);

			((ServerPlayerEntity)player).teleport(serverworld, player.getPosX(), 150, player.getPosZ(), player.getYaw(0), player.getPitch(0));
			
			player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 500, 0));
			
			player.setHeldItem(Hand.MAIN_HAND, new ItemStack(BlockInit.ROCKET.get().asItem()));
			
		} else {
			player.teleportKeepLoaded(player.getPosX(), player.getPosY() + 1, player.getPosZ());
			world.setBlockState(pos.up(), BlockInit.ROCKET.get().getDefaultState());
			
			// ((RocketTileEntity)world.getTileEntity(pos.up())).active = true;
		}
		
		world.setBlockState(pos, Blocks.AIR.getDefaultState());
		this.remove();
	}
}
