////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.tasks;
// made by reeve
// on 10:36 PM

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.RealName;
import pw.xwy.prison_core.utility.CustomTaskEnchant;

public class JumpBoostCheck implements Runnable {
	private JavaPlugin main;

	public JumpBoostCheck(JavaPlugin main) {
		this.main = main;
		run();
	}

	@Override
	public void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
			if (Bukkit.getOnlinePlayers().size() > 0) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (ItemCheck(p.getInventory().getBoots())) {
						((CustomTaskEnchant) RealName.MOONGRAVITY.getEnchant()).event(p);
					}
				}
			}
		}, 0, 35);
	}

	private boolean ItemCheck(ItemStack i) {
		return i != null && i.hasItemMeta() && i.getItemMeta().hasLore() && i.getItemMeta().getLore().contains(RealName.MOONGRAVITY.getEnchant().getName());
	}

}
