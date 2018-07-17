////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.listeners;
// made by reeve
// on 8:59 PM

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.enums.Rarity;
import pw.xwy.prison_core.custom_enchants.menus.*;

public class MenuClick implements Listener {
	
	@EventHandler
	public void MenuClicked(InventoryClickEvent e) {
		
		ItemStack clicked = e.getCurrentItem();
		
		if (e.getInventory().getName().equalsIgnoreCase("§c§lCustom Enchants") ||
				e.getInventory().getName().equalsIgnoreCase(Rarity.HYDRO.getLabel()) ||
				e.getInventory().getName().equalsIgnoreCase(Rarity.MYSTICAL.getLabel()) ||
				e.getInventory().getName().equalsIgnoreCase(Rarity.RARE.getLabel()) ||
				e.getInventory().getName().equalsIgnoreCase(Rarity.UNCOMMON.getLabel()) ||
				e.getInventory().getName().equalsIgnoreCase(Rarity.COMMON.getLabel())) {
			e.setCancelled(true);
			if (clicked != null) {
				if (clicked.hasItemMeta()) {
					if (clicked.getItemMeta().hasDisplayName()) {
						if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§5Helmet Enchantments"))
							e.getWhoClicked().openInventory(HelmMenu.get().getInventory());
						else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§5Chestplate Enchantments"))
							e.getWhoClicked().openInventory(ChestMenu.get().getInventory());
						else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§5Leggings Enchantments"))
							e.getWhoClicked().openInventory(LeggingsMenu.get().getInventory());
						else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§5Boots Enchantments"))
							e.getWhoClicked().openInventory(BootsMenu.get().getInventory());
						else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§5Sword Enchantments"))
							e.getWhoClicked().openInventory(SwordMenu.get().getInventory());
						else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§5Bow Enchantments"))
							e.getWhoClicked().openInventory(BowMenu.get().getInventory());
						else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§5Pickaxe Enchantments"))
							e.getWhoClicked().openInventory(PickMenu.get().getInventory());
						else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§5Axe Enchantments"))
							e.getWhoClicked().openInventory(AxeMenu.get().getInventory());
						else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§eEXP Conversion")) {
							new ConversionMenu().openInventory((Player) e.getWhoClicked());
						}
						else if (e.getSlot() == 44)
							e.getWhoClicked().openInventory(new MainMenu().get());
					}
				}
			}
		}
	}
}
