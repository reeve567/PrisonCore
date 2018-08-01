package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.listeners.ChatListener;

public class ChatstopCommand implements CommandExecutor {
	public ChatstopCommand() {
		Bukkit.getPluginCommand("chatstop").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		if (command.getLabel().equalsIgnoreCase("chatstop")) {
			if (commandSender.hasPermission("xwy.admin.chatstop")) {
				
				if (!ChatListener.stopped) {
					ChatListener.stopped = true;
					if (args.length == 1) {
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (!p.hasPermission("xwy.admin.chatclear")) p.sendMessage(new String[200]);
						}
					}
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("§9Chat has been §cstopped §9by §8" + commandSender.getName() + "§9.");
					Bukkit.broadcastMessage("");
				} else {
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("§9Chat has been §aresumed §9by §8" + commandSender.getName() + "§9.");
					Bukkit.broadcastMessage("");
					ChatListener.stopped = false;
				}
				
				
			}
		}
		return false;
	}
}
