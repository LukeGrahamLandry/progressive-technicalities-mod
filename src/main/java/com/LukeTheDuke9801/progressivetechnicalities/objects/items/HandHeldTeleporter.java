package com.LukeTheDuke9801.progressivetechnicalities.objects.items;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.entities.ThrowableFireballEntity;
import com.LukeTheDuke9801.progressivetechnicalities.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.List;

public class HandHeldTeleporter extends Item{
	public HandHeldTeleporter(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Shift right click to save location, hold right click to return to saved location. "));
			tooltip.add(new StringTextComponent("Currently bound to " + getLocation(stack).toString()));
		} 
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	// set destination
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (KeyboardHelper.isHoldingShift()) {
			Location location = new Location(playerIn);
			setLocation(stack, location);
		} else {
			playerIn.setActiveHand(handIn);  // required to make the useFinished / stoppedUsing fire
		}

		return ActionResult.resultConsume(stack);
	}

	// teleport to destination
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		Location targetLocation = getLocation(stack);
		Location currentLocation = new Location(entityLiving);
		if (targetLocation.dim.equals(currentLocation.dim)){
			entityLiving.setPosition(targetLocation.pos.getX(), targetLocation.pos.getY(), targetLocation.pos.getZ());
		}

		return stack;
	}

	// set destination
	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
		Location location = new Location(playerIn);
		setLocation(stack, location);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	private Location getLocation(ItemStack stack) {
		CompoundNBT compound = stack.getTag();

		if (compound == null || !compound.contains("savedLocation")) return new Location(new BlockPos(0, 70, 0), DimensionType.OVERWORLD);

		return new Location(compound.getCompound("savedLocation"));
	}

	private void setLocation(ItemStack stack, Location location) {
		CompoundNBT compound = stack.getTag();
		if (compound == null) compound = new CompoundNBT();

		compound.put("savedLocation", location.toNBT());
		stack.setTag(compound);
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
