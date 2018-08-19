package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.ExtraRank;
import pw.xwy.prison_core.utility.MineManager;
import pw.xwy.prison_core.utility.PlayerManager;
import pw.xwy.prison_core.utility.Rank;

import static pw.xwy.prison_core.utility.SellUtility.sell;

public class SellAllCommand implements CommandExecutor {
	
	public SellAllCommand() {
		Bukkit.getPluginCommand("sellall").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getLabel().equalsIgnoreCase("sellall")) {
			if (!(sender instanceof Player)) {
				return false;
			}
			Player player = (Player) sender;
			Rank rank = PlayerManager.getXPlayer(player).getData().getRank();
			Rank[] ranks = Rank.values();
			
			
			for (int i = 0; i <= rank.ordinal(); i++) {
				if (args.length != 0) {
					if (ranks[i].toString().equalsIgnoreCase(args[0])) {
						sell(player, rank);
						return true;
					}
				}
				
				if (MineManager.mines.get(ranks[i]).getArea() != null) {
					if (MineManager.mines.get(ranks[i]).getArea().contains(player.getLocation())) {
						sell(player, ranks[i]);
						return true;
					}
				}
				
			}
			ExtraRank[] ranks1 = ExtraRank.values();
			for (ExtraRank rank1 : ranks1) {
				
				if (args.length != 0) {
					if (rank1.toString().equalsIgnoreCase(args[0]) && player.hasPermission("xwy.mine." + rank1.toString().toLowerCase())) {
						sell(player, rank);
						return true;
					}
				}
				
				if (MineManager.extraMines.get(rank1).getArea() != null) {
					if (MineManager.extraMines.get(rank1).getArea().contains(player.getLocation())) {
						sell(player, rank1);
						return true;
					}
				}
			}
			
			sell(player, rank);
			return true;
		}
		return false;
	}
	
	
}
