package pw.xwy.prison_core.utility.mine;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.enums.ExtraRank;
import pw.xwy.prison_core.utility.enums.Rank;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.player.XPlayer;

import java.util.ArrayList;

public class SellUtility {
	private static final String bar = "§6§l§m«§8§m-------------------§6§l§m»";
	public static final String[] message = {
			bar,
			"§6Mud§bKip §7Sell All",
			"",
			"§7Shop: §6@shop@",
			"§7Items Sold: §6@amount@",
			"§7Amount: §6$@price@",
			"§7Multiplier: §6@multiplier@",
			bar
		
	};
	
	public static void sell(Player pl, ExtraRank rank) {
		Mine mine = MineManager.extraMines.get(rank);
		Double total = 0.0;
		int amount = 0;
		Inventory inventory = pl.getInventory();
		for (int i = 0; i < inventory.getSize(); i++) {
			if (inventory.getContents()[i] != null) {
				if (mine.shopContains(inventory.getContents()[i].getType(), inventory.getContents()[i].getDurability())) {
					amount += inventory.getContents()[i].getAmount();
					total += mine.shopPriceFor(inventory.getContents()[i].getType(), inventory.getContents()[i].getDurability()) * inventory.getContents()[i].getAmount();
					inventory.setItem(i, new CustomItem(inventory.getItem(i)).setCusomType(Material.AIR));
				}
			}
			
		}
		
		pl.getInventory().setContents(inventory.getContents());
		XPlayer player = PlayerManager.getXPlayer(pl);
		
		ArrayList<String> strings = new ArrayList<>();
		
		sendMessage(total, amount, player, strings, rank.toString());
		player.sendMessage(strings.toArray(new String[0]));
		
		PlayerManager.getXPlayer(player).getData().addBalance(total * player.getSellMultuplier());
	}
	
	private static void sendMessage(Double total, int amount, XPlayer player, ArrayList<String> strings, String string) {
		for (String s : message) {
			s = s.replaceAll("@shop@", string);
			s = s.replaceAll("@amount@", String.valueOf(amount));
			s = s.replaceAll("@price@", String.valueOf((int) (total * player.getSellMultuplier() * 1000) / 1000.0));
			s = s.replaceAll("@multiplier@", String.valueOf((int) (player.getSellMultuplier() * 1000) / 1000.0));
			strings.add(s);
		}
	}
	
	public static void sell(Player pl, Rank rank) {
		Mine mine = MineManager.mines.get(rank);
		Double total = 0.0;
		int amount = 0;
		Inventory inventory = pl.getInventory();
		for (int i = 0; i < inventory.getSize(); i++) {
			if (inventory.getContents()[i] != null) {
				if (mine.shopContains(inventory.getContents()[i].getType(), inventory.getContents()[i].getDurability())) {
					amount += inventory.getContents()[i].getAmount();
					total += mine.shopPriceFor(inventory.getContents()[i].getType(), inventory.getContents()[i].getDurability()) * inventory.getContents()[i].getAmount();
					inventory.setItem(i, new CustomItem(inventory.getItem(i)).setCusomType(Material.AIR));
				}
			}
			
		}
		
		pl.getInventory().setContents(inventory.getContents());
		XPlayer player = PlayerManager.getXPlayer(pl);
		ArrayList<String> strings = new ArrayList<>();
		
		sendMessage(total, amount, player, strings, rank.toString());
		pl.sendMessage(strings.toArray(new String[0]));
		
		PlayerManager.getXPlayer(pl).getData().addBalance(total * player.getSellMultuplier());
	}
	
}
