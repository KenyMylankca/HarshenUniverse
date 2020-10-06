package kenymylankca.harshenuniverse;

import java.util.Random;

import kenymylankca.harshenuniverse.objecthandlers.HarshenTrade;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class HarshenVillagers 
{
	public static VillagerProfession VALOR;
	
	public static void preInit()
	{
		VALOR = regProfession("valor");
		
		regCareer(VALOR, "badge", new HarshenTrade() {@Override public ItemStack getOutput(Random random) {return new ItemStack(HarshenItems.VALOR_BADGE);}
			@Override public ItemStack getInputOne(Random random) {return new ItemStack(Items.EMERALD, random.nextInt(10) + 15);}});
	}
	
	private static VillagerProfession regProfession(String name)
	{
		VillagerProfession prof = new VillagerProfession(HarshenUniverse.MODID + ":" + name, HarshenUniverse.MODID + ":textures/entity/villager/" + name + ".png",
				HarshenUniverse.MODID + ":textures/entity/zombie_villager/" + name + ".png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(prof);
		return prof;
	}
	
	private static VillagerCareer regCareer(VillagerProfession parent, String name, HarshenTrade trade)
	{
		return new VillagerCareer(parent, name).addTrade(1, trade);
	}
}
