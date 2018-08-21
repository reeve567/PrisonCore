////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 6/17/18 12:13 PM                            /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility.ce;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.enums.CEnchant;
import pw.xwy.prison_core.utility.enums.CustomEnchant;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;
import pw.xwy.prison_core.utility.InventoryUtility;
import pw.xwy.prison_core.utility.item.CustomItem;

public class ConversionMenu implements Listener {
	
	private Inventory inventory = Bukkit.createInventory(null, 27, "§0Convert XP to Enchants");
	
	public ConversionMenu() {
		InventoryUtility.setBackground(inventory, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(15).setName(" "));
		inventory.setItem(9, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(5).setName(ChatColor.DARK_GREEN + "Convert 400 XP into an enchant").setLore("&7Common"));
		inventory.setItem(11, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(13).setName(ChatColor.GREEN + "Convert 800 XP into an enchant").setLore("&7Uncommon"));
		inventory.setItem(13, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(11).setName(ChatColor.BLUE + "Convert 2000 XP into an enchant").setLore("&7Rare"));
		inventory.setItem(15, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(10).setName(ChatColor.DARK_PURPLE + "Convert 4500 XP into an enchant").setLore("&7Mystical"));
		inventory.setItem(17, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(1).setName(ChatColor.GOLD + "Convert 10000 XP into an enchant").setLore("&7Legendary"));
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getRawSlot() != -1) {
			if (e.getInventory().getTitle().equalsIgnoreCase(inventory.getTitle())) {
				e.setCancelled(true);
				Player player = (Player) e.getWhoClicked();
				if (e.getRawSlot() == 9) {
					onClick(player, Rarity.COMMON, 400);
				} else if (e.getRawSlot() == 11) {
					onClick(player, Rarity.UNCOMMON, 800);
				} else if (e.getRawSlot() == 13) {
					onClick(player, Rarity.RARE, 2000);
				} else if (e.getRawSlot() == 15) {
					onClick(player, Rarity.MYSTICAL, 4500);
				} else if (e.getRawSlot() == 17) {
					onClick(player, Rarity.HYDRO, 10000);
				}
			}
		}
	}
	
	private void onClick(Player player, Rarity rarity, int xp) {
		if (player.getInventory().firstEmpty() != -1) {
			if (player.getExp() >= xp) {
				player.getInventory().addItem(getBook(CustomEnchant.getRandomEnchant(rarity)));
				player.setExp(player.getExp() - xp);
			}
		} else {
			player.sendMessage(Messages.fullInventory.get());
		}
		
	}
	
	public static ItemStack getBook(CEnchant e) {
		return EnchantUtility.bookGive(e.getLabel(), false);
	}
	
	public void openInventory(Player player) {
		player.openInventory(inventory);
	}
	
}
