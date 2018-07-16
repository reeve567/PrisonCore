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
// on 6:20 PM

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.enums.Souls;
import pw.xwy.prison_core.custom_enchants.soulcrates.Prize;

import java.util.ArrayList;

public class CratesUtility {
	
	public static void openCrate(Player p, ItemStack key) {
		for (Souls s : Souls.values()) {
			if (s.getItem().getItemMeta().getDisplayName().equals(key.getItemMeta().getDisplayName())) {
			
			}
		}
		
		
	}
	
	
	public static ArrayList<ItemStack> movePrizes(ArrayList<ItemStack> items, ArrayList<Prize> prizes, int putItems) {
		
		items.remove(0);
		if (putItems - 1 < prizes.size()) {
			items.add(prizes.get(putItems - 1).getDisplayItem());
		}
		return items;
	}
	
	public static void putPrize(Inventory inv, int slot, int putItems, ArrayList<Prize> prizes) {
		inv.setItem(slot, prizes.get(putItems - 1).getDisplayItem());
	}
	
	
}
