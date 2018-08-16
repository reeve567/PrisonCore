package pw.xwy.prison_core.commands;

import org.bukkit.entity.Player;
import pw.xwy.prison_core.listeners.gui.DonorShopGUI;

public class DonorShopCommand {
	
	public static void run(Player player) {
		new DonorShopGUI(player).openInventory(player);
	}
}
