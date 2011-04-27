package need.chronos.chronoszone;

import need.chronos.Time;

import org.bukkit.entity.Player;

public interface ChronosZone
{
	public boolean affectsPlayer(Player player);
	
	public Time getTime(Player player);

}
