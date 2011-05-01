package need.chronos;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Logger;

import need.chronos.chronoszone.ChronosZone;

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
	private ArrayList<ChronosZone> chronoszones = new ArrayList<ChronosZone>();

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() 
	{ 
		PlayerListener p = new PlayerListener() 
		{
	
	        @Override
	        public void onPlayerMove(PlayerMoveEvent event) 
	        {
	        	event.getPlayer().sendMessage(isAChronosPlayer(event.getPlayer())+"");
	        	event.getPlayer().sendMessage(chronosplayers.toString());
	        	updateAll();
	        }
	
	        @Override
	        public void onPlayerJoin(PlayerJoinEvent event) 
	        {	        	
	        	chronosplayers.add(new ChronosPlayer(event.getPlayer(),chronoszones));
	        	updateAll();
	        }
	
	        @Override
	        public void onPlayerRespawn(PlayerRespawnEvent event) 
	        {
	        	for(ChronosPlayer player : chronosplayers)
	        	{
	        		player.onPlayerRespawn(event);
	        	}
	        	updateAll();
	        }
	
	        @Override
	        public void onPlayerTeleport(PlayerTeleportEvent event) 
	        {
	        	updateAll();
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
		if (cmd.getName().equals("chronos"))
		{
			
			return true;
    	}
		else return false;
	}	
	
	public void updateAll()
	{
		for(ChronosPlayer chronosplayer: chronosplayers)
		{
			chronosplayer.Update();
		}
	}
	
	Stack<String> stringArrayToStack(String[] array)
	{
		Stack<String> stack = new Stack<String>();
		stack.addAll(java.util.Arrays.asList(array));
		return stack;
	}
}
