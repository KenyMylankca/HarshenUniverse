package kenymylankca.harshenuniverse.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;
/**
 * A simple class that can hold either an Item or a block. Used to prevent modders being able to register a biome as an inventory item
 * @author Wyn Price
 */
public class BlockItem 
{
	/**The Item/Block*/
	public final Impl impl;
	
	public BlockItem(Item item) {
		impl = item;
	}
	
	public BlockItem(Block block) {
		impl = block;
	}
}