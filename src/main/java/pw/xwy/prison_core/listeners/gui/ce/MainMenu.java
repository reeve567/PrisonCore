////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.listeners.gui.ce;
// made by reeve
// on 1:07 PM

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.ce.*;
import pw.xwy.prison_core.utility.enums.Rarity;
import pw.xwy.prison_core.utility.item.CustomItem;

public class MainMenu implements Listener {
	
	private Inventory MainMenu;
	
	public MainMenu(Player player) {
		if (player != null) {
			MainMenu = Bukkit.createInventory(null, 54, "§c§lCustom Enchants");
			
			for (int i = 0; i < 54; i++) {
				ItemStack topPane = new CustomItem(Material.STAINED_GLASS_PANE).setName(" ");
				topPane.setDurability((short) 7);
				MainMenu.setItem(i, topPane);
			}
			
			ItemStack Helm = new CustomItem(Material.DIAMOND_HELMET).setName("§5Helmet Enchantments");
			ItemStack Pick = new CustomItem(Material.DIAMOND_PICKAXE).setName("§5Pickaxe Enchantments");
			ItemStack Chest = new CustomItem(Material.DIAMOND_CHESTPLATE).setName("§5Chestplate Enchantments");
			ItemStack Sword = new CustomItem(Material.DIAMOND_SWORD).setName("§5Sword Enchantments");
			ItemStack Axe = new CustomItem(Material.DIAMOND_AXE).setName("§5Axe Enchantments");
			ItemStack Leggings = new CustomItem(Material.DIAMOND_LEGGINGS).setName("§5Leggings Enchantments");
			ItemStack Bow = new CustomItem(Material.BOW).setName("§5Bow Enchantments");
			ItemStack Boots = new CustomItem(Material.DIAMOND_BOOTS).setName("§5Boots Enchantments");
			
			MainMenu.setItem(13, Helm);
			MainMenu.setItem(20, Pick);
			MainMenu.setItem(22, Chest);
			MainMenu.setItem(24, Sword);
			MainMenu.setItem(29, Axe);
			MainMenu.setItem(31, Leggings);
			MainMenu.setItem(33, Bow);
			MainMenu.setItem(40, Boots);
			MainMenu.setItem(49, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(4).setName("§eEXP Conversion"));
		}
	}
	
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
						} else if (e.getSlot() == 44)
							e.getWhoClicked().openInventory(new MainMenu((Player) e.getWhoClicked()).get());
					}
				}
			}
		}
	}
	
	public Inventory get() {
		
		return MainMenu;
	}
	
}
