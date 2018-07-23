package kenijey.harshenuniverse;

import kenijey.harshenuniverse.base.HarshenStructure;
import kenijey.harshenuniverse.config.HarshenConfigs;
import kenijey.harshenuniverse.structures.overworld.Castle;
import kenijey.harshenuniverse.structures.overworld.Graveyard;
import kenijey.harshenuniverse.structures.overworld.Shrine;
import kenijey.harshenuniverse.structures.pontus.PontusRitual;
import kenijey.harshenuniverse.structures.pontus.TreeDome;

public class HarshenStructures 
{
	public static final HarshenStructure CASTLE = new Castle();
	public static final HarshenStructure SHRINE = new Shrine();
	public static final HarshenStructure PONTUS_TREE_DOME = new TreeDome();
	public static final HarshenStructure PONTUS_RITUAL = new PontusRitual();
	public static final HarshenStructure GRAVEYARD = new Graveyard();
	
	public static void preInit()
	{
		regStructure(CASTLE);
		regStructure(GRAVEYARD);
		regStructure(SHRINE);
		regStructure(PONTUS_TREE_DOME);
		regStructure(PONTUS_RITUAL);
	}
	
	public static void register()
	{
		for(HarshenStructure structure : HarshenConfigs.STRUCTURES.allComponants)
			if(HarshenConfigs.STRUCTURES.isEnabled(structure))
				HarshenStructure.allStructures.add(structure);
	}
	
	private static void regStructure(HarshenStructure structure)
	{
		HarshenConfigs.STRUCTURES.allComponants.add(structure);
	}
}