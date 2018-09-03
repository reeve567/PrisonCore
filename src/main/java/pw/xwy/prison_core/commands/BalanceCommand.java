package pw.xwy.prison_core.commands;

import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.item.VoucherUtility;
import pw.xwy.prison_core.utility.player.XPlayer;
import pw.xwy.prison_core.utility.player.XPlayerConfig;

public class BalanceCommand {
	
	public static void run(Player player, String[] args) {
		if (args.length == 0) {
			player.sendMessage("§6Balance §8§l»» §7" + XPlayerConfig.moneySymbol + PlayerManager.getXPlayer(player).getBalance());
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("give") && player.hasPermission("xwy.giveMoney"))
				PlayerManager.getXPlayer(player).addBalance(Double.parseDouble(args[1]));
			else if (args[0].equalsIgnoreCase("withdraw") || args[0].equalsIgnoreCase("w")) {
				XPlayer xplayer = PlayerManager.getXPlayer(player);
				if (args[1].equalsIgnoreCase("all")) {
					double amount = xplayer.getBalance();
					xplayer.setBalance(0);
					player.getInventory().addItem(VoucherUtility.getMoneyVoucher(player, amount));
				} else {
					double amount = Double.parseDouble(args[1]);
					
					if (xplayer.getBalance() >= amount) {
						xplayer.addBalance(-amount);
						player.getInventory().addItem(VoucherUtility.getMoneyVoucher(player, amount));
					}
				}
				
			}
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reset")) {
				PlayerManager.getXPlayer(player).setBalance(XPlayerConfig.startingMoney);
			}
		}
	}
	
	
}
