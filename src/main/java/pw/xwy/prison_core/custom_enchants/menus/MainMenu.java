////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.menus;
// made by reeve
// on 1:07 PM

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomItem;

public class MainMenu {
	
	private final Inventory MainMenu;
	
	public MainMenu() {
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
	
	public Inventory get() {
		
		return MainMenu;
	}
	
}
