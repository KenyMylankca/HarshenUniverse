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
		
	protected <T> T get(String name, String category, T defaultValue)
	{
		return get(name, category, new TextComponentTranslation("config." + name).getUnformattedText(), defaultValue);
	}
	
	protected <T> T get(String name, String category, String comment, T defaultValue)
	{
		try
		{
			Object returned = HarshenUtils.getMethod("get", config.getClass(), String.class, String.class, defaultValue.getClass()).invoke(config, category, name, defaultValue);
			if(defaultValue.getClass().isArray())
				for(Method method : config.getClass().getMethods())
					if(method.getParameterTypes().length == 3 && method.getParameterTypes()[0] == String.class && method.getParameterTypes()[1] == String.class
					&& method.getParameterTypes()[2] == defaultValue.getClass() && method.getParameterTypes()[2].isArray())
						returned = method.invoke(config, category, name, defaultValue);
			if(!(returned instanceof Property))	throw new IllegalArgumentException("Returned Type was not a property. This is practically impossible");
			Property property = (Property) returned;
			property.setComment(comment);
			propertyMap.put(category + "*" + name, property);
			return (T) property.getClass().getMethod("get" + defaultValue.getClass().getSimpleName().replace("Integer", "Int").replace("[]", "List")).invoke(property);
		}
		catch (NullPointerException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			HarshenUniverse.LOGGER.error("Forge Config has no such getter for " + defaultValue.getClass() + ". ErrorClass: " + e.getClass().getSimpleName());
			e.printStackTrace();
		}
		return defaultValue;
	}
	
	protected <T> T get(String name, T defaultValue)
	{
		return this.get(name, getName(), defaultValue);
	}
}