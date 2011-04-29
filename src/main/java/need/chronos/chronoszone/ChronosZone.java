package need.chronos.chronoszone;

import need.chronos.Time;

import org.bukkit.entity.Player;

public abstract class ChronosZone
{
	protected String worldName;
	
	public ChronosZone(String worldName)
	{
		this.worldName = worldName;
	}
	
	public abstract boolean affectsPlayer(Player player);
	
	public abstract Time getTime(Player player);
	
	protected boolean isInWorld(Player player)
	{
		return worldName == null || player.getWorld().getName().equals(worldName);
	}
}
