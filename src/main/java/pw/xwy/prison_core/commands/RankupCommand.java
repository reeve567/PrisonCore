package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.player.XPlayer;

public class RankupCommand implements CommandExecutor {
	
	public RankupCommand() {
		Bukkit.getServer().getPluginCommand("rankup").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("rankup")) {
			if (commandSender instanceof Player) {
				Player player = (Player) commandSender;
				
				XPlayer data = PlayerManager.getXPlayer(player);
				if (data.canPrestige()) {
					data.prestige();
					player.sendMessage("prestiged");
				} else if (data.canRankup()) {
					data.rankup();
					player.sendMessage("ranked up");
				}
			} else {
				commandSender.sendMessage("must be an instance of Player");
			}
		}
		return false;
	}
}
