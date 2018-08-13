package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.CrateManager;
import pw.xwy.prison_core.utility.NormalWarps;

public class SetCommand implements CommandExecutor {
	public SetCommand() {
		Bukkit.getPluginCommand("set").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		if (command.getLabel().equalsIgnoreCase("set")) {
			if (!commandSender.hasPermission("xwy.admin.set")) {
				return true;
			}
			
			if (args.length == 0) {
			
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("warp")) {
					if (commandSender instanceof Player) {
						boolean found = false;
						for (NormalWarps warp : NormalWarps.values()) {
							if (warp.toString().equalsIgnoreCase(args[1].toUpperCase())) {
								found = true;
								break;
							}
						}
						if (found) {
							NormalWarps.valueOf(args[1].toUpperCase()).setLocation(((Player) commandSender).getLocation());
						} else {
							commandSender.sendMessage("No warp exists");
						}
						
					} else {
						commandSender.sendMessage("must be run as player");
					}
				}
			} else if (args[0].equalsIgnoreCase("crate")) {
				if (commandSender instanceof Player) {
					commandSender.sendMessage("tried to set");
					return CrateManager.setCrate((Player) commandSender);
				} else {
					commandSender.sendMessage("must be run as player");
				}
			}
		}
		return false;
	}
}
