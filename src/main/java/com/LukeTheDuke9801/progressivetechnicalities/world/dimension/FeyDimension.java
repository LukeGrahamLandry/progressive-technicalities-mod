package com.LukeTheDuke9801.progressivetechnicalities.world.dimension;

import com.LukeTheDuke9801.progressivetechnicalities.init.BiomeInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.BlockInit;
import com.LukeTheDuke9801.progressivetechnicalities.init.FluidInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class FeyDimension extends Dimension{
		public FeyDimension(World world, DimensionType type) {
			super(world, type, 0.1f);
		}

		@Override
		public ChunkGenerator<?> createChunkGenerator() {
			OverworldGenSettings settings = new OverworldGenSettings();
			settings.setDefaultFluid(FluidInit.SILVER_FLUID_BLOCK.get().getDefaultState());
			settings.setDefaultBlock(BlockInit.FEY_STONE.get().getDefaultState());
			SingleBiomeProviderSettings biomeProviderSettings = new SingleBiomeProviderSettings(world.getWorldInfo());
		    biomeProviderSettings.setBiome(BiomeInit.FEY_PLAINS.get());
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
			int j = 6000;
			float f1 = (j + partialTicks) / 24000.0f - 0.25f;
			if (f1 < 0.0f) {
				f1 += 1.0f;
			}

			if (f1 > 1.0f) {
				f1 -= 1.0f;
			}

			float f2 = f1;
			f1 = 1.0f - (float) ((Math.cos(f1 * Math.PI) + 1.0d) / 2.0d);
			f1 = f2 + (f1 - f2) / 3.0f;
			return f1;
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
		public double getMovementFactor() {
			return 100;
		}
		
	}
