package pw.xwy.prison_core.commands;

import org.bukkit.entity.Player;
import pw.xwy.prison_core.listeners.KitGUI;

public class KitCommand {
	
	public static void run(Player player, String[] args) {
		new KitGUI(player).openInventory();
	}
	
}
