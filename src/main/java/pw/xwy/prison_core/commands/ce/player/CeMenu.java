////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.commands.ce.player;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.listeners.gui.ce.MainMenu;

public class CeMenu {
	
	
	public void run(CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Inventory inv = new MainMenu(player).get();
			player.openInventory(inv);
		} else
			sender.sendMessage(Messages.senderIsConsole.get());
	}
}
