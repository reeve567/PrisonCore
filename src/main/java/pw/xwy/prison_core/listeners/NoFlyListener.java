package pw.xwy.prison_core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import pw.xwy.prison_core.commands.EventCommand;
import pw.xwy.prison_core.utility.Rect3D;

import java.util.ArrayList;

public class NoFlyListener implements Listener {

	private ArrayList<Rect3D> zones = new ArrayList<>();

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getPlayer().getAllowFlight() || e.getPlayer().isFlying()) {

			if (EventCommand.parkourMaze.getArea().contains(e.getPlayer().getLocation()) && !e.getPlayer().hasPermission("xwy.admin")) {
				e.getPlayer().setFlying(false);
				e.getPlayer().setAllowFlight(false);
			}
		}
	}

	@EventHandler
	public void onToggle(PlayerToggleFlightEvent e) {
		if (!e.isFlying() && EventCommand.parkourMaze.getArea().contains(e.getPlayer().getLocation()) && !e.getPlayer().hasPermission("xwy.admin")) {
			e.setCancelled(true);
		}
	}
}
