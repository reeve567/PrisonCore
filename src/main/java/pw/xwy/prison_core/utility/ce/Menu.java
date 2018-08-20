////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 7:55 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility.ce;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.MenuItem;
import pw.xwy.prison_core.utility.enums.Rarity;

import java.util.ArrayList;

import static pw.xwy.prison_core.utility.ce.MenuUtility.getItem;
import static pw.xwy.prison_core.utility.ce.MenuUtility.setItem;

public class Menu {
	
	protected Inventory inventory = Bukkit.createInventory(null, 45, "§c§lCustom Enchants");
	
	Menu(ItemSets type) {
		for (int i = 0; i < 45; i++) {
			ItemStack topPane = getItem(" ", Material.STAINED_GLASS_PANE, new ArrayList<>());
			topPane.setDurability((short) 7);
			inventory.setItem(i, topPane);
		}
		setItem(Rarity.HYDRO.getLabel(), Material.BOOKSHELF, new ArrayList<>(), 0, inventory);
		setItem(Rarity.MYSTICAL.getLabel(), Material.BOOKSHELF, new ArrayList<>(), 9, inventory);
		setItem(Rarity.RARE.getLabel(), Material.BOOKSHELF, new ArrayList<>(), 18, inventory);
		setItem(Rarity.UNCOMMON.getLabel(), Material.BOOKSHELF, new ArrayList<>(), 27, inventory);
		setItem(Rarity.COMMON.getLabel(), Material.BOOKSHELF, new ArrayList<>(), 36, inventory);
		setItem("§9Back", Material.WOOL, 14, new ArrayList<>(), 44, inventory);
		int nextHydro = 1;
		int nextMystical = 10;
		int nextRare = 19;
		int nextUncommon = 28;
		int nextCommon = 37;
		for (MenuItem mi : MenuItem.values()) {
			if (mi.getCustomEnchant().isEnabled() && mi.getCustomEnchant().containsSet(type)) {
				if (mi.getCustomEnchant().getRarity().equals(Rarity.HYDRO)) {
					setItem(mi.getCustomEnchant().getName(), mi.getType(), mi.getDurability(), mi.getDesc(), nextHydro++, inventory);
				} else if (mi.getCustomEnchant().getRarity().equals(Rarity.MYSTICAL)) {
					setItem(mi.getCustomEnchant().getName(), mi.getType(), mi.getDurability(), mi.getDesc(), nextMystical++, inventory);
				} else if (mi.getCustomEnchant().getRarity().equals(Rarity.RARE)) {
					setItem(mi.getCustomEnchant().getName(), mi.getType(), mi.getDurability(), mi.getDesc(), nextRare++, inventory);
				} else if (mi.getCustomEnchant().getRarity().equals(Rarity.UNCOMMON)) {
					setItem(mi.getCustomEnchant().getName(), mi.getType(), mi.getDurability(), mi.getDesc(), nextUncommon++, inventory);
				} else if (mi.getCustomEnchant().getRarity().equals(Rarity.COMMON)) {
					setItem(mi.getCustomEnchant().getName(), mi.getType(), mi.getDurability(), mi.getDesc(), nextCommon++, inventory);
				}
			}
		}
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
}
