package com.kenymylankca.harshenuniverse.integration.jei;

import kenymylankca.harshenuniverse.HarshenUniverse;

public class JEICategoryUIDs 
{
	public static final String LIGHTNING_RITUAL = getString("lightning_ritual");
	public static final String HERETIC_CAULDRON = getString("heretic_cauldron");
	public static final String PENDESTAL_SLAB = getString("pedestal_slab");
	public static final String HERETIC_RITUAL = getString("heretic_ritual");
	public static final String MAGIC_TABLE = getString("magic_table");
	
	private static String getString(String string)
	{
		return HarshenUniverse.MODID + "." + string;
	}
}