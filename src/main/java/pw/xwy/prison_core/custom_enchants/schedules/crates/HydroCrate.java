////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.schedules.crates;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.prison_core.custom_enchants.soulcrates.HydroSC;
import pw.xwy.prison_core.custom_enchants.soulcrates.Prize;
import pw.xwy.prison_core.custom_enchants.utilities.CratesUtility;

import java.util.ArrayList;

public class HydroCrate extends BukkitRunnable {
	
	private Player p;
	private ArrayList<Prize> prizes;
	private Inventory inv;
	private int moves = 0;
	private int runs;
	private int rot;
	private boolean log;
	
	public HydroCrate(ArrayList<Prize> prizes, Inventory inv, Player p, int runs, int rot, boolean log) {
		this.p = p;
		this.prizes = prizes;
		this.inv = inv;
		this.runs = runs;
		this.rot = rot;
		this.log = log;
	}
	
	@Override
	public void run() {
		
		moves++;
		
		
		if (!p.isOnline()) {
			if (log) {
				System.out.println(p.getName() + " has left the game during a hydro roll.");
			}
			cancel();
		}
		if (moves >= rot) {
			if (rot == 3) {
				HydroSC.get().remRunningInventory(p);
			}
			cancel();
		}
		
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		
		for (int i = 9; i < 18; i++) {
			items.add(inv.getItem(i));
		}
		
		if (runs - 1 >= prizes.size()) {
			runs = 1;
		}
		
		ArrayList<ItemStack> movedItems = CratesUtility.movePrizes(items, prizes, runs);
		
		for (int i = 9; i < 18; i++) {
			inv.setItem(i, movedItems.get(i - 9));
		}
		
		p.openInventory(inv);
		runs++;
	}
	
	
}
