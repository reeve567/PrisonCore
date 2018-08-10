package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class AutosellCommand implements CommandExecutor {
	
	public static ArrayList<UUID> toggled = new ArrayList<>();
	
	public AutosellCommand() {
		Bukkit.getPluginCommand("autosell").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("autosell")) {
			if (!(commandSender instanceof Player)) {
				return false;
			}
			Player player = (Player) commandSender;
			if (toggled.contains(player.getUniqueId())) {
				//off
				toggled.remove(player.getUniqueId());
			} else {
				//on
				toggled.add(player.getUniqueId());
			}
		}
		return false;
	}
}
