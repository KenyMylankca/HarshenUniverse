package kenijey.harshenuniverse.tileentity;

import java.util.UUID;

import kenijey.harshenuniverse.HarshenSounds;
import kenijey.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshenuniverse.config.GeneralConfig;
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
	private EntityLiving entityliving = null;
	private UUID spawnedEntityUUID = null;
	private ItemStack spawnedEntitysEgg = null;
	
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
            spawnedEntitysEgg = stack;
		}
		catch (NullPointerException e) {
		}
		return this.entityliving;
	}
	
	@Override
	protected void tick() 
	{
		EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), GeneralConfig.harshenSpawnerDistance, false);
		if(player != null && getEntity(getItem()) != null && !player.isCreative())
			activate(player);
		
		if(entityliving==null)
			for(Entity entity : world.loadedEntityList)
				if(entity.getUniqueID() == spawnedEntityUUID)
				{
					if(entityliving == null && !entity.isEntityAlive())
						world.setBlockToAir(pos);
					if(entity.isEntityAlive() && (player == null || player.isCreative()) && entityliving == null)
						deactivate(spawnedEntitysEgg, entity);
				}
	}
	
	private void activate(EntityPlayer player)
	{
		setItemAir();
		this.entityliving.setPosition(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
		this.entityliving.setRotationYawHead(player.getPosition().subtract(pos).getY());
		entityliving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
		if(!world.isRemote)
			world.spawnEntity(this.entityliving);
		spawnedEntityUUID = entityliving.getUniqueID();
		world.playSound(pos.getX(), pos.getY(), pos.getZ(), HarshenSounds.SPAWNER_SUMMON, SoundCategory.BLOCKS, 1f, 1f, false);
	}
	
	private void deactivate(ItemStack stack, Entity entity)
	{
		setItem(stack);
		entity.setDead();
	}
}