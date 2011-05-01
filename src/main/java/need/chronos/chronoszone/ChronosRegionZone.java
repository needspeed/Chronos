package need.chronos.chronoszone;

import java.util.Stack;
import need.chronos.ChronosPlayer;
import need.chronos.Time;
import static need.chronos.ChronosUtil.*;
import org.bukkit.entity.Player;

public class ChronosRegionZone extends ChronosZone
{
	double x1,x2,z1,z2;
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
	public ChronosRegionZone(Stack<String> cargs) throws Exception
	{
		this(
				cargs.pop(),
				Integer.decode(cargs.pop()),
				Integer.decode(cargs.pop()),
				Integer.decode(cargs.pop()),
				Integer.decode(cargs.pop()),
				new Time(
						Integer.decode(cargs.pop()),
						Integer.decode(cargs.pop()).equals("rel")
						)
			);
		priority = 1; //hardcoded, needs to be changed
	}
	public static String isValid(Stack<String> cargs)
	{
		if(
			cargs.size() >= 7 && 
			StringIsNumber(cargs.get(1)) && 
			StringIsNumber(cargs.get(2)) && 
			StringIsNumber(cargs.get(3)) && 
			StringIsNumber(cargs.get(4)) && 
			StringIsNumber(cargs.get(5)) && 
			(cargs.get(6).equalsIgnoreCase("ab")||cargs.get(6).equalsIgnoreCase("rel"))
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
				x1<p.getLocation().getX()&&
				x2>p.getLocation().getX()&& 
				z1<p.getLocation().getZ()&&
				z2>p.getLocation().getZ()
		);
	}

	@Override
	public Time getTime(Player player)
	{
		return time;
	}
	
}
