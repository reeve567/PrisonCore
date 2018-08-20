package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
	
	public FlyCommand() {
		Bukkit.getPluginCommand("fly").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		//TODO do this for pvp
		if (!(commandSender instanceof Player))
			return false;
		
		if (((Player) commandSender).getAllowFlight()) {
			((Player) commandSender).setAllowFlight(false);
			commandSender.sendMessage("§6§lFly §8§l»» §7Disabled");
		} else {
			((Player) commandSender).setAllowFlight(true);
			commandSender.sendMessage("§6§lFly §8§l»» §7Enabled");
		}
		
		
		return false;
	}
	
}
