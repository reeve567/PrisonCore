////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.listeners;
// made by reeve
// on 12:49 PM

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.listeners.DeathListener;

public class RespawnListener implements Listener {
	
	
	@EventHandler
	public void respawn(PlayerRespawnEvent e) {
		if (DeathListener.keptItems.get(e.getPlayer()) != null) {
			
			for (ItemStack i : DeathListener.keptItems.get(e.getPlayer())) {
				e.getPlayer().getInventory().addItem(i);
			}
			DeathListener.keptItems.remove(e.getPlayer());
		}
	}
	
	
}
