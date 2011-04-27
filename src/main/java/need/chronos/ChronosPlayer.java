package need.chronos;

import java.util.ArrayList;
import java.util.Collection;

import need.chronos.chronoszone.ChronosZone;

import org.bukkit.entity.Player;

public class ChronosPlayer
{
	
	private ArrayList<ChronosZone> chronoszones = new ArrayList<ChronosZone>();
	
	private Player player;
	
	public ChronosPlayer(Player player)
	{
		this.player = player;
	}
	
	public ChronosPlayer(Player player,ChronosZone zone)
	{
		this(player);
		addZone(zone);
	}
	
	public ChronosPlayer(Player player,Collection<ChronosZone> zones)
	{
		this(player);
		addZones(zones);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void addZone(ChronosZone zone)
	{
		chronoszones.add(zone);
		//ToDo: Sort list
	}
	
	public void addZones(Collection<ChronosZone> zones)
	{
		chronoszones.addAll(zones);
		//ToDo: Sort list
	}
	
	public void removeZone(ChronosZone zone)
	{
		chronoszones.remove(zone);
	}
	
	public void update()
	{
		for(ChronosZone zone:chronoszones)
		{
			if(zone.affectsPlayer(player))
			{
				Time time = zone.getTime(player);
				player.setPlayerTime(time.getTime(), time.isRelative());
				player.sendMessage("Your offset is: " + player.getPlayerTimeOffset());
				player.sendMessage("Your time is: " + player.getPlayerTime());
				break;
			}
		}
	}
}
