package need.chronos.chronoszone;

import java.util.Stack;

import need.chronos.Time;

import org.bukkit.entity.Player;
import static need.chronos.ChronosUtil.*;

public class ChronosFixedZone extends ChronosZone
{

	Time time;
	
	public ChronosFixedZone(String worldName, Time time)
	{
		super(worldName);
		this.time = time;
		priority = 2; //hardcoded, needs to be changed
	}
	
	public ChronosFixedZone(String worldName, long time, boolean relative)
	{
		this(worldName,new Time(time,relative));
	}
	
	public ChronosFixedZone(Stack<String> cargs)
	{
		this(cargs.pop(), 
				new Time
				(
				Integer.decode(cargs.pop()),
				Integer.decode(cargs.pop()).equals("rel")
				)
			);
	}
	
	public static String IsValid(Stack<String> cargs)
	{
		if(StringIsNumber(cargs.get(1)) && (cargs.get(2).equalsIgnoreCase("ab")||cargs.get(6).equalsIgnoreCase("rel")))
		{
			return null;
		}
		return "Expects <time> <rel|ab>";
	}
	
	@Override
	public boolean affectsPlayer(Player player)
	{
		return isInWorld(player);
	}

	@Override
	public Time getTime(Player player)
	{
		return null;
	}
	
	@Override
	public String GetDescription()
	{
		return String.format("%1$: %2$; world: %3$; time: %4$",Id(),"fixed",worldName,time.toString());
	}
}
