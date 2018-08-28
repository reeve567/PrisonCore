package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VoteCommand implements CommandExecutor {
	
	public VoteCommand() {
		Bukkit.getPluginCommand("vote").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		commandSender.sendMessage("§8§l» §6http://bit.ly/MudkipVote1");
		return true;
	}
}
