package kenymylankca.harshenuniverse.handlers;

import com.google.common.eventbus.Subscribe;

import kenymylankca.harshenuniverse.HarshenBlocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

public class HandlerBlockBurn 
{
	@Subscribe
	public void itemBurn(FurnaceFuelBurnTimeEvent furnaceBurn)
	{
		Item item = furnaceBurn.getItemStack().getItem();
		if(item == Item.getItemFromBlock(HarshenBlocks.HARSHEN_MAGIC_TABLE))
			furnaceBurn.setBurnTime(500);
	}
}
