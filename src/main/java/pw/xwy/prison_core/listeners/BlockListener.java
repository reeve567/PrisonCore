package pw.xwy.prison_core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import pw.xwy.prison_core.BlockMinedEvent;
import pw.xwy.prison_core.utility.Mine;
import pw.xwy.prison_core.utility.MineManager;
import pw.xwy.prison_core.utility.NormalWarps;

public class BlockListener implements Listener {
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!e.isCancelled()) {
			Mine mi = null;
			for (Mine mine : MineManager.mines.values()) {
				if (mine.getArea() != null) {
					if (mine.getArea().contains(e.getBlock())) {
						mi = mine;
						break;
					}
				}
			}
			if (mi == null) {
				if (NormalWarps.SPAWN.getLocation() == null) {
					e.setCancelled(true);
				} else if (e.getBlock().getWorld().getName().equalsIgnoreCase(NormalWarps.SPAWN.getLocation().getWorld().getName())) {
					e.setCancelled(true);
				}
				return;
			}
			BlockMinedEvent.call(e.getBlock(), e.getPlayer(), mi, true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (!e.isCancelled()) {
			//TODO: add support for cells
			if (NormalWarps.SPAWN.getLocation() == null) {
				e.setCancelled(true);
			} else if (e.getBlock().getWorld().getName().equalsIgnoreCase(NormalWarps.SPAWN.getLocation().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
	}
}
