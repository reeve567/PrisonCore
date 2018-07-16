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
import pw.xwy.prison_core.custom_enchants.utilities.MainUtility;
import pw.xwy.prison_core.custom_enchants.utilities.MenuUtility;
import pw.xwy.prison_core.utility.CustomItem;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
	
	private static Inventory MainMenu = Bukkit.createInventory(null, 54, "§c§lCustom Enchants");
	
	static {
		
		List<String> desc = new ArrayList<String>();
		
		for (int i = 0; i < 54; i++) {
			desc.clear();
			ItemStack topPane = MenuUtility.getItem(" ", Material.STAINED_GLASS_PANE, desc);
			topPane.setDurability((short) 7);
			MainMenu.setItem(i, topPane);
		}
		
		desc.clear();
		ItemStack Helm = MainUtility.getItem("§5Helmet Enchantments", Material.DIAMOND_HELMET, desc, true);
		ItemStack Chest = MainUtility.getItem("§5Chestplate Enchantments", Material.DIAMOND_CHESTPLATE, desc, true);
		ItemStack Leggings = MainUtility.getItem("§5Leggings Enchantments", Material.DIAMOND_LEGGINGS, desc, true);
		ItemStack Boots = MainUtility.getItem("§5Boots Enchantments", Material.DIAMOND_BOOTS, desc, true);
		
		ItemStack Bow = MainUtility.getItem("§5Bow Enchantments", Material.BOW, desc, true);
		ItemStack Pick = MainUtility.getItem("§5Pickaxe Enchantments", Material.DIAMOND_PICKAXE, desc, true);
		ItemStack Sword = MainUtility.getItem("§5Sword Enchantments", Material.DIAMOND_SWORD, desc, true);
		ItemStack Axe = MainUtility.getItem("§5Axe Enchantments", Material.DIAMOND_AXE, desc, true);
		
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
	
	public static Inventory get() {
		
		return MainMenu;
	}
	
}
