package kenymylankca.harshenuniverse.api;

import java.util.ArrayList;

import org.apache.commons.logging.LogFactory;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
/**
 * The liquid that can go inside the HereticCauldron.
 * It holds information about what to render as the liquid, and how large each one of these class is, relative to the cauldron 3 level limit.
 * 
 * @author Wyn Price
 *
 */
public class CauldronLiquid
{
	/**A list containing all registered liquids*/
	public final static ArrayList<CauldronLiquid> ALL_LIQUIDS = new ArrayList<>();	
	
	/**The default empty cauldron liquid.*/
	public static final CauldronLiquid NONE = new CauldronLiquid("none");
	
	/** The name of the liquid */
	private final String name;
	/** If the liquid Should render a blockstate, the state will be stored here*/
	private IBlockState state;
	/** If the liquid Should render a ResourceLocation, it will be stored here*/
	private ResourceLocation resourceLoc;
	/** The MODID of the mod that registered the liquid. */
	private String modID;
	
	/**
	 * Used internally by all constructors.
	 * @param name The name of the liquid
	 */
	private CauldronLiquid(String name)
	{
		this.name = name;
		ALL_LIQUIDS.add(this);
	}
	
	/**
	 * Register a CauldronLiquid, were the textures displayed is a resource location.
	 * @param name The name of the liquid
	 * @param textureLocation The location of where the .png file of what the cauldron should render.
	 */
	public CauldronLiquid(String name, ResourceLocation textureLocation)
	{
		this(name);
		this.resourceLoc = textureLocation;
	}

	/**
	 * Register a CauldronLiquid, were the textures displayed is a resource location.
	 * @param name The name of the liquid
	 * @param blockState The blockstate that the cauldron will render the liquid as (note that this will be the texture of the particle tag in the block model)
	 */
	public CauldronLiquid(String name, IBlockState blockState) {
		this(name);
		this.state = blockState;
	}
	
	/**
	 * Get the name of the object
	 * @return the MODID plus the name.
	 */
	public String getName() {
		return modID + ":" + name;
	}
	
	@Override
	public String toString() {
		return modID + ":" + name;
	}
	
	/**Used to set the MODID of an object. PLEASE DONT USE THIS.*/
	public CauldronLiquid setModID(String modID)
	{
		this.modID = modID;
		return this;
	}
	
	/**
	 * Does the cauldron have a state
	 * @return true if the liquid was registered with {@link CauldronLiquid#CauldronLiquid(String, IBlockState)}
	 */
	public Boolean hasState() {
		return state != null;
	}
		
	/**
	 * Gets the CauldronLiquid from a state
	 * @param state the state to get the 
	 * @return the CauldronLiquid assigned to that state, {@link CauldronLiquid#NONE} if it doesn't exist.
	 */
	public static CauldronLiquid getFromState(IBlockState state)
	{
		for(CauldronLiquid liquid : ALL_LIQUIDS)
			if(liquid.hasState() && liquid.state.getBlock() == state.getBlock() && liquid.state.getBlock().getMetaFromState(liquid.state) == state.getBlock().getMetaFromState(state))
				return liquid;
		return NONE;
	}
	
	/**
	 * Gets the state or the location of the CauldronLiquid.
	 * @return The {@link CauldronLiquid#state} if the state exists, {@link CauldronLiquid#resourceLoc} if it dosn't.
	 */
	public Object getStateOrLoc()
	{
		return state == null? resourceLoc : state;
	}

	/**
	 * Gets a CauldronLiquid from the name. Used when loading from NBT
	 * @param name the name of the CauldronLiquid that is trying to be found
	 * @return The CauldronLiquid if found, {@link CauldronLiquid#NONE} if not
	 */
	public static CauldronLiquid getFromName(String name) {
		if(name == null || name.isEmpty()) return NONE;
		for(CauldronLiquid liquid : ALL_LIQUIDS)
			if(liquid.getName().equals(name))
				return liquid;
		if(!name.equals("null:none"))
			LogFactory.getLog("harshenuniverse").error("Tried to load an unregistered CauldronLiquid. " + name + " doesnt exist!");
		return NONE;
	}
}