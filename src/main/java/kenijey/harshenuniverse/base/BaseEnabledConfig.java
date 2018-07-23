package kenijey.harshenuniverse.base;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Property;

public abstract class BaseEnabledConfig<T> extends BaseConfig
{
	public ArrayList<T> allComponants = new ArrayList<>();
	private HashMap<T, Boolean> enabledMap = new HashMap<>();
	

	@Override
	public String getName() {
		return getNameType() + " Enabled";
	}
	
	private final String CATEGORY = getNameType() + " enables";

	
	public abstract String getNameType();
	
	protected boolean testIfLegit(T componant)
	{
		return true;
	}
	
	protected abstract String getComponantPathInConfig(T componant);
	
	protected abstract String getComponantCommentName(T componant);
	@Override
	public void read() {
		for(T componant: allComponants)
		{
			if(!testIfLegit(componant))
			{
				continue;
			}
			Property property = config.get(CATEGORY, getComponantPathInConfig(componant), true);
			property.setComment(new TextComponentTranslation("config.isEnabled", getComponantCommentName(componant)).getUnformattedText());
			enabledMap.put(componant, property.getBoolean());
		}
	}
	
	public boolean isEnabled(T componant)
	{
		if(!enabledMap.containsKey(componant))
			return true;
		return enabledMap.get(componant);
	}

}
