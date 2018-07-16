////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:51 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.soulcrates;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pw.xwy.prison_core.custom_enchants.utilities.Resources;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Crate {
	
	protected static ArrayList<Prize> prizes = new ArrayList<>();
	private static Crate crate;
	Resources resources = new Resources();
	Inventory baseInventory;
	private HashMap<Player, Inventory> inventories = new HashMap<>();
	
	Crate(Inventory inv) {
		crate = this;
		baseInventory = inv;
	}
	
	public static Crate get() {
		return crate;
	}
	
	public final Inventory getBaseInventory() {
		return baseInventory;
	}
	
	public final ArrayList<Prize> getPrizes() {
		return prizes;
	}
	
	final Inventory getRunningInventory(Player p) {
		return inventories.get(p);
	}
	
	public final boolean isRunningInventory(Player p) {
		return inventories.containsKey(p);
	}
	
	public final void remRunningInventory(Player p) {
		inventories.remove(p);
	}
	
	final void setRunningInventory(Player p, Inventory inv) {
		inventories.put(p, inv);
	}
}
