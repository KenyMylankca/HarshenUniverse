package kenymylankca.harshenuniverse.config;

import kenymylankca.harshenuniverse.base.BaseConfig;

public class GeneralConfig extends BaseConfig
{
	public static float bloodChance;
	public static int bloodHeightRange;
	public static boolean renderHiddenPlates;
	public static double structureRuinChance;
	public static int bloodyTorchDistance;
	public static int nocturnalDistance;
	public static boolean trueInvisibility;
	public static int structureProtectorDelay;
	
	@Override
	public String getName() {
		return "General";
	}

	@Override
	public void read() {
		bloodChance = get("blood_chance", 0.35F);
		bloodHeightRange = get("blood_height_range", 15);
		renderHiddenPlates = get("render_hidden_plates", true);
		structureRuinChance = get("structure_ruin_chance", 0.2D);
		bloodyTorchDistance = get("bloody_torch_distance", 5);
		nocturnalDistance = get("nocturnal_distance", 2);
		trueInvisibility = get("true_invisibility", true);
		structureProtectorDelay = get("structure_protector_delay", 75);
	}
}