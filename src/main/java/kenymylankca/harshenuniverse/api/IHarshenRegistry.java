package kenymylankca.harshenuniverse.api;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The main Registry Interface thats passed into custom classes to be used to register pretty much everything harshenuniverse related.
 * @author Wyn Price
 *
 */
public interface IHarshenRegistry 
{
	/**
	 * Used to register a lightning ritual recipe. This recipe will be automatically be added to JEI and into game. 
	 * @param output The ItemStack of what the output item should be
	 * @param input1 One of the inputs for the recipe
	 * @param input2 One of the inputs for the recipe
	 * @param input3 One of the inputs for the recipe
	 * @param input4 One of the inputs for the recipe
	 */
	void registerLightningRecipe(ItemStack output, HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4);
	
	/**
	 * Used to register a cauldron recipe. This recipe will be automatically be added to JEI and into game.
	 * @param input The Item that goes on the top of the Heretic Cauldron; the input item.
	 * @param output The Item that is created by the recipe
	 * @param liquid The {@link CauldronLiquid} that is used for this recipe.
	 * @see IHarshenRegistry#registerCauldronLiquid(ItemStack, ItemStack, CauldronLiquid, int)
	 */
	void registerCauldronRecipe(HarshenStack input, ItemStack output, CauldronLiquid liquid);
		
	/**
	 * Used to register heretic ritual (the big one) recipes. This recipe will be automatically be added to JEI and into game.
	 * @param cauldronItem The item on the cauldron. 
	 * @param output The output item after the recipe is finished
	 * @param catalyst The {@link CauldronLiquid} that is used for this recipe. 
	 * @param input1 One of the items that go on the Pedestals
	 * @param input2 One of the items that go on the Pedestals
	 * @param input3 One of the items that go on the Pedestals
	 * @param input4 One of the items that go on the Pedestals
	 * @param input5 One of the items that go on the Pedestals
	 * @param input6 One of the items that go on the Pedestals
	 * @param input7 One of the items that go on the Pedestals
	 * @param input8 One of the items that go on the Pedestals
	 */
	void registerHereticRecipe(HarshenStack cauldronItem, ItemStack output, CauldronLiquid catalyst,  HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4, 
			 HarshenStack input5, HarshenStack input6, HarshenStack input7, HarshenStack input8);
	
	/**
	 * Used to register a new Pedestal Slab Recipe. This recipe will be automatically be added to JEI and into game.
	 * @param input The Input item that goes on top of the pedestal.
	 * @param output The output item
	 */
	void registerPedestalSlabRecipe(HarshenStack input, ItemStack output);

	/**
	 * Used to make a magic table recipe. This recipe will be automatically be added to JEI and into game.
	 * @param output The output item for the recipe.
	 * @param input1 One of the input items
	 * @param input2 One of the input items
	 * @param input3 One of the input items
	 * @param input4 One of the input items
	 */
	void registerMagicTableRecipe(ItemStack output, HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4);
		
	/**
	 * Used to register a {@link CauldronLiquid}. <b>THIS IS NOT NBT SENSITIVE</b>
	 * @param fullItem The Item used to add the liquid to the cauldron. For example it could be a bucket of water. 
	 * @param emptyItem The item to get after filling up the cauldron. Also the item to use to get liquid from the cauldron and change it into the fullItem. In my example it would be a regular bucket.
	 * 		If this is {@link ItemStack#EMPTY} then it will be used as a one way item, only capable of putting the liquid in the cauldron, then disappearing
	 * @param liquid The Actual Liquid that is used by the cauldron.
	 * @param fillBy The amount that this item fills the cauldron each time. Clamped to 1 and 3.
	 * @return The cauldron liquid you registered. (Note that if emptyItem is {@link ItemStack#EMPTY} then it will return a new, edited instance of the liquid. 
	 * 		Please set your previously registered CauldronLiquid to the return of this
	 */
	CauldronLiquid registerCauldronLiquid(ItemStack fullItem, ItemStack emptyItem, CauldronLiquid liquid, int fillBy);

	/**
	 * Used to register a CauldronLiquid through forge universal buckets. 
	 * @param fluid the fluid which the universal bucket holds.
	 * @param liquid The Actual Liquid that is used by the cauldron.
	 * @param fillBy The amount that this item fills the cauldron each time. Clamped to 1 and 3.
	 * @return The CauldronLiquid you registered. Note on some occasions it will register a new instance of the cauldron liquid.
	 */
	CauldronLiquid registerCauldronLiquid(FluidStack fluid, CauldronLiquid liquid, int fillBy);

	/**
	 * Registers a basic inventory item that can go into the harshenuniverse custom inventory.
	 * @param item The item/block that is being registered.
	 * @param slot The Slot that this item can go in. A item registered to {@link EnumAccessoryInventorySlots#LEFT_EAR} will be allowed to be put in {@link EnumAccessoryInventorySlots#RIGHT_EAR}, but not {@link EnumAccessoryInventorySlots#NECK}.
	 * @param provider The Event Provider for this item. Use regular forge events, except with the annotation {@link HarshenEvent} instead of {@link SubscribeEvent}
	 * 		The event will only be called when the player has the item in their custom inventory If null, nothing will happen to the player when the items in their inventory
	 * @param multiplyEvent Should the Event be multiplied. If the player has more than on of this item in their custom inventory, should the code run once per item, or only on the first available item. 
	 * 		True if the event should run once per item in the inventory, false if it should only run once, no matter the amount of occurrences in the inventory
	 * @param toolTipLines 	The amount of lines that is shown on the items tooltip. The language id of what you need to translate is the domain path of the item, plus the number 
	 * 		(as default is 2, it would look for the domain path, + 1 and then 2)
	 * 
	 * @see HarshenEvent
	 * @see IHarshenAccessoryProvider
	 */
	void registerInventoryItem(BlockItem item, EnumAccessoryInventorySlots slot, Object provider, boolean multiplyEvent, int toolTipLines);
		
	/**
	 * Registers a more advance item that can go into the harshenuniverse custom inventory
	 * @param item The item/block being registered
	 * @param provider The provider for that item.
	 */
	void registerInventoryItem(BlockItem item, IHarshenAccessoryProvider provider);
	
	/**
	 * Used to register a glass container into the game. This will register the glass container with a cauldron liquid.
	 * @param color the background color that is used to render the item.
	 * @param liquid The liquid that will be used in the cauldron
	 * @return The value that will be used for the meta data of the registered glass container
	 */
	int registerGlassContainer(String name, CauldronLiquid liquid, int color);
	
	/**
	 * Used to register a glass container into the game. This will register a drinkable glass container will a variety of effects.
	 * @param color the background color that is used to render the item
	 * @param name the name used for the containers name
	 * @param effects The list of effects that will be applied when the glass container is drunk
	 * @return the value that will be used for the meta data of the registered glass container
	 */
	int registerGlassContainer(String name, int color, PotionEffect... effects);

	/**
	 * Used to get the helper for this API.
	 *<br>If you want something added to this, please make an issue on my github page, and (depending on how difficult it is)
	 * I'd be happy to add it
	 * @return the helper
	 */
	IHarshenHelper getHelper();
}