package kenymylankca.harshenuniverse.objecthandlers;

import net.minecraft.item.Item;

public class InventoryItem
{
	public final Item item;
	
	public final int meta;
	
	public InventoryItem(Item item, int meta)
	{
		this.item = item;
		this.meta = meta;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof InventoryItem && ((InventoryItem)obj).item == item && (meta > 0 ? ((InventoryItem)obj).meta == meta : true);
	}
}