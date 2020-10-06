package kenymylankca.harshenuniverse.enums.items;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class GlassContainerValues
{
	public static GlassContainerValue EMPTY;
	public static GlassContainerValue VOID;
	public static GlassContainerValue REGEN;
	public static GlassContainerValue CURE;
	
	public static GlassContainerValue HARSHING_WATER;
	public static GlassContainerValue HARSHEN_DIMENSIONAL_FLUID;
	public static GlassContainerValue BLOOD;
	public static GlassContainerValue LAVA;
	public static GlassContainerValue MILK;
	public static GlassContainerValue WATER;
	public static GlassContainerValue EARTH;
	public static GlassContainerValue SAND;
	public static GlassContainerValue MAGIC;
	
	public final static void reloadAll()
	{
		ArrayList<Field> fields = new ArrayList<>();
		for(Field field : GlassContainerValues.class.getDeclaredFields())
			if(GlassContainerValue.class.isAssignableFrom(field.getType()))
				fields.add(field);
		for(int i = 0; i < fields.size(); i++)
		{
			try {
				fields.get(i).set(null, GlassContainerValue.getContainerFromMeta(i));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}