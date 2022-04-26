package com.kenymylankca.harshenuniverse.base;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ResourceLocation;

public abstract class BaseHarshenCommand extends CommandBase 
{
	@Override
	public abstract String getName();

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands." + getName() + ".usage";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
		
	protected void message(ICommandSender sender, String translationSuffix, Object... args)
	{
		notifyCommandListener(sender, this, "commands." + getName() + "." + translationSuffix, args);
	}
	
	public static List<String> getListOfStringsMatching(String input, Collection<?> possibleCompletions)
    {
        List<String> list = Lists.<String>newArrayList();

        if (!possibleCompletions.isEmpty())
        {
            for (String s1 : Iterables.transform(possibleCompletions, Functions.toStringFunction()))
            {
                if (doesStringStartWith(input, s1))
                {
                    list.add(s1);
                }
            }

            if (list.isEmpty())
            {
                for (Object object : possibleCompletions)
                {
                    if (object instanceof ResourceLocation && doesStringStartWith(input, ((ResourceLocation)object).getResourcePath()))
                    {
                        list.add(String.valueOf(object));
                    }
                }
            }
        }
        return list;
    }
}