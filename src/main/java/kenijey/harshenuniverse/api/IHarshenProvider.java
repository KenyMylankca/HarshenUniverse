package kenijey.harshenuniverse.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/**
 * The Interface used to interact with everything harshenuniverse related.
 * @author Wyn Price
 *
 */
public interface IHarshenProvider 
{
	/**
	 * The Slot that the item can go into. Slots with the same "type" can go into each-other. For Example:
	 * <br>A item registered to {@link EnumAccessoryInventorySlots#LEFT_EAR} will be allowed to be put in {@link EnumAccessoryInventorySlots#RIGHT_EAR}, but not {@link EnumAccessoryInventorySlots#NECK}.
	 * @return
	 */
	public EnumAccessoryInventorySlots getSlot();
	
	/**
	 * The amount of lines that is shown on the items tooltip. The language id of what you need to translate is the domain path of the item, plus the number (as default is 2, it would look for the domain path, + 1 and then 2)
	 * <br><b>Domain Path is the registry name of the item, minus the MODID and ":"</b>
	 */
	public default int toolTipLines(){ return 2;};
	
	/**
	 * The Event Provider for this item. Use regular forge events, except with the annotation {@link HarshenEvent} instead of {@link SubscribeEvent}
	 * <br><b>The event will only be called when the player has the item in their custom inventory</b>
	 * @param stack The ItemStack passed in. Used if you want different providers for different metadata/NBT
	 * @return A new instance of the Object provider.
	 */
	public default Object getProvider(ItemStack stack){return null;};
	
	/**
	 * Should the Event be multiplied. If the player has more than on of this item in their custom inventory, should the code run once per item, or only on the first avalible item.
	 * @param stack The ItemStack passed in. Used if you want different results for different metadata/NBT
	 * @return true if the event should be run on every item the items in the inventory
	 * <br>false if it shouldn't
	 */
	public default boolean isMultiplyEvent(ItemStack stack){return true;};
	
	/**
	 * Called every tick the items in the players inventory. Useful if you just want to add potion effects
	 * @param player The players inventory the items in.
	 * @param tick The Total amount of ticks the items been in the players inventory.
	 */
	public default void onTick(EntityPlayer player, int tick){};
	
	/**
	 * Called when the items removed from the players inventory. Called ServerSide and ClientSide
	 * @param player The Player who just removed the item out of their inventory
	 */
	public default void onRemove(EntityPlayer player, int slot){};
	
	/**
	 * Called when the items added to the players inventory. Called ServerSide and ClientSide.
	 * @param player The player who just added the item to their own inventory
	 */
	public default void onAdd(EntityPlayer player, int slot){};
}
