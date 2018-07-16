////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.schedules;
// made by reeve
// on 10:36 PM

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;

public class Speed implements Runnable {
	
	private JavaPlugin main;
	
	public Speed(JavaPlugin main) {
		
		this.main = main;
		run();
	}
	
	@Override
	public void run() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
			
			if (Bukkit.getOnlinePlayers().length > 0) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (ItemCheck(p.getInventory().getBoots()) == 1) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0));
					} else if (ItemCheck(p.getInventory().getBoots()) == 2) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
					}
				}
			}
		}, 20L, 0);
	}
	
	int ItemCheck(ItemStack i) {
		
		if (i != null && i.hasItemMeta() && i.getItemMeta().hasLore() && i.getItemMeta().getLore().contains(CEnchant.WINDSSPEEDI.getName())) {
			return 1;
		} else if (i != null && i.hasItemMeta() && i.getItemMeta().hasLore() && i.getItemMeta().getLore().contains(CEnchant.WINDSSPEEDII.getName())) {
			return 2;
		}
		return 0;
	}
	
}