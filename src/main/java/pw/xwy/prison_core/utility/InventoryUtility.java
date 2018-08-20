////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 6/17/18 12:18 PM                            /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtility {
	
	public static Inventory setBackground(Inventory inventory, ItemStack stack) {
		for (int i = 0; i < inventory.getSize(); i++)
			inventory.setItem(i, stack);
		return inventory;
	}
	
}
