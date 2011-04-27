package need.chronos.chronoszone;

import need.chronos.Time;

import org.bukkit.entity.Player;

public class ChronosTimeZone implements ChronosZone
{
	private int scale=1000;
	@Override
	public boolean affectsPlayer(Player player)
	{
		return true;
	}

	@Override
	public Time getTime(Player player)
	{
		return new Time(-scale*player.getLocation().getBlockZ(), true);
	}

}
