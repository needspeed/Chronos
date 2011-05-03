package need.chronos.command;

import java.util.Stack;

import need.chronos.chronoszone.ChronosZone;

import org.bukkit.command.CommandSender;

public class ListZones extends ChCommand
{

	public ListZones(String responsible)
	{
		super(responsible);
	}

	@Override
	public boolean handleCommand(CommandSender sender, Stack<String> args)
	{
		for(ChronosZone zone : chronos.chronoszones)
		{
			sender.sendMessage(zone.GetDescription());
		}
		return true;
	}

}
