package need.chronos.command;

import java.util.Stack;

import need.chronos.ChronosPlayer;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Selection extends ChCommand
{

	boolean one;
	public Selection(String responsible, boolean one)
	{
		super(responsible);
		this.one = one;
		
	}

	@Override
	public boolean handleCommand(CommandSender sender, Stack<String> args)
	{
		if(sender instanceof Player)
		{
			ChronosPlayer player = chronos.GetChronosPlayer((Player)sender);
			if(player != null)
			{
				if(one)
				{
					player.setPos1();
					return true;
				}
				player.setPos2();
				return true;
			}
			sender.sendMessage("You are not known by this plugin, try to reconnect");
			return false;
		}
		sender.sendMessage("You aren't a Player");
		return false;
	}
	

}
