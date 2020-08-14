package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.List;

public class UndoStaff extends Item{
	private Location[] locations = new Location[5];  // TODO: use nbt to make stack specific
	private int tick = 0;
	public UndoStaff(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Brings you to your location 5 seconds ago"));
			tooltip.add(new StringTextComponent("Currently bound to " + locations[0].toString()));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	// go to destination
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);

		Location targetLocation = locations[0];
		Location currentLocation = new Location(playerIn);
		if (targetLocation.dim.equals(currentLocation.dim)){
			playerIn.setPosition(targetLocation.pos.getX(), targetLocation.pos.getY(), targetLocation.pos.getZ());
		}

		return ActionResult.resultConsume(stack);
	}

	// set destinations
	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
		Location location = new Location(playerIn);
		for (int i=0;i<5;i++){
			locations[i] = location;
		}
	}

	// update destination
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote){
			this.tick++;
			if (this.tick > 20){
				this.tick = 0;
				locations[0] = locations[1];
				locations[1] = locations[2];
				locations[2] = locations[3];
				locations[3] = locations[4];
				locations[4] = new Location(entityIn);
			}
		}

	}



	static class Location{
		public BlockPos pos;
		public String dim;
		public Location(BlockPos posIn, DimensionType dimensionTypeIn){
			this.pos = posIn;
			this.dim = dimensionTypeIn.getRegistryName().toString();
		}

		public Location(CompoundNBT compound){
			this.pos = new BlockPos(compound.getInt("x"), compound.getInt("y"), compound.getInt("z"));
			this.dim = compound.getString("dim");
		}

		public Location(Entity entity){
			this.pos = entity.getPosition();
			this.dim = entity.dimension.getRegistryName().toString();
		}

		public CompoundNBT toNBT(){
			CompoundNBT compound = new CompoundNBT();
			compound.putInt("x", this.pos.getX());
			compound.putInt("y", this.pos.getY());
			compound.putInt("z", this.pos.getZ());
			compound.putString("dim", this.dim);
			return compound;
		}

		@Override
		public String toString(){
			String x = String.valueOf(this.pos.getX());
			String y = String.valueOf(this.pos.getY());
			String z = String.valueOf(this.pos.getZ());
			return "(" + x + ", " + y + ", " + z + ") in " + this.dim;
		}
	}
}
