package need.chronos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import need.chronos.chronoszone.ChronosZone;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ChronosPlayer
{
	public  int x1, z1, x2, z2;
	private ArrayList<ChronosZone> chronoszones = new ArrayList<ChronosZone>();
	private ArrayList<String> groups;
	private Player player;
	
	public ChronosPlayer(Player player)
	{
		this.player = player;
	}
	
	public ChronosPlayer(Player player,ChronosZone zone)
	{
		this(player);
		AddZone(zone);
	}
	
	public ChronosPlayer(Player player,Collection<ChronosZone> zones)
	{
		this(player);
		AddZones(zones);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void AddZone(ChronosZone zone)
	{
		chronoszones.add(zone);
		Collections.sort(chronoszones);
	}
	
	public void AddZones(Collection<ChronosZone> zones)
	{
		chronoszones.addAll(zones);
		Collections.sort(chronoszones);
	}
	
	public void RemoveZone(ChronosZone zone)
	{
		chronoszones.remove(zone);
	}
	
	public void Update()
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

	public void AddToGroup(String group)
	{
		if(groups == null)
		{
			groups = new ArrayList<String>();
		}
		groups.add(group);
	}

	public boolean IsInGroup(String group)
	{
		return group.equals(player.getName()) || groups.contains(group) || group.equalsIgnoreCase("all");
	}

	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		if(player.equals(event.getPlayer()))
			player = event.getPlayer();
	}

	public void AddZoneIfIsInGroup(String group, ChronosZone zone)
	{
		if(IsInGroup(group))
		{
			AddZone(zone);
		}
	}
	
	public void setPos1()
	{
		x1 = player.getLocation().getBlockX();
		z1 = player.getLocation().getBlockZ();
	}
	
	public void setPos2()
	{
		x2 = player.getLocation().getBlockX();
		z2 = player.getLocation().getBlockZ();
	}
}

