package need.chronos.chronoszone;


import java.util.Stack;

import need.chronos.Time;
import org.bukkit.entity.Player;
import static need.chronos.ChronosUtil.*;

public class ChronosTimeZone extends ChronosZone
{
	
	private int scale = 10;
	private boolean relative = true;
	private int offset = 0;
	
	public ChronosTimeZone(String worldName)
	{
		super(worldName);
	}
	public ChronosTimeZone(String worldName, int scale)
	{
		super(worldName);
		this.scale = scale;
	}
	public ChronosTimeZone(String worldName, int scale, boolean relative)
	{
		this(worldName, scale);
		this.relative = relative;
	}
	public ChronosTimeZone(String worldName, int scale, int offset, boolean relative)
	{
		this(worldName, scale, relative);
		this.offset = offset;
	}

	// /chronos addzone timezone <worldname> [all|group|player]
	// /chronos addzone timezone <worldname> <scale> [all|group|player]
	// /chronos addzone timezone <worldname> <scale> <offset> [all|group|player]
	// /chronos addzone timezone <worldname> <scale> <rel|ab> [all|group|player]
	// /chronos addzone timezone <worldname> <scale> <offset> <rel|ab> [all|group|player]
	public ChronosTimeZone(Stack<String> cargs)
	{
		super(cargs.pop());
		if(StringIsNumber(cargs.peek()))
		{
			scale = Integer.decode(cargs.pop());
		}
		if(StringIsNumber(cargs.peek()))
		{
			offset = Integer.decode(cargs.pop());
		}
		if(cargs.peek().equalsIgnoreCase("rel") || cargs.peek().equalsIgnoreCase("+"))
		{
			relative = true;
			cargs.pop();
		}
		else if (cargs.peek().equalsIgnoreCase("ab") || cargs.peek().equalsIgnoreCase("-"))
		{
			relative = false;
			cargs.pop();
		}
		priority = 0; //hardcoded, needs to be changed
	}
	@Override
	public boolean affectsPlayer(Player player)
	{
		return isInWorld(player);
	}

	@Override
	public Time getTime(Player player)
	{
		return new Time(-scale*player.getLocation().getBlockZ()+offset, relative);
	}

}
