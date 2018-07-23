package kenijey.harshenuniverse.internal;

import java.util.ArrayList;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.HarshenPlugin;
import kenijey.harshenuniverse.api.IHarshenPlugin;
import kenijey.harshenuniverse.enums.items.GlassContainerValue;
import kenijey.harshenuniverse.objecthandlers.HarshenAPIError;
import kenijey.harshenuniverse.recipies.CauldronRecipes;
import kenijey.harshenuniverse.recipies.HereticRitualRecipes;
import kenijey.harshenuniverse.recipies.LightningRitualRecipes;
import kenijey.harshenuniverse.recipies.MagicTableRecipe;
import kenijey.harshenuniverse.recipies.PedestalSlabRecipes;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

public class HarshenAPIHandler
{
	public final static ArrayList<IHarshenPlugin> ALL_PLUGINS = new ArrayList<>();
	
	public static ArrayList<LightningRitualRecipes> allRitualRecipes = new ArrayList<>();
	public static ArrayList<CauldronRecipes> allCauldronRecipes = new ArrayList<>();
	public static ArrayList<HereticRitualRecipes> allHereticCauldronRecipes = new ArrayList<>();
	public static ArrayList<PedestalSlabRecipes> allPedestalRecipes = new ArrayList<>();
	public static ArrayList<MagicTableRecipe> allMagicTableRecipes = new ArrayList<>();
		
	public static void loadPlugins(ASMDataTable asmData) {
		for(IHarshenPlugin plugin : HarshenUtils.getInstancesOfAnnotation(asmData, HarshenPlugin.class, IHarshenPlugin.class))
			if(plugin.getModID() != HarshenUniverse.MODID) ALL_PLUGINS.add(plugin);
			else ALL_PLUGINS.add(0, plugin);
	}
	
	private static String getPluginNames()
	{
		String allModIds = "";	
		for(int i = 0; i < ALL_PLUGINS.size(); i++)
			if(i == 0) allModIds += ALL_PLUGINS.get(i).getModID();
			else if(i == ALL_PLUGINS.size() - 1) allModIds += " and " + ALL_PLUGINS.get(i).getModID();
			else allModIds += ", " + ALL_PLUGINS.get(i).getModID();
		return allModIds;
	}
	
	public static void register()
	{
		allRitualRecipes.clear();
		allCauldronRecipes.clear();
		allHereticCauldronRecipes.clear();
		allPedestalRecipes.clear();
		allMagicTableRecipes.clear();
		GlassContainerValue.reset();
		
		HarshenUniverse.LOGGER.info("Sending registry events to the following mods: " + getPluginNames());
		for(IHarshenPlugin plugin : ALL_PLUGINS)
			try 
			{
				plugin.register(new HarshenRegistry(plugin.getModID()));
			}
			catch (Throwable t) {
				throw new HarshenAPIError("An error occured from HarshenUniverses API handler. Guilty mod -> " + plugin.getModID(), t);
			}
	}
}