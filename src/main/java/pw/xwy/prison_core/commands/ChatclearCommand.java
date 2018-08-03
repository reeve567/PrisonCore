package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatclearCommand implements CommandExecutor {
	
	public ChatclearCommand() {
		Bukkit.getPluginCommand("chatclear").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("chatclear")) {
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!p.hasPermission("xwy.admin.chatclear"))
					p.sendMessage(new String[200]);
			}
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§9Chat has been &ccleared &9by &8" + commandSender.getName() + "§9.");
			Bukkit.broadcastMessage("");
			return true;
		}
		return false;
	}
}
