package com.kenymylankca.harshenuniverse.commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import kenymylankca.harshenuniverse.base.BaseHarshenCommand;
import kenymylankca.harshenuniverse.interfaces.HarshenCommand;
import kenymylankca.harshenuniverse.interfaces.HarshenCommandTabList;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class HarshenCommandLoader extends BaseHarshenCommand
{
	@Override
	public String getName() {
		return "harshenuniverse";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length > 0 && getAllAvalibleMethods().contains(args[0]))
			try {
				loadMethod(args[0], server, sender, args);
			} catch (CommandException e) {
				throw e;
			} catch (Throwable e) {
				message(sender, args[0] + ".errored");
			}
		else
			message(sender, "notfound", argsToObjs(args));
	}
	
	public ArrayList<String> getAllAvalibleMethods()
	{
		ArrayList<String> stringList = new ArrayList<>();
		for(Method method : HarshenCommands.class.getMethods())
			if(method.getAnnotation(HarshenCommand.class) != null)
				stringList.add(method.getName());
		return stringList;
	}
	
	public void loadMethod(String methodName, MinecraftServer server, ICommandSender sender, String[] args) throws Throwable
	{
		try {
			getMethod(methodName, HarshenCommand.class).invoke(this, server, sender, getNewArgs(args));
		} catch (Throwable e){
			if(e instanceof InvocationTargetException)
			{
				throw ((InvocationTargetException) e).getTargetException();
			}
			else
				message(sender, "failed", argsToObjs(args));
		}		
	}
	
	public <T extends Annotation> Method getMethod(String methodName, Class<T> claz)
	{
		for(Method method : HarshenCommands.class.getMethods())
			if(method.getAnnotation(claz) != null && method.getName().equalsIgnoreCase(methodName))
				return method;
		return null;
	}
	
	public static void sendMessage(ICommandSender sender, String translationSuffix, Object... args) {
		notifyCommandListener(sender, new HarshenCommandLoader(), "commands.harshenuniverse." + Thread.currentThread().getStackTrace()[3].getMethodName() + "." + translationSuffix, args);
	}
	
	private Object[] argsToObjs(String... args)
	{
		Object[] objs = new Object[args.length];
		for(int i = 0; i < args.length; i++)
			objs[i] = args[i];
		return objs;
	}
	
	private String[] getNewArgs(String[] args)
	{
		String[] argList = new String[args.length - 1];
		for(int i = 1; i < args.length; i++)
			argList[i-1] = args[i];
		return argList;
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		if(args.length == 0) return super.getTabCompletions(server, sender, args, targetPos);
		else if(args.length == 1) return getListOfStringsMatching(args[0], getAllAvalibleMethods());
		else
		{
			if(!getAllAvalibleMethods().contains(args[0]))
				return super.getTabCompletions(server, sender, args, targetPos);
			Method method = getMethod(args[0] + "_tabList", HarshenCommandTabList.class);
			if(method != null && method.getReturnType() == List.class)
				try {
					return (List<String>) method.invoke(new HarshenCommands(), server, sender, getNewArgs(args), targetPos);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			return super.getTabCompletions(server, sender, args, targetPos);
		}
	}
}