package kenijey.harshenuniverse.tileentity;

import kenijey.harshenuniverse.HarshenSounds;
import kenijey.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshenuniverse.items.RitualStick;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class TileEntityHarshenSpawner extends BaseTileEntityHarshenSingleItemInventory
{
	private EntityLiving entityliving;
	private double distance = 5d;
	
	public Entity getEntity(ItemStack stack)
	{
		if(stack.getItem() == Item.getItemFromBlock(Blocks.AIR) || stack.equals(ItemStack.EMPTY))
		{
			this.entityliving = null;
			return null;
		}
		try
		{
			entityliving = (EntityLiving) EntityList.createEntityByIDFromName(ItemMonsterPlacer.getNamedIdFrom(stack), world);
            entityliving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
		}
		catch (NullPointerException e) {
		}
		return this.entityliving;
	}
	
	@Override
	protected void tick() 
	{
		EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), distance, false);
		if((player != null && getEntity(getItem()) != null) && (!player.capabilities.isCreativeMode || (player.capabilities.isCreativeMode && player.getHeldItemOffhand().getItem() instanceof RitualStick)))
			activate(player);
	}
	
	private void activate(EntityPlayer player)
	{
		setItemAir();
		this.entityliving.setPosition(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
		this.entityliving.setRotationYawHead(player.getPosition().subtract(pos).getY());
		entityliving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
		if(!world.isRemote)
			world.spawnEntity(this.entityliving);
		world.playSound(pos.getX(), pos.getY(), pos.getZ(), HarshenSounds.SPAWNER_SUMMON, SoundCategory.BLOCKS, 1f, 1f, false);
		world.setBlockToAir(pos);
	}
}