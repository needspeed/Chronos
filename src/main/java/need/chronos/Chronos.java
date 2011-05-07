package need.chronos;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Logger;
import need.chronos.chronoszone.ChronosZone;
import need.chronos.command.AddZone;
import need.chronos.command.ChCommand;
import need.chronos.command.ListZones;
import need.chronos.command.Selection;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Chronos extends JavaPlugin
{
	private ArrayList<ChronosPlayer> chronosplayers = new ArrayList<ChronosPlayer>();
	public ArrayList<ChronosZone> chronoszones = new ArrayList<ChronosZone>();
	private ChCommand[] cmds = new ChCommand[]{new AddZone("addzone"),new ListZones("listzones"), new Selection("pos1",true), new Selection("pos2",false)};

	@Override
 	public void onDisable() {}

	@Override
	public void onEnable() 
	{
		ChCommand.SetChronosInstance(this);
		for(Player player : getServer().getOnlinePlayers())
		{
			chronosplayers.add(new ChronosPlayer(player,chronoszones));
		}
		PlayerListener p = new PlayerListener() 
		{
	
	        @Override
	        public void onPlayerMove(PlayerMoveEvent event) 
	        {
	        	Update(event.getPlayer());
	        }
	
	        @Override
	        public void onPlayerJoin(PlayerJoinEvent event) 
	        {	        	
	        	chronosplayers.add(new ChronosPlayer(event.getPlayer(),chronoszones));
	        	Update(event.getPlayer());
	        }
	
	        @Override
	        public void onPlayerRespawn(PlayerRespawnEvent event) 
	        {
	        	for(ChronosPlayer player : chronosplayers)
	        	{
	        		player.onPlayerRespawn(event);
	        	}
	        	Update(event.getPlayer());
	        }
	
	        @Override
	        public void onPlayerTeleport(PlayerTeleportEvent event) 
	        {
	        	Update(event.getPlayer());
	        }

		};

	    getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, p, Priority.Monitor, this);
	    getServer().getPluginManager().registerEvent(Type.PLAYER_RESPAWN, p, Priority.Monitor, this);
	    getServer().getPluginManager().registerEvent(Type.PLAYER_TELEPORT, p, Priority.Monitor, this);
	    getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, p, Priority.Monitor, this);
	    Logger.getLogger("Minecraft").info(this.getDescription().getName() + " " + this.getDescription().getVersion() + " is enabled.");
	}
	
	public boolean isAChronosPlayer(Player player)
	{
		for(ChronosPlayer cp: chronosplayers)if(cp.getPlayer().equals(player))return true;
		return false;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
    {		
		Stack<String> cargs = stringArrayToStack(args);
		String cmdName = cargs.pop();
		System.out.println(cmdName);
		for(ChCommand chCommand : cmds)
		{
			if(chCommand.IsResponsibleFor(cmdName))
			{
				return chCommand.handleCommand(sender, cargs);
			}
		}
		return false;
	}	
	
	public void UpdateAll()
	{
		for(ChronosPlayer chronosplayer: chronosplayers)
		{
			chronosplayer.Update();
		}
	}
	
	public void Update(Player player)
	{
		for(ChronosPlayer chronosplayer: chronosplayers)
		{
			if(chronosplayer.getPlayer().equals(player))
			{
				chronosplayer.Update();
			}
		}
	}
	
	public void AddZoneToPlayersIfIsInGroupAndUpdate(ChronosZone zone, Stack<String> groups)
	{
		for(ChronosPlayer chronosplayer: chronosplayers)
		{
			for(String group : groups)
			{
				if(chronosplayer.IsInGroup(group))
				{
					chronosplayer.AddZone(zone);
				}
			}
			chronosplayer.Update();
		}
	}

	public ChronosPlayer GetChronosPlayer(Player player)
	{
		for(ChronosPlayer chronosplayer: chronosplayers)
		{
			if(chronosplayer.getPlayer().equals(player))
			{
				return chronosplayer;
			}
		}
		return null;
	}
	
	Stack<String> stringArrayToStack(String[] array)
	{
		Stack<String> stack = new Stack<String>();
		for(int i = array.length-1;i>=0;i--)
		{
			stack.add(array[i]);
		}
		return stack;
	}
}
