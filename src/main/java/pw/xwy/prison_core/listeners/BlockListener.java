package pw.xwy.prison_core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pw.xwy.prison_core.utility.Mine;
import pw.xwy.prison_core.utility.MineManager;

public class BlockListener implements Listener {


	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!e.isCancelled()) {
			
			for (Mine mine: MineManager.mines.values()) {
			
			}
			
			
		}
	}

}
