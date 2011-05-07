package need.chronos.chronoszone;

import java.util.Stack;
import need.chronos.Time;
import static need.chronos.ChronosUtil.*;
import org.bukkit.entity.Player;

public class ChronosRegionZone extends ChronosZone
{
	int x1,x2,z1,z2;
	Time time;
	
	public ChronosRegionZone(String worldName,int x1, int z1,int x2,int z2,Time time)
	{
		super(worldName);
		this.x1 = Math.min(x1, x2);
		this.x2 = Math.max(x1, x2);
		this.z1 = Math.min(z1, z2);
		this.z2 = Math.max(z1, z2);
		this.time = time;
	}
	
	// /chronos addzone regionzone <time> <rel|ab> <player|group|all> --> <worldname> <x1> <z1> <x2> <z2> <time> <re|ab>
	public ChronosRegionZone(Stack<String> cargs)
	{
		this(
				cargs.pop(),
				Integer.decode(cargs.pop()),
				Integer.decode(cargs.pop()),
				Integer.decode(cargs.pop()),
				Integer.decode(cargs.pop()),
				new Time(
						Integer.decode(cargs.pop()),
						cargs.pop().equals("rel")
						)
			);
		priority = 1; //hardcoded, needs to be changed
	}
	public static String IsValid(Stack<String> cargs)
	{
		int len = cargs.size();
		if(
			cargs.size() >= 7 && 
			StringIsNumber(cargs.get(len-2)) && 
			StringIsNumber(cargs.get(len-3)) && 
			StringIsNumber(cargs.get(len-4)) && 
			StringIsNumber(cargs.get(len-5)) && 
			StringIsNumber(cargs.get(len-6)) && 
			(cargs.get(len-7).equalsIgnoreCase("ab")||cargs.get(6).equalsIgnoreCase("rel"))
		  )
		{
			return null;
		}
		return "Invalid arguments, as player input <time> <rel|ab>, from console <worldname> <x1> <z1> <x2> <z2> <time> <re|ab>";
	}
	
	@Override
	public boolean affectsPlayer(Player p)
	{
		return isInWorld(p)&&
		(
				x1-1<p.getLocation().getX()&&
				x2+1>p.getLocation().getX()&& 
				z1-1<p.getLocation().getZ()&&
				z2+1>p.getLocation().getZ()
		);
	}

	@Override
	public Time getTime(Player player)
	{
		return time;
	}

	@Override
	public String GetDescription()
	{
		return String.format("%1$d: %2$s; world: %3$s; time: %4$s; loc: (%5$d|%6$d)-(%7$d|%8$d)", Id(),"region",worldName,time.toString(),x1,z1,x2,z2);
	}
	
}
