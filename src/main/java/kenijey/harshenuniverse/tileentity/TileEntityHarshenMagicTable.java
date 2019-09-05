package kenijey.harshenuniverse.tileentity;

import java.util.ArrayList;
import java.util.Random;

import kenijey.harshenuniverse.HarshenSounds;
import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.base.BaseTileEntityHarshenInventory;
import kenijey.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenijey.harshenuniverse.objecthandlers.HarshenItemStackHandler;
import kenijey.harshenuniverse.objecthandlers.HarshenMachineItemStackHandler;
import kenijey.harshenuniverse.recipes.MagicTableRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class TileEntityHarshenMagicTable extends BaseTileEntityHarshenInventory
{
	private MagicTableRecipe overstandingRecipe;
	private int fuseTime;
	
	public TileEntityHarshenMagicTable() {
		super(5);
	}
	
	@Override
	protected void tick() 
	{
		if(hasRecipe() && overstandingRecipe == null && getItem(4).getMaxStackSize() > getItem(4).getCount())
			overstandingRecipe = getRecipe();
		if(overstandingRecipe != null)
			if(!world.isRemote)
			{
				if(fuseTime == 1) world.playSound(null, pos, HarshenSounds.MAGIC_TABLE, SoundCategory.BLOCKS, 1f, 1f);
				
				if(fuseTime++ > 100)
				{
					if(isSlotEmpty(4))
						setItem(4, overstandingRecipe.getOutput().copy());
					else
						getItem(4).grow(1);
					deactivate();
					for(int i = 0; i < 4; i++)
						getItem(i).shrink(1);
				}
				else if(!hasRecipe())
					deactivate();
				else;
			}
			else
				for(int i = 0; i < (fuseTime / 20f) * (fuseTime / 20f); i++)
					HarshenUniverse.proxy.spawnParticle(EnumHarshenParticle.ITEM, new Vec3d(pos).addVector(0.5, 1.25, 0.5), 
							new Vec3d(( MathHelper.clamp(new Random().nextDouble(), 0.15, 0.85) - 0.5D) / 30D, 
									(new Random().nextBoolean() ? -1 : 1 ) / 50D, (MathHelper.clamp(new Random().nextDouble(), 0.15, 0.85) - 0.5D) / 30D),
							1.5f, false, overstandingRecipe.getOutput());
	}
	
	private void deactivate()
	{
		fuseTime = 0;
		overstandingRecipe = null;
		markDirty();
	}
	
	private MagicTableRecipe getRecipe()
	{
		ArrayList<ItemStack> tempStacks = new ArrayList<>();
		for(int i = 0; i < 4; i++)
				tempStacks.add(getItem(i));
		return MagicTableRecipe.getRecipeFromStacks(tempStacks);
	}
	
	private boolean hasRecipe()
	{
		return getRecipe() != null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		fuseTime = compound.getInteger("fuseTime");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("fuseTime", fuseTime);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public HarshenItemStackHandler getCapablityHandler(HarshenItemStackHandler handler) {
		return new HarshenMachineItemStackHandler(handler).addBlockedSlots(4);
	}
}