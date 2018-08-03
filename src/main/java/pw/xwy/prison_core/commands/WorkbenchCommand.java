package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorkbenchCommand implements CommandExecutor {
	
	public WorkbenchCommand() {
		Bukkit.getPluginCommand("workbench").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("workbench")) {
		}
		return false;
	}
}
