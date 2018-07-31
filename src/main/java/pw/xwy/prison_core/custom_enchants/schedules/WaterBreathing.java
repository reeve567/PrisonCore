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
// on 10:14 PM

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;

public class WaterBreathing implements Runnable {
	
	private JavaPlugin main;
	
	public WaterBreathing(JavaPlugin main) {
		this.main = main;
		run();
	}
	
	@Override
	public void run() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
			if (Bukkit.getOnlinePlayers().length > 0) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (ItemCheck(p.getInventory().getHelmet())) {
						if (p.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
							p.removePotionEffect(PotionEffectType.WATER_BREATHING);
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 45, 0));
					}
				}
			}
		}, 0, 40);
	}
	
	boolean ItemCheck(ItemStack i) {
		if (i != null && i.hasItemMeta() && i.getItemMeta().hasLore() && i.getItemMeta().getLore().contains(CEnchant.SCUBADIVER.getName())) {
			return true;
		}
		return false;
	}
}
