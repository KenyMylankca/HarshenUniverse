package kenymylankca.harshenuniverse.base;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

public abstract class BaseConfig
{
	protected Configuration config = null;
	private final static ArrayList<BaseConfig> ALL_CONFIGS = new ArrayList<>();
			
	public BaseConfig() {
		ALL_CONFIGS.add(this);
	}
	
	public static void reloadAll()
	{
		for(BaseConfig config : ALL_CONFIGS)
			config.syncConfig();
	}
	
	public Configuration getConfig()
	{
		return config;
	}
	
	public void preInit()
	{
		if(getName() == null)
			throw new IllegalArgumentException("name of config file cant be null");
		File configFile = new File(Loader.instance().getConfigDir(), HarshenUniverse.MODID + "/" + getName() + ".cfg");
		config = new Configuration(configFile);
		syncConfig();
	}
	
	private void syncConfig()
	{
		config.load();
		read();
		if(config.hasChanged())
			config.save();
	}
	public abstract String getName();
	
	public abstract void read();
	
	
	public static HashMap<String, Property> propertyMap = new HashMap<>();
		
	protected <T> T get(String name, String category, T normal)
	{
		return get(name, category, new TextComponentTranslation("config." + name).getUnformattedText(), normal);
	}
	
	protected <T> T get(String name, String category, String comment, T normal)
	{
		try
		{
			Object returned = HarshenUtils.getMethod("get", config.getClass(), String.class, String.class, normal.getClass()).invoke(config, category, name, normal);
			if(normal.getClass().isArray())
				for(Method method : config.getClass().getMethods())
					if(method.getParameterTypes().length == 3 && method.getParameterTypes()[0] == String.class && method.getParameterTypes()[1] == String.class
					&& method.getParameterTypes()[2] == normal.getClass() && method.getParameterTypes()[2].isArray())
						returned = method.invoke(config, category, name, normal);
			if(!(returned instanceof Property))	throw new IllegalArgumentException("Returned Type was not a property. This is practically impossible");
			Property property = (Property) returned;
			property.setComment(comment);
			propertyMap.put(category + "*" + name, property);
			return (T) property.getClass().getMethod("get" + normal.getClass().getSimpleName().replace("Integer", "Int").replace("[]", "List")).invoke(property);
		}
		catch (NullPointerException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			HarshenUniverse.LOGGER.error("Forge Config has no such getter for " + normal.getClass() + ". ErrorClass: " + e.getClass().getSimpleName());
			e.printStackTrace();
		}
		return normal;
	}
	
	protected <T> T get(String name, T normal)
	{
		return this.get(name, getName(), normal);
	}
}