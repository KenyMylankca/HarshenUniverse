package kenijey.harshenuniverse.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import kenijey.harshenuniverse.base.BaseConfig;
import kenijey.harshenuniverse.handlers.HandlerPontusAllowed;
import kenijey.harshenuniverse.handlers.server.HandlerSyncConfig;
import kenijey.harshenuniverse.interfaces.HarshenCommand;
import kenijey.harshenuniverse.interfaces.HarshenCommandTabList;
import kenijey.harshenuniverse.interfaces.ICommandStructure;
import kenijey.harshenuniverse.network.HarshenNetwork;
import kenijey.harshenuniverse.network.packets.MessagePacketPlayerHasAccess;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class HarshenCommands 
{
	@HarshenCommand
	public static void reload(MinecraftServer server, ICommandSender sender, String[] args)
	{
		BaseConfig.reloadAll();
		for(EntityPlayer player : sender.getEntityWorld().playerEntities)
			HandlerSyncConfig.syncConfigWithClient((EntityPlayerMP) player);
		message(sender, "success");
	}
	
	@HarshenCommand
	public static void pontuslevel(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		EntityPlayer player = args.length == 1 ? CommandBase.getCommandSenderAsPlayer(sender) : CommandBase.getPlayer(server, sender, args[0]);
		String rawInt = args[args.length == 1 ? 0 : 1];
		int i = CommandBase.parseInt(rawInt);
		player.getEntityData().setInteger("PontusBiomeLevel", i);
		HarshenNetwork.sendToPlayer(player, new MessagePacketPlayerHasAccess(player));
		HandlerPontusAllowed.setAllowed(player, i);
		message(sender, "success", player.getName(), i);
	}
	
	@HarshenCommandTabList
	public static List<String> pontuslevel_tabList(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos)
	{
		return args.length != 1? Collections.emptyList() : CommandBase.getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
	}
	
	@HarshenCommand
	public static void loadstructure(MinecraftServer server, ICommandSender sender, String[] args) throws NumberInvalidException
	{
		if(args.length < 1)
		{
			message(server, "usage");
			return;
		}
		ICommandStructure struc = null;
		for(ICommandStructure structure : ICommandStructure.ALL_STRUCTURES)
			if(structure.structureName().equals(args[0]))
				struc = structure;
		if(struc != null)
		{
            BlockPos blockpos = sender.getPosition();
            Vec3d vec3d = sender.getPositionVector();
            double d0 = vec3d.x;
            double d1 = vec3d.y;
            double d2 = vec3d.z;

            if (args.length >= 4)
            {
                d0 = CommandBase.parseDouble(d0, args[1], true);
                d1 = CommandBase.parseDouble(d1, args[2], false);
                d2 = CommandBase.parseDouble(d2, args[3], true);
                blockpos = new BlockPos(d0, d1, d2);
            }
            struc.addToWorld(sender.getEntityWorld(), blockpos, new Random(), false);
            message(sender, "success");
		}
		else
			message(sender, "notfound");
	}
	
	@HarshenCommandTabList
	public static List<String> loadstructure_tabList(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos)
	{
		ArrayList<String> stringList = new ArrayList<>();
		if(args.length == 1)
			for(ICommandStructure structure : ICommandStructure.ALL_STRUCTURES)
				stringList.add(structure.structureName());
		else if(args.length < 5)
			 stringList.addAll(CommandBase.getTabCompletionCoordinate(args, 1, targetPos));
		return stringList;
	}

	private static void message(ICommandSender sender, String translationSuffix, Object... args) {
		HarshenCommandLoader.sendMessage(sender, translationSuffix, args);
	}
}
