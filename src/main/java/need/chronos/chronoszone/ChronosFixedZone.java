package need.chronos.chronoszone;

import need.chronos.Time;

import org.bukkit.entity.Player;

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
	
}
