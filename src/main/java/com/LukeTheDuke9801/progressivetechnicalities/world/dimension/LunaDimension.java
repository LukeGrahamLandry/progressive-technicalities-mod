package com.LukeTheDuke9801.progressivetechnicalities.world.dimension;

import com.LukeTheDuke9801.progressivetechnicalities.init.BiomeInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class LunaDimension extends Dimension{
	public LunaDimension(World world, DimensionType type) {
		super(world, type, 0.0f);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		OverworldGenSettings settings = new OverworldGenSettings();
		settings.setDefaultBlock(BlockInit.LUNA_STONE.get().getDefaultState());
		settings.setDefaultFluid(Blocks.AIR.getDefaultState());
		SingleBiomeProviderSettings biomeProviderSettings = new SingleBiomeProviderSettings(world.getWorldInfo());
	    biomeProviderSettings.setBiome(BiomeInit.LUNA_PLAINS.get());
		return new OverworldChunkGenerator(world, new SingleBiomeProvider(biomeProviderSettings), settings);
	}

	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
		return null;
	}

	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
		return null;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		 double d0 = MathHelper.frac((double)worldTime / 24000.0D - 0.25D);
	     double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
	     return (float)(d0 * 2.0D + d1) / 3.0F;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		return Vec3d.ZERO;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}

	@Override
	public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
		return SleepResult.BED_EXPLODES;
	}

	@Override
	public boolean hasSkyLight() {
		return true;
	}

	@Override
	public int getActualHeight() {
		return 256;
	}
	
	@Override
	public boolean doesWaterVaporize() {
		return true;
	}
}
