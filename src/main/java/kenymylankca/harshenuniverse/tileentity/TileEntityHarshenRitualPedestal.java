package kenymylankca.harshenuniverse.tileentity;

import java.util.ArrayList;
import java.util.Random;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.HarshenStack;
import kenymylankca.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventoryActive;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketKillAllWithTag;
import kenymylankca.harshenuniverse.network.packets.MessagePacketSpawnItemParticles;
import kenymylankca.harshenuniverse.recipes.LightningRitualRecipes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class TileEntityHarshenRitualPedestal extends BaseTileEntityHarshenSingleItemInventoryActive
{
	private LightningRitualRecipes workingRecipe;
	private boolean isActiveNonController;
	private int activeNonControllerTimer = 0;
	
	@Override
	public void tick() {
		boolean flag = handler.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.AIR);
		if(flag)
			timer = 0;
		if(flag != hasItem)
		{
			hasItem = flag;
			dirty();
		}
		if(isActive && workingRecipe != null && !flag)
			if(checkForCompletion(true))
			{
				BlockPos pos = workingRecipe.getPositionOfRitual();
				for(EnumFacing facing : EnumFacing.HORIZONTALS)
					if(!((TileEntityHarshenRitualPedestal)world.getTileEntity(pos.offset(facing))).getItem().isEmpty())
						HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketSpawnItemParticles(
										((TileEntityHarshenRitualPedestal)world.getTileEntity(pos.offset(facing))).getItem(),
										new Vec3d(pos.offset(facing)).addVector(0.5, 0.85, 0.5),
										HarshenUtils.speedToPos(new Vec3d(pos.offset(facing)).addVector(0.5, 0.85, 0.5),
										new Vec3d(pos).addVector(0.5, 1, 0.5), 15D), 1f, false, 20, workingRecipe.getTag()));
				
				HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketSpawnItemParticles(workingRecipe.getOutput(),
						new Vec3d(pos).addVector(0.5, 1, 0.5), new Vec3d((randPos() - 0.5D) / 30D, (new Random().nextBoolean() ? -1 : 1 ) / 50D, (randPos() - 0.5D) / 30D), 1.5f, false,
						(int) ((activeTimer / 20f) * (activeTimer / 20f)), workingRecipe.getTag()));
			}
			else
				deactivateAll();
		if(isActiveNonController)
			activeNonControllerTimer++;
	}
	
	@Override
	public int getActiveTimer() {
		return isActive ? super.getActiveTimer() : activeNonControllerTimer;
	}
	
	private void deactivateAll() 
	{
		isActive = false;
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
			((TileEntityHarshenRitualPedestal)world.getTileEntity(workingRecipe.getPositionOfRitual().offset(facing))).deactivate();	
		workingRecipe = null;
	}
	

	public void deactiveateNonController()
	{
		activeNonControllerTimer = 0;
		isActiveNonController = false;
	}
	
	public String getTag()
	{
		return workingRecipe == null ? "" : workingRecipe.getTag();
	}
	
	public void setActiveNonController()
	{
		isActiveNonController = true;
	}
	
	@Override
	public boolean isActive() {
		return super.isActive() || isActiveNonController;
	}

	@Override
	protected boolean checkForCompletion(boolean checkingUp)
	{
		boolean found = false;
		for(LightningRitualRecipes recipe : LightningRitualRecipes.getRecipes(getItem()))
		{
			if(found)
				break;
			ArrayList<HarshenStack> localItems = new ArrayList<>(recipe.getInputs());
			for(EnumFacing facing : EnumFacing.HORIZONTALS)
			{
				BlockPos position = pos.offset(facing);
				ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
				ArrayList<Boolean> isBlock = new ArrayList<Boolean>();
				ArrayList<Boolean> isBlockHolding = new ArrayList<Boolean>();
				for(EnumFacing face : EnumFacing.HORIZONTALS)
				{
					boolean flag = world.getTileEntity(position.offset(face)) instanceof TileEntityHarshenRitualPedestal
							&& world.getBlockState(position.offset(face).down()).getBlock() == Blocks.GOLD_BLOCK;
					isBlock.add(flag);
					if(flag)
						blocks.add(position.offset(face));
				}
				if(!isBlock.contains(false))
				{
					for(EnumFacing face : EnumFacing.HORIZONTALS)
					{
						TileEntityHarshenRitualPedestal pedestal = (TileEntityHarshenRitualPedestal) world.getTileEntity(position.offset(face));
						HarshenStack removeStack = null;
						for(HarshenStack hStack : localItems)
							if(hStack.containsItem(pedestal.getItem()))
							{
								removeStack = hStack;
								break;
							}
						if(removeStack != null)
							localItems.remove(removeStack);
					}
					if(localItems.isEmpty())
					{
						if(!checkingUp)
						{
							world.playSound(null, position, HarshenSounds.LIGHTNING_RITUAL, SoundCategory.BLOCKS, 3f, 1f);
							this.workingRecipe = recipe.setUpRitual(world, position);
							activateLightningRitual(position, blocks);
						}
						found = true;
						break;
					}
				}
			}
		}
		return found;
	}
	
	private void activateLightningRitual(BlockPos pos, ArrayList<BlockPos> positions)
	{
		for(BlockPos position : positions)
			((TileEntityHarshenRitualPedestal) world.getTileEntity(position)).activate();
	}
	@Override
	protected int getTicksUntillDone() {
		return 100;
	}

	@Override
	protected void finishedTicking() {
		handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
		if(world.isRemote || workingRecipe == null)
			return;
		BlockPos pos = workingRecipe.getPositionOfRitual();
		world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
		InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), workingRecipe.getOutput().copy());
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketKillAllWithTag(workingRecipe.getTag()));
		workingRecipe = null;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("isNonControllerActive", isActiveNonController);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		isActiveNonController = compound.getBoolean("isNonControllerActive");
		super.readFromNBT(compound);
	}
}