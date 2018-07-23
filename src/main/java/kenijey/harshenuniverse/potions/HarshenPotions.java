package kenijey.harshenuniverse.potions;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenPotions 
{
	public static Potion potionSoulless;
	public static Potion potionHarshed;
	public static Potion potionCure;
	public static Potion potionBleeding;

	public static void preInit()
	{
		potionSoulless = new PotionSoulless();
		potionHarshed = new PotionHarshed();
		potionCure = new PotionCure();
		potionBleeding = new PotionBleeding();
	}
	
	public static void register()
	{
		reg(potionSoulless);
		reg(potionHarshed);
		reg(potionCure);
		reg(potionBleeding);
	}
	
	private static void reg(Potion potion)
	{
		ForgeRegistries.POTIONS.register(potion);
	}
}