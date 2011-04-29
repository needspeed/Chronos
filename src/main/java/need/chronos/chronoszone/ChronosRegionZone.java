package need.chronos.chronoszone;

import need.chronos.ChronosPlayer;
import need.chronos.Time;

import org.bukkit.entity.Player;

public class ChronosRegionZone extends ChronosZone
{
	double x1,x2,z1,z2;
	Time time;
	
	public ChronosRegionZone(String worldName, ChronosPlayer p, Time time)
	{
		super(worldName);
		this.x1 = Math.min(p.x1, p.x2);
		this.x2 = Math.max(p.x1, p.x2);
		this.z1 = Math.min(p.z1, p.z2);
		this.z2 = Math.max(p.z1, p.z2);
		this.time = ti;
	}
	
	@Override
	public boolean affectsPlayer(Player p)
	{
		return isInWorld(p)&&(x1<p.getLocation().getX()&&x2>p.getLocation().getX()&& z1<p.getLocation().getZ()&&z2>p.getLocation().getZ());
	}

	@Override
	public Time getTime(Player player)
	{
		return time;
	}
	
}
