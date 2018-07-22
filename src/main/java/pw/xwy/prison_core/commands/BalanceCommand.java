package pw.xwy.prison_core.commands;

import org.bukkit.entity.Player;
import pw.xwy.prison_core.PlayerDataManager;

public class BalanceCommand {
	
	public static void run(Player player, String[] args) {
		if (args.length == 0) {
			player.sendMessage("§6Balance §8§l»» §7" + PlayerDataManager.moneySymbol + PlayerDataManager.getPlayerData(player.getUniqueId()).getBalance());
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("give")) {
				PlayerDataManager.getPlayerData(player.getUniqueId()).setBalance(PlayerDataManager.getPlayerData(player.getUniqueId()).getBalance() + Double.parseDouble(args[1]));
			}
			
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reset")) {
				PlayerDataManager.getPlayerData(player.getUniqueId()).setBalance(PlayerDataManager.startingMoney);
			}
		}
	}
	
	
}
