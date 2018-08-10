package pw.xwy.prison_core.commands;

import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.PlayerConfig;
import pw.xwy.prison_core.utility.PlayerData;

public class BalanceCommand {
	
	public static void run(Player player, String[] args) {
		if (args.length == 0) {
			player.sendMessage("§6Balance §8§l»» §7" + PlayerConfig.moneySymbol + ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData().getBalance());
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("give"))
				ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData().addBalance(Double.parseDouble(args[1]));
			else if (args[0].equalsIgnoreCase("withdraw") || args[0].equalsIgnoreCase("w")) {
				PlayerData data = ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData();
				double amount = args[1];
				
			}
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reset")) {
				ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData().setBalance(PlayerConfig.startingMoney);
			}
		}
	}
	
	
}
