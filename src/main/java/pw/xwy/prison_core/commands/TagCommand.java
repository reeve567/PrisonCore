package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.listeners.gui.TagGUI;

import java.util.List;

public class TagCommand implements CommandExecutor, TabCompleter {
	
	public TagCommand() {
		Bukkit.getPluginCommand("tags").setExecutor(this);
		Bukkit.getPluginCommand("tags").setTabCompleter(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		new TagGUI((Player) commandSender);
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
		return null;
	}
}
