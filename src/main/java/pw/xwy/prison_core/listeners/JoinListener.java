package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.PlayerConfig;

public class JoinListener implements Listener {
	public static int UniquePlayers;
	
	public JoinListener() {
		PrisonCore.registerEvents(this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		ConfigurationHandler.playerConfigs.put(e.getPlayer().getUniqueId(), new PlayerConfig(e.getPlayer().getUniqueId()));
		
		if (ConfigurationHandler.isUniquePlayer(e.getPlayer())) {
			Bukkit.broadcastMessage("§7Welcome §6" + e.getPlayer().getName() + " §7to the server");
			Bukkit.broadcastMessage("§6" + ++UniquePlayers + " §7unique players have joined");
		}
		e.getPlayer().sendMessage("§7Welcome to §6Mud§6kip §8Prison§7!");
	}
	
}
