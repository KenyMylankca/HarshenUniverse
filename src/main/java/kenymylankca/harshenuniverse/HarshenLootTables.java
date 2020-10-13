package kenymylankca.harshenuniverse;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class HarshenLootTables 
{
	public final static ResourceLocation shrine = reg("chests/shrine");
	public final static ResourceLocation castle = reg("chests/castle");
	public final static ResourceLocation graveyard = reg("chests/graveyard");
	public final static ResourceLocation zombieDrops = reg("entities/zombie_eye");
	public final static ResourceLocation golemDrops = reg("entities/iron_heart");
	public final static ResourceLocation endermanDrops = reg("entities/popped_chorus_fruit");
	public final static ResourceLocation soulPartDrops = reg("entities/soul_fragment");
	public final static ResourceLocation harshenSoulDrops = reg("entities/harshen_soul");
	public final static ResourceLocation soullessKnightDrops = reg("entities/soulless_knight");
	public final static ResourceLocation kazzendreDrop = reg("entities/kazzendre");
	public final static ResourceLocation bloodySheepDrop = reg("entities/bloody_sheep");
	public final static ResourceLocation jacobDrop = reg("entities/jacob");
	
	public static ResourceLocation reg(String name)
	{
		return LootTableList.register(new ResourceLocation(HarshenUniverse.MODID, name));
	}
}