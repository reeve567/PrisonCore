////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility.ce;
// made by reeve
// on 12:16 AM

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomEnchant;

import java.util.ArrayList;
import java.util.List;

public class EnchantUtility {

	public static ItemStack bookGive(String n, boolean perfect) {

		List<String> desc = new ArrayList<>();
		if (perfect) {
			desc.add("");
			desc.add("§aSuccess: §f<number>%".replaceAll("<number>", "100"));
			desc.add("§cDestroy: §f<number>%".replaceAll("<number>", "0"));
			desc.add("");
		} else {
			desc.add("");
			desc.add("§aSuccess: §f<number>%".replaceAll("<number>", "" + EnchantDrop.getRandomNumberFrom(1, 100)));
			desc.add("§cDestroy: §f<number>%".replaceAll("<number>", "" + EnchantDrop.getRandomNumberFrom(0, 100)));
			desc.add("");
		}

		CustomEnchant customEnchant = CustomEnchantsManager.manager.getEnchantsByLabel().get(n);

		if (customEnchant != null) {
			desc.add(customEnchant.getRarity().getLabel());
			desc.add("");
			desc.add(ChatColor.WHITE + customEnchant.getSets().getName() + " Enchant");
			return getItem(customEnchant.getName(), desc);
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
