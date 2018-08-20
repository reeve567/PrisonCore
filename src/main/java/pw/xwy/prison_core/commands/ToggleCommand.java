package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ToggleCommand implements CommandExecutor {
	public static ArrayList<UUID> disabled = new ArrayList<>();
	
	public ToggleCommand() {
		Bukkit.getServer().getPluginCommand("scoreboardtoggle").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getLabel().equalsIgnoreCase("scoreboardtoggle")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (disabled.contains(player.getUniqueId())) {
					disabled.remove(player.getUniqueId());
					player.sendMessage("§7You have §aenabled §7the scoreboard.");
				} else {
					disabled.add(player.getUniqueId());
					player.sendMessage("§7You have §cdisabled §7the scoreboard.");
					player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
				}
			}
		}
		return false;
	}
}
