package kenymylankca.harshenuniverse.tileentity;

import java.util.UUID;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
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
	private EntityLiving entityTurning = null;
	private UUID spawnedEntityUUID = null;
	private ItemStack spawnedEntitysEgg = null;
	private boolean isActive = false;
	
	public Entity getEntity()
	{
		if(getItemStack().getItem() == Item.getItemFromBlock(Blocks.AIR) || getItemStack().equals(ItemStack.EMPTY))
		{
			this.entityTurning = null;
			return null;
		}
		try
		{
			entityTurning = (EntityLiving) EntityList.createEntityByIDFromName(ItemMonsterPlacer.getNamedIdFrom(getItemStack()), world);
            entityTurning.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityTurning)), (IEntityLivingData)null);
            spawnedEntitysEgg = getItemStack();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		return this.entityTurning;
	}
	
	@Override
	protected void tick() 
	{
		EntityPlayer playerToActivate = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 9, false);
		EntityPlayer playerToDeactivate = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 18, false);
		
		if(playerToActivate != null && getEntity() != null && !playerToActivate.isCreative() && !playerToActivate.isDead)
			activate(playerToActivate);
		
		boolean isInWorld = false;
		if(isActive)
			for(Entity entity : world.loadedEntityList)
				if(entity.getUniqueID() == spawnedEntityUUID)
				{
					isInWorld = true;
					if(entity.isEntityAlive() && (playerToDeactivate == null || playerToDeactivate.isCreative() || playerToDeactivate.isDead))
						deactivate(spawnedEntitysEgg, entity);
				}
		if(isActive && !isInWorld && !world.isRemote)
			world.setBlockToAir(pos);
	}
	
	private void activate(EntityPlayer player)
	{
		isActive = true;
		setItemAir();
		this.entityTurning.setPosition(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
		this.entityTurning.setRotationYawHead(player.getPosition().subtract(pos).getY());
		entityTurning.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityTurning)), (IEntityLivingData)null);
		if(!world.isRemote)
			world.spawnEntity(this.entityTurning);
		spawnedEntityUUID = entityTurning.getUniqueID();
		entityTurning = null;
		world.playSound(pos.getX(), pos.getY(), pos.getZ(), HarshenSounds.SPAWNER_SUMMON, SoundCategory.BLOCKS, 0.8f, player.world.rand.nextFloat() + 0.1F, false);
	}
	
	private void deactivate(ItemStack stack, Entity entity)
	{
		isActive = false;
		setItem(stack);
		entityTurning = (EntityLiving) EntityList.createEntityByIDFromName(ItemMonsterPlacer.getNamedIdFrom(stack), world);
		entity.setDead();
	}
}