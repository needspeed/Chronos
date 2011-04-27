package need.chronos.chronoszone;

import need.chronos.Time;

import org.bukkit.entity.Player;

public class ChronosFixedZone implements ChronosZone
{

	Time time;
	
	public ChronosFixedZone(Time time)
	{
		this.time = time;
	}
	
	public ChronosFixedZone(long time, boolean relative)
	{
		this.time = new Time(time,relative);
	}
	
	@Override
	public boolean affectsPlayer(Player player)
	{
		return true;
	}

	@Override
	public Time getTime(Player player)
	{
		return null;
	}
	
}
