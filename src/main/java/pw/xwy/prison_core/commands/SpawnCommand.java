package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.enums.NormalWarps;

public class SpawnCommand implements CommandExecutor {
	
	public SpawnCommand() {
		Bukkit.getPluginCommand("spawn").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("spawn")) {
			if (commandSender instanceof Player) {
				if (NormalWarps.SPAWN.getLocation() != null)
					((Player) commandSender).teleport(NormalWarps.SPAWN.getLocation());
			}
		}
		return false;
	}
}
