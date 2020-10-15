package kenymylankca.harshenuniverse;

import java.util.ArrayList;

import kenymylankca.harshenuniverse.potions.PotionBleeding;
import kenymylankca.harshenuniverse.potions.PotionCureal;
import kenymylankca.harshenuniverse.potions.PotionHarshed;
import kenymylankca.harshenuniverse.potions.PotionSoulless;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenPotions 
{
	public static Potion potionBleeding;
	public static Potion potionCureal;
	public static Potion potionHarshed;
	public static Potion potionSoulless;

	public static ArrayList<Potion> harshenPotions = new ArrayList<>();
	
	public static void preInit()
	{
		potionBleeding = new PotionBleeding();
		potionCureal = new PotionCureal();
		potionHarshed = new PotionHarshed();
		potionSoulless = new PotionSoulless();
	}
	
	public static void register()
	{
		reg(potionBleeding);
		reg(potionCureal);
		reg(potionHarshed);
		reg(potionSoulless);
	}
	
	private static void reg(Potion potion)
	{
		ForgeRegistries.POTIONS.register(potion);
		harshenPotions.add(potion);
	}
}