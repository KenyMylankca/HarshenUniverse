package kenijey.harshenuniverse.enums.items;

import java.util.ArrayList;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.api.CauldronLiquid;
import kenijey.harshenuniverse.api.HarshenStack;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IStringSerializable;

public class GlassContainerValue implements IStringSerializable
{
	private final static ArrayList<GlassContainerValue> ALL_VALUES = new ArrayList<>();
	
	private static int IDSET = 0;
		
	private int meta;
	private final String name;
	private PotionEffect[] effects;
	private CauldronLiquid type;
	private boolean isSubContainer = false;
	private boolean removePaste = false;
	public int color;
	
	public GlassContainerValue(String name, CauldronLiquid type, int color)
	{
		this.color = color;
		this.type = type;
		this.name = name;
		this.effects = null;
		isSubContainer = true;
		this.meta = IDSET++;
	}
	
	public GlassContainerValue(String name, int color, PotionEffect... effects)
	{
		this.color = color;
		this.name = name;
		if(effects.length > 0)
			this.effects = effects;
		this.meta = IDSET++;
	}
	
	public boolean isSubContainer() {
		return isSubContainer;
	}
	
	public CauldronLiquid getType() {
		return type;
	}
	
	public ItemStack getStack(int amount)
	{
		return new ItemStack(HarshenItems.GLASS_CONTAINER, amount, this.meta);
	}
	 
	public ItemStack getStack()
	{
		return new ItemStack(HarshenItems.GLASS_CONTAINER, 1, this.meta);
	}
	
	public HarshenStack getHarshenStack(int amount)
	{
		return new HarshenStack(getStack(amount));
	}
	
	public HarshenStack getHarshenStack()
	{
		return new HarshenStack(getStack());
	}

	@Override
	public String getName() {
		return name;
	}
	
	public boolean isPaste() {
		return !removePaste;
	}
	
	public static GlassContainerValue getContainerFromMeta(int meta)
	{
		for(GlassContainerValue cycleEnu : GlassContainerValue.values())
			if(cycleEnu.getMeta() == meta)
				return cycleEnu;
		return GlassContainerValues.EMPTY;
	}
	
	public static CauldronLiquid getTypeFromMeta(int meta)
	{
		return getContainerFromMeta(meta).getType();
	}
	
	public static int getMetaFromType(CauldronLiquid type)
	{
		return getContainerFromType(type).meta;
	}
	
	public static GlassContainerValue getContainerFromType(CauldronLiquid type)
	{
		for(GlassContainerValue cycleEnu : GlassContainerValue.values())
			if(cycleEnu.getType() == type)
				return cycleEnu;
		return GlassContainerValues.EMPTY;
	}
	
	public PotionEffect[] getEffects() 
	{
		if(effects == null || effects[0] == null)
			return null;
		PotionEffect[] returnEffects = new PotionEffect[effects.length];
		for(int i = 0; i < effects.length; i++)
			returnEffects[i] = new PotionEffect(effects[i].getPotion(), effects[i].getDuration(), effects[i].getAmplifier(), effects[i].getIsAmbient(), effects[i].doesShowParticles());
		return returnEffects;
	}
	
	public int getMeta()
	{
		return this.meta;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static String[] getNames()
	{
		String s = "";
		for(GlassContainerValue l : GlassContainerValue.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
	
	private static int registerGlassContatiner(GlassContainerValue value)
	{
		if(!ALL_VALUES.contains(value))
			ALL_VALUES.add(value);
		return value.meta;
	}
	
	public static int registerGlassContainer(String name, int color, CauldronLiquid type)
	{
		return registerGlassContatiner(new GlassContainerValue(name, type, color));
	}
	
	public static int registerGlassContainer(String name, int color, PotionEffect...effects)
	{
		return registerGlassContatiner(new GlassContainerValue(name, color, effects));
	}
	
	public static GlassContainerValue[] values()
	{
		return ALL_VALUES.toArray(new GlassContainerValue[ALL_VALUES.size()]);
	}
	
	public static void reset()
	{
		ALL_VALUES.clear();
		IDSET = 0;
	}
}
