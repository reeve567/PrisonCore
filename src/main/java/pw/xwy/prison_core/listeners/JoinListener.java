package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.xwy.prison_core.utility.player.PlayerManager;

public class JoinListener implements Listener {
	public static int UniquePlayers;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		PlayerManager.getXPlayer(e.getPlayer());
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			e.getPlayer().setAllowFlight(false);
		}
		if (PlayerManager.isUniquePlayer(e.getPlayer())) {
			Bukkit.broadcastMessage("§7Welcome §6" + e.getPlayer().getName() + " §7to the server");
			Bukkit.broadcastMessage("§6" + ++UniquePlayers + " §7unique players have joined");
			
			//TODO STARTER KIT
			
		}
		e.getPlayer().sendMessage("§7Welcome to §6Mud§6kip §8Prison§7!");
	}
	
}
