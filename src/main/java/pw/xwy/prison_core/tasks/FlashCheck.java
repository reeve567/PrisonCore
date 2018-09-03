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
// on 11:43 PM

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.utility.enums.CEnchant;

public class FlashCheck implements Runnable {
	
	private JavaPlugin main;
	
	public FlashCheck(JavaPlugin main) {
		this.main = main;
		run();
	}
	
	@Override
	public void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
			if (Bukkit.getOnlinePlayers().size() > 0) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (ItemCheck(p.getInventory().getHelmet()) &&
							ItemCheck(p.getInventory().getChestplate()) &&
							ItemCheck(p.getInventory().getLeggings()) &&
							ItemCheck(p.getInventory().getBoots())) {
						if (p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
							p.removePotionEffect(PotionEffectType.FAST_DIGGING);
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 45, 0));
					}
				}
			}
		}, 0, 35);
	}
	
	private boolean ItemCheck(ItemStack i) {
		return i != null && i.hasItemMeta() && i.getItemMeta().hasLore() && i.getItemMeta().getLore().contains(CEnchant.FLASH.getName());
	}
	
}
