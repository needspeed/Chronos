package need.chronos.chronoszone;

import need.chronos.Time;

import org.bukkit.entity.Player;

public abstract class ChronosZone implements Comparable<ChronosZone>
{
	protected String worldName;
	protected byte priority;
	private int id;
	private static int idcount = 0;
	
	public ChronosZone(String worldName)
	{
		this.worldName = worldName;
		id = idcount++;
	}
	
	public abstract boolean affectsPlayer(Player player);
	
	public abstract Time getTime(Player player);
	
	protected boolean isInWorld(Player player)
	{
		return worldName == null || player.getWorld().getName().equals(worldName);
	}
	
	public int compareTo(ChronosZone zone)
	{
		return zone.priority - priority;
	}
	
	public int Id()
	{
		return id;
	}
	
	public abstract String GetDescription();
}
