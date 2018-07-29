////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 6/17/18 12:13 PM                            /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.CustomEnchant;
import pw.xwy.prison_core.custom_enchants.enums.Messages;
import pw.xwy.prison_core.custom_enchants.enums.Rarity;
import pw.xwy.prison_core.custom_enchants.utilities.InventoryUtility;
import pw.xwy.prison_core.custom_enchants.utilities.MainUtility;
import pw.xwy.prison_core.utility.CustomItem;

public class ConversionMenu implements Listener {
	
	private Inventory inventory = Bukkit.createInventory(null, 27, "§0Convert XP to Enchants");
	
	public ConversionMenu() {
		InventoryUtility.setBackground(inventory, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(15).setName(" "));
		inventory.setItem(9, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(5).setName(ChatColor.DARK_GREEN + "Convert 15 levels into an enchant").setLore("&7Common"));
		inventory.setItem(11, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(13).setName(ChatColor.GREEN + "Convert 25 levels into an enchant").setLore("&7Uncommon"));
		inventory.setItem(13, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(11).setName(ChatColor.BLUE + "Convert 35 levels into an enchant").setLore("&7Rare"));
		inventory.setItem(15, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(10).setName(ChatColor.DARK_PURPLE + "Convert 45 levels into an enchant").setLore("&7Mystical"));
		inventory.setItem(17, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(1).setName(ChatColor.GOLD + "Convert 50 levels into an enchant").setLore("&7Legendary"));
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getRawSlot() != -1) {
			if (e.getInventory().getTitle().equalsIgnoreCase(inventory.getTitle())) {
				e.setCancelled(true);
				Player player = (Player) e.getWhoClicked();
				if (e.getRawSlot() == 9) {
					onClick(player, Rarity.COMMON, 15);
				} else if (e.getRawSlot() == 11) {
					onClick(player, Rarity.UNCOMMON, 25);
				} else if (e.getRawSlot() == 13) {
					onClick(player, Rarity.RARE, 35);
				} else if (e.getRawSlot() == 15) {
					onClick(player, Rarity.MYSTICAL, 45);
				} else if (e.getRawSlot() == 17) {
					onClick(player, Rarity.HYDRO, 50);
				}
			}
		}
	}
	
	private void onClick(Player player, Rarity rarity, int level) {
		if (player.getInventory().firstEmpty() != -1) {
			if (player.getLevel() >= level) {
				player.getInventory().addItem(getBook(CustomEnchant.getRandomEnchant(rarity)));
				player.setLevel(player.getLevel() - level);
			}
		} else {
			player.sendMessage(Messages.fullInventory.get());
		}
		
	}
	
	public static ItemStack getBook(CEnchant e) {
		return MainUtility.bookGive(e.getLabel(), false);
	}
	
	public void openInventory(Player player) {
		player.openInventory(inventory);
	}
	
}
