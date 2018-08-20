package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.misc_managers.CrateManager;

public class KeyCommand implements CommandExecutor {
	
	public KeyCommand() {
		Bukkit.getPluginCommand("key").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("key")) {
			if (!(commandSender instanceof Player)) {
				return false;
			}
			
			if (strings.length == 1) {
				switch (strings[0].toLowerCase()) {
					case ("vote"):
						((Player) commandSender).getInventory().addItem(CrateManager.getKey(CrateManager.CrateType.VOTE));
						break;
					case ("rare"):
						((Player) commandSender).getInventory().addItem(CrateManager.getKey(CrateManager.CrateType.RARE));
						break;
					case ("elite"):
						((Player) commandSender).getInventory().addItem(CrateManager.getKey(CrateManager.CrateType.ELITE));
						break;
					case ("tag"):
						((Player) commandSender).getInventory().addItem(CrateManager.getKey(CrateManager.CrateType.TAG));
						break;
				}
			}
		}
		return false;
	}
}
