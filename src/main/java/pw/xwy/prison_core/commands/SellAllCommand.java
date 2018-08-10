package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pw.xwy.prison_core.utility.*;

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
			Rank rank = ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData().getRank();
			Rank[] ranks = Rank.values();
			for (int i = 0; i <= rank.ordinal(); i++) {
				if (MineManager.mines.get(ranks[i]).getArea() != null) {
					if (MineManager.mines.get(ranks[i]).getArea().contains(player.getLocation())) {
						sell(player, ranks[i]);
						return true;
					}
				}
				
			}
			sell(player, rank);
			return true;
		}
		return false;
	}
	
	private void sell(Player player, Rank rank) {
		Mine mine = MineManager.mines.get(rank);
		Double total = 0.0;
		int amount = 0;
		Inventory inventory = player.getInventory();
		for (int i = 0; i < inventory.getSize(); i++) {
			if (inventory.getContents()[i] != null) {
				if (mine.shopContains(inventory.getContents()[i].getType(), inventory.getContents()[i].getDurability())) {
					amount += inventory.getContents()[i].getAmount();
					total += mine.shopPriceFor(inventory.getContents()[i].getType(), inventory.getContents()[i].getDurability()) * inventory.getContents()[i].getAmount();
					inventory.setItem(i, new CustomItem(inventory.getItem(i)).setCusomType(Material.AIR));
				}
			}
			
		}
		
		
		player.getInventory().setContents(inventory.getContents());
		PlayerData config = ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData();
		player.sendMessage("mine : " + rank.toString());
		player.sendMessage("amount sold : " + amount);
		player.sendMessage("multiplier : " + config.getSellMultuplier());
		player.sendMessage("total price : " + total * config.getSellMultuplier());
		ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData().addBalance(total * config.getSellMultuplier());
	}
}
