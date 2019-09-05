package kenijey.harshenuniverse.config;

import kenijey.harshenuniverse.base.BaseConfig;

public class GeneralConfig extends BaseConfig
{
	public static boolean bloodDrops;
	public static double bloodChance;
	public static int bloodHeightRange;
	public static boolean renderHiddenPlates;
	public static double structureRuinChance;
	public static int bloodyTorchDistance;
	public static int nocturnalDistance;
	
	@Override
	public String getName() {
		return "General";
	}

	@Override
	public void read() {
		bloodDrops = get("blood_drops", true);
		bloodChance = get("blood_chance", 0.35D);
		bloodHeightRange = get("blood_height_range", 15);
		renderHiddenPlates = get("render_hidden_plate", true);
		structureRuinChance = get("structure_ruin_chance", 0.2D);
		bloodyTorchDistance = get("bloody_torch_distance", 5);
		nocturnalDistance = get("nocturnal_distance", 2);
	}
}