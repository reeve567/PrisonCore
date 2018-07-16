////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.enums;
// made by reeve
// on 6:58 PM


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public enum Souls {
	COMMON(Rarity.COMMON, "CommonKey"),
	UNCOMMON(Rarity.UNCOMMON, "UncommonKey"),
	RARE(Rarity.RARE, "RareKey"),
	MYSTICAL(Rarity.MYSTICAL, "MysticalKey"),
	HYDRO(Rarity.HYDRO, "HydroKey");
	
	private final String name;
	private final String commandLabel;
	private final ItemStack item;
	
	Souls(Rarity name, String commandLabel) {
		this.name = name.getLabel() + " Key";
		this.commandLabel = commandLabel;
		
		ItemStack it = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = it.getItemMeta();
		im.setDisplayName(this.name);
		ArrayList<String> lores = new ArrayList<>();
		lores.add(ChatColor.GRAY + "Redeem this from Rubix for a prize.");
		im.setLore(lores);
		im.addEnchant(Enchantment.getByName("Glow"), 1, true);
		it.setItemMeta(im);
		this.item = it;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCommandLabel() {
		return commandLabel;
	}
	
	public ItemStack getItem() {
		return item;
	}
}
