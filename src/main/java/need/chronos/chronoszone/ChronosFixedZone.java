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
				cargs.pop().equals("rel")
				)
			);
	}
	
	public static String IsValid(Stack<String> cargs)
	{
		int len = cargs.size();
		if(StringIsNumber(cargs.get(len-2)) && (cargs.get(len-3).equalsIgnoreCase("ab")||cargs.get(len-3).equalsIgnoreCase("rel")))
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
		return time;
	}
	
	@Override
	public String GetDescription()
	{
		return String.format("%1$d: %2$s; world: %3$s; time: %4$s",Id(),"fixed",worldName,time.toString());
	}
}
