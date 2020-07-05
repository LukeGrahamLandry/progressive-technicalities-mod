package com.LukeTheDuke9801.progressivetechnicalities.objects.blocks.machines;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.LukeTheDuke9801.progressivetechnicalities.ProgressiveTechnicalities;
import com.LukeTheDuke9801.progressivetechnicalities.objects.items.Battery;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public abstract class AbstractMachineTileEntity extends LockableTileEntity
		implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {
	private static final int[] SLOTS_UP = new int[] { 0 };
	private static final int[] SLOTS_DOWN = new int[] { 2, 1 };
	private static final int[] SLOTS_HORIZONTAL = new int[] { 1 };
	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
	private int cookTime;
	private int recipeCookTime;
	private boolean isBurning = false;
	protected final IRecipeType<? extends AbstractCookingRecipe> recipeType;

	private final Map<ResourceLocation, Integer> field_214022_n = Maps.newHashMap(); // ???
	
	private int energy;
	private final int energyPerTick = 10;

	protected AbstractMachineTileEntity(TileEntityType<?> tileTypeIn,
			IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(tileTypeIn);
		this.recipeType = recipeTypeIn;
		this.energy = 0;
	}

	protected final IIntArray furnaceData = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return AbstractMachineTileEntity.this.cookTime;
			case 1:
				return AbstractMachineTileEntity.this.recipeCookTime;
			case 2:
				return AbstractMachineTileEntity.this.energy;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				AbstractMachineTileEntity.this.cookTime = value;
				break;
			case 1:
				AbstractMachineTileEntity.this.recipeCookTime = value;
			case 2:
				AbstractMachineTileEntity.this.energy = value;
			}

		}

		public int size() {
			return 3;
		}
	};

	public void read(CompoundNBT compound) {
		super.read(compound);
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.items);
		this.isBurning = compound.getBoolean("IsBurning");
		this.cookTime = compound.getInt("CookTime");
		this.recipeCookTime = compound.getInt("RecipeCookTime");
		this.energy = compound.getInt("Energy");
	}

	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);

		compound.putBoolean("IsBurning", this.isBurning);
		compound.putInt("CookTime", this.cookTime);
		compound.putInt("RecipeCookTime", this.recipeCookTime);
		compound.putInt("Energy", this.energy);
		ItemStackHelper.saveAllItems(compound, this.items);

		return compound;
	}

	public void tick() {
		if (!this.world.isRemote) {
			ItemStack inputItemstack = getStackInNamedSlot("input");
			if (!inputItemstack.isEmpty()) {
				IRecipe<?> irecipe = this.world.getRecipeManager()
						.getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world).orElse(null);

				if (this.energy > this.energyPerTick && this.canSmelt(irecipe)) {
					this.cookTime++;
					this.energy -= this.energyPerTick;
					if (this.cookTime == this.recipeCookTime) {
						this.cookTime = 0;
						this.recipeCookTime = this.getCookTime();
						this.smelt(irecipe);
					}
				}
			}
		}

	}
	
	public void addEnergy(int amount) {
		this.energy += amount;
	}

	protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
		if (!getStackInNamedSlot("input").isEmpty() && recipeIn != null) {
			ItemStack itemstack = recipeIn.getRecipeOutput();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = getStackInNamedSlot("output");
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.isItemEqual(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit()
						&& itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { 
					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); 
				}
			}
		} else {
			return false;
		}
	}

	private void smelt(@Nullable IRecipe<?> recipe) {
		if (recipe != null && this.canSmelt(recipe)) {
			ItemStack itemstack = getStackInNamedSlot("input");
			ItemStack itemstack1 = recipe.getRecipeOutput();
			ItemStack itemstack2 = getStackInNamedSlot("output");
			if (itemstack2.isEmpty()) {
				this.items.set(2, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			itemstack.shrink(1);
		}
	}

	protected int getCookTime() {
		return this.world.getRecipeManager()
				.getRecipe((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.world)
				.map(AbstractCookingRecipe::getCookTime).orElse(200);
	}

	public static boolean isFuel(ItemStack stack) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
	}

	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN) {
			return SLOTS_DOWN;
		} else {
			return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
		}
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from
	 * the given side.
	 */
	public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from
	 * the given side.
	 */
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		if (direction == Direction.DOWN && index == 1) {
			Item item = stack.getItem();
			if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory() {
		return this.items.size();
	}

	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the stack in the given slot.
	 */
	public ItemStack getStackInSlot(int index) {
		return this.items.get(index);
	}
	
	public ItemStack getStackInNamedSlot(String index) {
		switch (index) {
		case "input":
			return this.items.get(0);
		
		case "fuel":
			return this.items.get(1);
		
		case "output":
			return this.items.get(2);
		
		default:
			return ItemStack.EMPTY;
		}
		
	}

	/**
	 * Removes up to a specified number of items from an inventory slot and returns
	 * them in a new stack.
	 */
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.items, index, count);
	}

	/**
	 * Removes a stack from the given slot and returns it.
	 */
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.items, index);
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.items.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.recipeCookTime = this.getCookTime();
			this.cookTime = 0;
			this.markDirty();
		}

	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with Container
	 */
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot. For guis use Slot.isItemValid
	 */
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 2) {
			return false;
		} else if (index != 1) {
			return true;
		} else {
			ItemStack itemstack = getStackInNamedSlot("fuel");
			return BatterySlot.isItemValidBattery(itemstack); // isFuel(stack) || stack.getItem() == Items.BUCKET &&
																// itemstack.getItem() != Items.BUCKET;
		}
	}

	public void clear() {
		this.items.clear();
	}

	public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
		if (recipe != null) {
			this.field_214022_n.compute(recipe.getId(), (p_214004_0_, p_214004_1_) -> {
				return 1 + (p_214004_1_ == null ? 0 : p_214004_1_);
			});
		}

	}

	@Nullable
	public IRecipe<?> getRecipeUsed() {
		return null;
	}

	public void onCrafting(PlayerEntity player) {
	}

	public void func_213995_d(PlayerEntity player) {
		List<IRecipe<?>> list = Lists.newArrayList();

		for (Entry<ResourceLocation, Integer> entry : this.field_214022_n.entrySet()) {
			player.world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((p_213993_3_) -> {
				list.add(p_213993_3_);
				spawnExpOrbs(player, entry.getValue(), ((AbstractCookingRecipe) p_213993_3_).getExperience());
			});
		}

		player.unlockRecipes(list);
		this.field_214022_n.clear();
	}

	private static void spawnExpOrbs(PlayerEntity player, int p_214003_1_, float experience) {
		if (experience == 0.0F) {
			p_214003_1_ = 0;
		} else if (experience < 1.0F) {
			int i = MathHelper.floor((float) p_214003_1_ * experience);
			if (i < MathHelper.ceil((float) p_214003_1_ * experience)
					&& Math.random() < (double) ((float) p_214003_1_ * experience - (float) i)) {
				++i;
			}

			p_214003_1_ = i;
		}

		while (p_214003_1_ > 0) {
			int j = ExperienceOrbEntity.getXPSplit(p_214003_1_);
			p_214003_1_ -= j;
			player.world.addEntity(new ExperienceOrbEntity(player.world, player.getPosX(), player.getPosY() + 0.5D,
					player.getPosZ() + 0.5D, j));
		}

	}

	public void fillStackedContents(RecipeItemHelper helper) {
		for (ItemStack itemstack : this.items) {
			helper.accountStack(itemstack);
		}

	}

	net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers = net.minecraftforge.items.wrapper.SidedInvWrapper
			.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(
			net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
		if (!this.removed && facing != null
				&& capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
				return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	/**
	 * invalidates a tile entity
	 */
	@Override
	public void remove() {
		super.remove();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}

}
