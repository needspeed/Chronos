package need.chronos.command;

import java.util.Stack;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import need.chronos.ChronosPlayer;
import need.chronos.chronoszone.ChronosFixedZone;
import need.chronos.chronoszone.ChronosRegionZone;
import need.chronos.chronoszone.ChronosTimeZone;
import need.chronos.chronoszone.ChronosZone;

public class AddZone extends ChCommand
{

	public AddZone(String responsible)
	{
		super(responsible);
	}

	@Override
	public boolean handleCommand(CommandSender sender, Stack<String> args)
	{
		String zoneName = args.pop();
		System.out.println(zoneName);
		ChronosZone zone = null;
		
		if(sender instanceof Player)
		{
			args.add(((Player)sender).getWorld().getName());
		}
		else if(sender instanceof ConsoleCommandSender)
		{
			if(chronos.getServer().getWorld(args.peek())==null);
			{
				sender.sendMessage("World doesn't exist");
				return false;
			}
		}
		if(zoneName.equalsIgnoreCase("timezone"))
		{
			zone = new ChronosTimeZone(args);
		}
		else if(zoneName.equalsIgnoreCase("regionzone"))
		{
			if(sender instanceof Player)
			{
				ChronosPlayer player = chronos.GetChronosPlayer((Player)sender);
				try
				{
					int len = args.size();
					args.add(len-1,new Integer(player.x1).toString());
					args.add(len-1,new Integer(player.z1).toString());
					args.add(len-1,new Integer(player.x2).toString());
					args.add(len-1,new Integer(player.z2).toString());
				}
				catch(NullPointerException ex)
				{
					sender.sendMessage("No selection found");
					return false;
				}
			}
			
			String error = ChronosRegionZone.IsValid(args);
			if(error == null)
			{
				zone = new ChronosRegionZone(args);
			}
			else
			{
				sender.sendMessage(error);
				return false;
			}
		}
		else if(zoneName.equalsIgnoreCase("fixedzone"))
		{
			String error = ChronosFixedZone.IsValid(args);
			if(error == null)
			{
				zone = new ChronosFixedZone(args);
			}
			else
			{
				sender.sendMessage(error);
				return false;
			}
		}
		if(zone != null)
		{
			chronos.chronoszones.add(zone);
			chronos.AddZoneToPlayersIfIsInGroupAndUpdate(zone, args);
			return true;
		}
		return false;
	}

}
