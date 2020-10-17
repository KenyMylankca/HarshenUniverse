package kenymylankca.harshenuniverse.config;

import java.util.HashMap;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseConfig;
import net.minecraft.item.Item;

public class AccessoryConfig extends BaseConfig
{
	public static String[] xrayBlacklist;
	public static int xrayAreaX, xrayAreaY, xrayAreaZ, xrayListSize;
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
		xrayBlacklist = get("xray_blacklist", HarshenItems.XRAY_PENDANT, HarshenUtils.listOf("minecraft:stone"));
		xrayAreaX = get("xray_distance_x", HarshenItems.XRAY_PENDANT, 35);
		xrayAreaY = get("xray_distance_y", HarshenItems.XRAY_PENDANT, 35);
		xrayAreaZ = get("xray_distance_z", HarshenItems.XRAY_PENDANT, 35);
		xrayListSize = get("xray_list_size", HarshenItems.XRAY_PENDANT, 20); 
		enderNecklaceDistance = get("ender_necklace_distance", HarshenItems.ENDER_NECKLACE, 66);
		reachPendantLength = get("reach_pendant_length", HarshenItems.REACH_PENDANT, 7d);
		punchyRingAttackDamage = get("punchy_ring_attack_damage", HarshenItems.PUNCHY_RING, 2.5);
	}
	
	private HashMap<String, String> keyMap = new HashMap<>();
	
	private <T> T get(String name, Item item, T normal) 
	{
		return super.get(name, item.getRegistryName().getResourcePath(), normal);
	}
}