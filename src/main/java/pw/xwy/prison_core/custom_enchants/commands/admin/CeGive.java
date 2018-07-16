////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.commands.admin;
// made by reeve
// on 12:32 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.Messages;
import pw.xwy.prison_core.custom_enchants.enums.Souls;

import static pw.xwy.prison_core.custom_enchants.utilities.MainUtility.bookGive;
import static pw.xwy.prison_core.custom_enchants.utilities.MainUtility.cmdCheck;


public class CeGive {
	
	public void run(CommandSender sender, String[] args) {
		if (sender.hasPermission("ce.admin") || sender.getName().equalsIgnoreCase("Xwy")) {
			if (args.length >= 3) {
				Player target = null;
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getName().equalsIgnoreCase(args[2])) {
						target = p;
					}
				}
				if (target != null) {
					boolean found = false;
					
					for (CEnchant e : CEnchant.values()) {
						if (cmdCheck(e.getLabel(), args[1])) {
							found = true;
							target.getInventory().addItem(bookGive(args[1], false));
							sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have given " + e.getName() + ChatColor.GRAY + " to " +
									ChatColor.RED + target.getName());
						}
					}
					for (Souls s : Souls.values()) {
						if (cmdCheck(s.getCommandLabel(), args[1])) {
							found = true;
							target.getInventory().addItem(s.getItem());
							sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have given " + s.getName() + ChatColor.GRAY + " to " +
									ChatColor.RED + target.getName());
						}
					}
					
					if (!found) {
						sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Unknown enchantment: " + args[1]);
					}
				} else
					sender.sendMessage(ChatColor.RED + "Player " + ChatColor.AQUA + args[2] + " not found.");
			} else if (args.length == 2) {
				if (sender instanceof Player) {
					boolean found = false;
					for (CEnchant e : CEnchant.values()) {
						if (cmdCheck(e.getLabel(), args[1])) {
							found = true;
							((Player) sender).getInventory().addItem(bookGive(args[1], false));
							sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have received: " + e.getName());
						}
					}
					for (Souls s : Souls.values()) {
						if (cmdCheck(s.getCommandLabel(), args[1])) {
							found = true;
							((Player) sender).getInventory().addItem(s.getItem());
							sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have received: " + s.getName());
						}
					}
					
					if (!found) {
						sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Unknown enchantment/key: " + args[1]);
					}
				} else {
					sender.sendMessage(Messages.senderIsConsole.get());
				}
			} else {
				if (sender instanceof Player) {
					sender.sendMessage(ChatColor.GRAY + "/ce give" + ChatColor.RED + " <enchant> [player]");
				} else {
					sender.sendMessage(Messages.senderIsConsole.get());
				}
			}
		}
	}
	
}
