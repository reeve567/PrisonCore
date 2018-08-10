package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommand implements CommandExecutor {
	
	public WorkbenchCommand() {
		Bukkit.getPluginCommand("workbench").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("workbench")) {
			if (commandSender instanceof Player) {
				((Player) commandSender).openWorkbench(null, true);
				//((Player) commandSender).openInventory(Bukkit.createInventory((Player) commandSender, InventoryType.CRAFTING, "Portable Workbench"));
			}
		}
		return false;
	}
}
