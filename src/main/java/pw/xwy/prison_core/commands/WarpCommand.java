package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.listeners.gui.WarpGUI;
import pw.xwy.prison_core.utility.enums.Rank;
import pw.xwy.prison_core.utility.mine.MineManager;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.player.XPlayer;

import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {
	
	public WarpCommand() {
		Bukkit.getPluginCommand("warp").setExecutor(this);
		Bukkit.getPluginCommand("warp").setTabCompleter(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (!(commandSender instanceof Player))
			return false;
		
		if (strings.length == 0) {
			((Player) commandSender).openInventory(new WarpGUI((Player) commandSender, WarpGUI.MAIN_MENU).inventory);
		} else {
			XPlayer player = PlayerManager.getXPlayer((Player) commandSender);
			
			try {
				if (player.getData().getRank().isHigherOrEqual(player.getData().getRank(), Rank.valueOf(strings[0].toUpperCase())))
					((Player) commandSender).teleport(MineManager.mines.get(Rank.valueOf(strings[0].toUpperCase())).getWarp());
			} catch (Exception e) {
				((Player) commandSender).openInventory(new WarpGUI((Player) commandSender, WarpGUI.MAIN_MENU).inventory);
			}
		}
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
		
		return null;
	}
}
