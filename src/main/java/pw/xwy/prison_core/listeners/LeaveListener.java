package pw.xwy.prison_core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pw.xwy.prison_core.utility.player.PlayerManager;

public class LeaveListener implements Listener {
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		PlayerManager.unloadPlayer(e.getPlayer().getUniqueId());
	}
	
	
}
