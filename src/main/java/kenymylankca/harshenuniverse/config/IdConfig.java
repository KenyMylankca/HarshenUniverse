package kenymylankca.harshenuniverse.config;

import kenymylankca.harshenuniverse.base.BaseConfig;
import kenymylankca.harshenuniverse.dimensions.DimensionPontus;
import net.minecraft.util.text.TextComponentTranslation;

public class IdConfig extends BaseConfig
{
	public static int PontusDimension;
	public static int EntitySoulPart;
	public static int EntitySoullessKnight;
	public static int EntityThrown;
	public static int EntityHarshenSoul;
	public static int EntitySoulShooter;
	public static int Kazzendre;
	public static int BloodySheep;
	public static int Jacob;
	
	@Override
	public String getName() {
		return "Ids";
	}

	@Override
	public void read()
	{
		PontusDimension = get("pontus_dimension", DimensionPontus.DIM_NAME, 5);
		EntitySoullessKnight = get("entity_soulless_knight", 83);
		EntitySoulPart = get("entity_soul_part", 84);
		EntityThrown = get("entity_thrown", 85);
		EntityHarshenSoul = get("entity_harshen_soul", 86);
		EntitySoulShooter = get("entity_soul_shooter", 87);
		Kazzendre = get("entity_kazzendre", 88);
		BloodySheep = get("entity_bloody_sheep", 89);
		Jacob = get("entity_jacob", 90);
	}
	
	@Override
	protected <T> T get(String name, String configName, T id) {
		return super.get(name, getName(), new TextComponentTranslation("config.id",  new TextComponentTranslation(configName).getUnformattedText()).getUnformattedText(), id);
	}
	
	@Override
	protected <T> T get(String name, T id) {
		return get(name, name.replaceFirst("_", ".") + ".name", id);
	}
}