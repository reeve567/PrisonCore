////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.utilities;
// made by reeve
// on 12:16 AM

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.listeners.EnchantDrop;

import java.util.ArrayList;
import java.util.List;

public class MainUtility {
	
	public static ItemStack bookGive(String n, boolean perfect) {
		
		List<String> desc = new ArrayList<>();
		if (perfect) {
			desc.add("");
			desc.add("§aSuccess: §f<number>%".replaceAll("<number>", "" + EnchantDrop.getRandomNumberFrom(100, 100)));
			desc.add("§cDestroy: §f<number>%".replaceAll("<number>", "" + EnchantDrop.getRandomNumberFrom(0, 0)));
			desc.add("");
		} else {
			desc.add("");
			desc.add("§aSuccess: §f<number>%".replaceAll("<number>", "" + EnchantDrop.getRandomNumberFrom(1, 100)));
			desc.add("§cDestroy: §f<number>%".replaceAll("<number>", "" + EnchantDrop.getRandomNumberFrom(0, 100)));
			desc.add("");
		}
		
		for (CEnchant e : CEnchant.values()) {
			if (cmdCheck(e.getLabel(), n) || cmdCheck(e.getName(), n)) {
				if (n.equalsIgnoreCase("Xwy")) {
					desc.clear();
					desc.add("");
					desc.add("§aSuccess: §f<number>%".replaceAll("<number>", "100"));
					desc.add("§cDestroy: §f<number>%".replaceAll("<number>", "0"));
					desc.add("");
					desc.add(CEnchant.XWY.getRarity().getLabel());
					desc.add("");
					desc.add("§fBoots");
					return getItem(CEnchant.XWY.getName(), desc);
				}
				desc.add(e.getRarity().getLabel());
				desc.add("");
				desc.add(ChatColor.WHITE + e.getLoreLbl() + " Enchant");
				return getItem(e.getName(), desc);
			}
		}
		return new ItemStack(Material.BOOK, 1);
	}
	
	public static boolean cmdCheck(String c, String s) {
		
		return s.equalsIgnoreCase(c);
	}
	
	public static ItemStack getItem(String name, List<String> desc) {
		
		ItemStack item = new ItemStack(Material.BOOK, 1);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.setLore(desc);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack getItem(String name, Material mat, List<String> desc) {
		
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.setLore(desc);
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
}
