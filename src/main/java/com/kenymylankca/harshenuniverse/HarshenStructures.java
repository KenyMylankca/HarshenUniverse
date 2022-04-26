package com.kenymylankca.harshenuniverse;

import com.kenymylankca.harshenuniverse.base.HarshenStructure;
import com.kenymylankca.harshenuniverse.config.HarshenConfigs;
import com.kenymylankca.harshenuniverse.structures.overworld.Castle;
import com.kenymylankca.harshenuniverse.structures.overworld.Graveyard;
import com.kenymylankca.harshenuniverse.structures.overworld.Shrine;
import com.kenymylankca.harshenuniverse.structures.pontus.House;
import com.kenymylankca.harshenuniverse.structures.pontus.PontusRitual;
import com.kenymylankca.harshenuniverse.structures.pontus.TreeDome;

public class HarshenStructures 
{
	public static final HarshenStructure CASTLE = new Castle();
	public static final HarshenStructure SHRINE = new Shrine();
	public static final HarshenStructure PONTUS_TREE_DOME = new TreeDome();
	public static final HarshenStructure PONTUS_RITUAL = new PontusRitual();
	public static final HarshenStructure GRAVEYARD = new Graveyard();
	public static final HarshenStructure HOUSE = new House();
	
	public static void preInit()
	{
		regStructure(CASTLE);
		regStructure(GRAVEYARD);
		regStructure(SHRINE);
		regStructure(PONTUS_TREE_DOME);
		regStructure(PONTUS_RITUAL);
		regStructure(HOUSE);
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