package kenymylankca.harshenuniverse.tileentity;

import java.util.Random;

import com.kenymylankca.enhancedarmaments.util.EAUtils;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.api.HarshenStack;
import kenymylankca.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventoryActive;
import kenymylankca.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenymylankca.harshenuniverse.internal.HarshenAPIHandler;
import kenymylankca.harshenuniverse.recipes.PedestalSlabRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.Loader;

public class TileEntityPedestalSlab extends BaseTileEntityHarshenSingleItemInventoryActive
{
	private PedestalSlabRecipes workingRecipe;
	
	@Override
	protected boolean checkForCompletion(boolean checkingUp) {
		boolean flag = false;
		for(PedestalSlabRecipes recipe : HarshenAPIHandler.allPedestalRecipes)
			if(recipe.getInput().containsItem(getItemStack()))
			{
				if(!checkingUp)
					workingRecipe = recipe;
				flag = true;
			}
		
		if(Loader.isModLoaded("enhancedarmaments"))
			if(EAUtils.canEnhance(getItemStack().getItem()))
				if(getItemStack().getTagCompound().getInteger("RARITY") < 6)
				{
					ItemStack outputStack = getItemStack().copy();
					outputStack.getTagCompound().setInteger("RARITY", outputStack.getTagCompound().getInteger("RARITY") + 1);
					
					PedestalSlabRecipes EARecipe = new PedestalSlabRecipes(new HarshenStack(getItemStack()), outputStack);
					if(!checkingUp)
						workingRecipe = EARecipe;
					flag = true;
				}
		
		if(!flag)
			return false;
		
		for(int x = -1; x < 2; x++)
			for(int z = -1; z < 2; z++){
				if((world.getBlockState(pos.add(x, -1, z)).getBlock() != Blocks.SOUL_SAND || 
				world.getBlockState(pos.add(x, 0, z)).getBlock() != HarshenBlocks.BLOOD_BLOCK) && !(x == 0 && z == 0))
					flag = false;
			}
		return flag;
	}
	
	@Override
	protected void tick() {
		if(!checkForCompletion(true) && isActive())
			deactivate();
		else if(isActive())
		{
			for(int x = -1; x < 2; x++)
				for(int z = -1; z < 2; z++)
					if(!(x == 0 && z == 0))
						for(int i = 0; i < 8; i ++)
						{
							Vec3d pos = new Vec3d(this.pos.add(x, 0, z)).addVector(randPos(), -0.1, randPos());
							HarshenUniverse.proxy.spawnParticle(EnumHarshenParticle.BLOOD, pos, 
									new Vec3d((this.pos.getX() + 0.5 - pos.x) / 20D, (this.pos.getY() + 0.5 - pos.y) / 20D, (this.pos.getZ() + 0.5 - pos.z) / 20D), 1f, false);
						}
		}
		else if(checkForCompletion(false))
			activate();
	}
	
	@Override
	public void activate() {
		world.playSound(null, this.pos, HarshenSounds.BLOOD_RITUAL, SoundCategory.BLOCKS, 1f, 1f);
		this.activeTimer = 0;
		this.isActive = true;
		super.activate();
	}
	
	@Override
	public void deactivate()
	{
		Minecraft.getMinecraft().getSoundHandler().stop(HarshenSounds.BLOOD_RITUAL.getSoundName().toString(), SoundCategory.BLOCKS);
		this.isActive = false;
		this.activeTimer = 0;
	}
	
	@Override
	protected int getTicksUntillDone() {
		return 140;
	}
	
	@Override
	protected void finishedTicking() {
		if(workingRecipe != null)
			setItem(workingRecipe.getOutput());
		markDirty();
		for(int x = -1; x < 2; x++)
			for(int z = -1; z < 2; z++)
				if(!(x == 0 && z == 0))
				{
					world.setBlockToAir(pos.add(x, 0, z));
					for(int i = 0; i < new Random().nextInt(10) + 100; i++)
						HarshenUniverse.proxy.spawnParticle(EnumHarshenParticle.BLOOD, new Vec3d(pos.add(x, 0, z)).addVector(randPos(), 0, randPos()), new Vec3d(0, 0.01, 0), 1f, false);
				}
	}
}