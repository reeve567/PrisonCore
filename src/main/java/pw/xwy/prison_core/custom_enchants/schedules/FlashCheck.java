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
// on 11:43 PM

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;

public class FlashCheck implements Runnable {
	
	private JavaPlugin main;
	
	public FlashCheck(JavaPlugin main) {
		
		this.main = main;
	}
	
	@Override
	public void run() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			@Override
			public void run() {
				
				if (Bukkit.getOnlinePlayers().length > 0) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (ItemCheck(p.getInventory().getHelmet()) &&
								ItemCheck(p.getInventory().getChestplate()) &&
								ItemCheck(p.getInventory().getLeggings()) &&
								ItemCheck(p.getInventory().getBoots())) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0));
						}
					}
				}
			}
		}, 20L, 0);
	}
	
	boolean ItemCheck(ItemStack i) {
		
		if (i != null && i.hasItemMeta() && i.getItemMeta().hasLore() && i.getItemMeta().getLore().contains(CEnchant.VALOR.getName())) {
			return true;
		}
		return false;
	}
	
}
