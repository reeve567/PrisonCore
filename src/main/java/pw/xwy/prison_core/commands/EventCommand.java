package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.prison_events.OneVersusOne;

public class EventCommand implements CommandExecutor {
	
	public static OneVersusOne oneVersusOne = new OneVersusOne();
	
	public EventCommand() {
		Bukkit.getPluginCommand("event").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (!(commandSender instanceof Player)) {
			return false;
		}
		
		if (strings.length == 0) {
			((Player) commandSender).teleport(oneVersusOne.getSpec());
			return true;
		} else if (commandSender.hasPermission("xwy.events.admin")) {
			if (strings.length == 1) {
				if (strings[0].equalsIgnoreCase("move")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (oneVersusOne.getSpecArea().contains(p.getLocation())) {
							p.teleport(oneVersusOne.getToGo());
						}
					}
				} else if (strings[0].equalsIgnoreCase("end")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (oneVersusOne.getArea().contains(p.getLocation())) {
							p.teleport(oneVersusOne.getWin());
							p.getInventory().clear();
						}
					}
				} else if (strings[0].equalsIgnoreCase("round")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (oneVersusOne.getWinArea().contains(p.getLocation())) {
							p.teleport(oneVersusOne.getToGo());
						}
					}
				}
			} else if (strings.length == 2) {
				if (strings[0].equalsIgnoreCase("win")) {
					Player player = Bukkit.getPlayer(strings[1]);
					player.teleport(oneVersusOne.getWin());
				}
			} else if (strings.length == 3) {
				if (strings[0].equalsIgnoreCase("put")) {
					Player player1 = Bukkit.getPlayer(strings[1]);
					Player player2 = Bukkit.getPlayer(strings[2]);
					oneVersusOne.moveToActive(player1, player2);
				}
			}
		}
		return false;
	}
}
