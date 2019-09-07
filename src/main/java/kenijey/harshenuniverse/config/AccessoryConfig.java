package kenijey.harshenuniverse.config;

import java.util.HashMap;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseConfig;
import net.minecraft.item.Item;

public class AccessoryConfig extends BaseConfig
{
	public static String[] blackListedXrays;
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
		blackListedXrays = get("xray.blacklist", HarshenItems.XRAY_PENDANT, HarshenUtils.listOf("minecraft:stone"));
		xrayAreaX = get("xray.distance.x", HarshenItems.XRAY_PENDANT, 35);
		xrayAreaY = get("xray.distance.y", HarshenItems.XRAY_PENDANT, 35);
		xrayAreaZ = get("xray.distance.z", HarshenItems.XRAY_PENDANT, 35);
		xrayListSize = get("xray.listsize", HarshenItems.XRAY_PENDANT, 20); 
		enderNecklaceDistance = get("endernecklace.distance", HarshenItems.ENDER_NECKLACE, 66);
		reachPendantLength = get("reach.length", HarshenItems.REACH_PENDANT, 7d);
		punchyRingAttackDamage = get("punchy.ring.attack.damage", HarshenItems.PUNCHY_RING, 2.5);
	}
	
	private HashMap<String, String> keyMap = new HashMap<>();
	
	private <T> T get(String name, Item item, T normal) 
	{
		return super.get(name, item.getRegistryName().getResourcePath(), normal);
	}
}