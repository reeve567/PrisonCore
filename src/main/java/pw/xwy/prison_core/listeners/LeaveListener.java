package pw.xwy.prison_core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.player.PlayerManager;

public class LeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage("§6§lSystem §8§l»» §6" + e.getPlayer().getName() + "§7 has quit the server");
		PrisonCore.log(e.getPlayer().getName() + " has left -- " + e.getPlayer().getAddress().toString().substring(1), 2);
		PlayerManager.unloadPlayer(e.getPlayer().getUniqueId());
	}


}
