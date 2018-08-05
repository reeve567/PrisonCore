package pw.xwy.prison_core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pw.xwy.prison_core.CrateManager;

public class CrateListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
				if (CrateManager.isCrate(e.getClickedBlock())) {
					e.setCancelled(true);
					//TODO: open crate based on key
					
					
					
					
					
					
				}
			}
		}
	}
	
	
}
