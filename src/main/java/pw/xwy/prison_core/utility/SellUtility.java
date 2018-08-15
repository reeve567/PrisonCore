package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SellUtility {
	
	public static void sell(Player player, ExtraRank rank) {
		Mine mine = MineManager.extraMines.get(rank);
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
		XPlayerData config = PlayerManager.getXPlayer(player).getData();
		player.sendMessage("mine : " + rank.toString());
		player.sendMessage("amount sold : " + amount);
		player.sendMessage("multiplier : " + config.getSellMultuplier());
		player.sendMessage("total price : " + total * config.getSellMultuplier());
		PlayerManager.getXPlayer(player).getData().addBalance(total * config.getSellMultuplier());
	}
	
	public static void sell(Player player, Rank rank) {
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
		XPlayerData config = PlayerManager.getXPlayer(player).getData();
		player.sendMessage("mine : " + rank.toString());
		player.sendMessage("amount sold : " + amount);
		player.sendMessage("multiplier : " + config.getSellMultuplier());
		player.sendMessage("total price : " + total * config.getSellMultuplier());
		PlayerManager.getXPlayer(player).getData().addBalance(total * config.getSellMultuplier());
	}
	
}
