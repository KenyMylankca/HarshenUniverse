package com.kenymylankca.harshenuniverse.config;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseConfig;

public class AccessoryConfig extends BaseConfig
{
	public static String[] xrayBlacklist;
	public static int xrayDistance, xrayListSize;
	public static int enderNecklaceDistance;
	public static double reachPendantLength;
	public static double punchyRingAttackDamage;

	@Override
	public String getName() {
		return "Accessories";
	}

	@Override
	public void read() 
	{
		xrayBlacklist = get("xray_blacklist", HarshenUtils.listOf("minecraft:stone"));
		xrayDistance = get("xray_distance", 35);
		xrayListSize = get("xray_list_size", 20); 
		enderNecklaceDistance = get("ender_necklace_distance", 66);
		reachPendantLength = get("reach_pendant_length", 7d);
		punchyRingAttackDamage = get("punchy_ring_attack_damage", 2.5);
	}
}