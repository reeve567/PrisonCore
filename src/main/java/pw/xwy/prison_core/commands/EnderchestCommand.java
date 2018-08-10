package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {
	
	public EnderchestCommand() {
		Bukkit.getPluginCommand("enderchest").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("enderchest")) {
			if (!(commandSender instanceof Player)) {
				return false;
			}
			if (commandSender.hasPermission("xwy.admin.enderchest")) {
				if (strings.length > 0) {
					((Player) commandSender).openInventory(Bukkit.getPlayer(strings[0]).getEnderChest());
				} else {
					((Player) commandSender).openInventory(((Player) commandSender).getEnderChest());
				}
			} else {
				((Player) commandSender).openInventory(((Player) commandSender).getEnderChest());
			}
			
			return true;
		}
		return false;
	}
}
