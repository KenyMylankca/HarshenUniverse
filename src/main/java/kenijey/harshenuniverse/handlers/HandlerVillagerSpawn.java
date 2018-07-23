package kenijey.harshenuniverse.handlers;

import kenijey.harshenuniverse.HarshenVillagers;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class HandlerVillagerSpawn 
{
	@SubscribeEvent
	public void onChunkPopulated(PopulateChunkEvent.Post event)
	{
		EntityVillager villager = new EntityVillager(event.getWorld(), VillagerRegistry.getId(HarshenVillagers.VALOR));
		BlockPos pos = event.getWorld().getTopSolidOrLiquidBlock(new BlockPos(event.getChunkX() * 16 + event.getRand().nextInt(16), 0, event.getChunkZ() * 16 + event.getRand().nextInt(16)));
		villager.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), villager.rotationYaw, villager.rotationPitch);
		if(event.isHasVillageGenerated())
			if(event.getRand().nextFloat() < 0.05f)
				event.getWorld().spawnEntity(villager);
	}
}