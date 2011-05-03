package need.chronos.command;

import java.util.Stack;

import org.bukkit.command.CommandSender;

import need.chronos.Chronos;

public abstract class ChCommand
{
	private final String responsible;
	protected static Chronos chronos;
	public ChCommand(String responsible)
	{
		this.responsible = responsible;
	}
	
	public boolean IsResponsibleFor(String cmd)
	{
		return cmd.equals(responsible);
	}
	
	public static void SetChronosInstance(Chronos chronos)
	{
		ChCommand.chronos = chronos;
	}
	
	public abstract boolean handleCommand(CommandSender sender, Stack<String> args);
}
