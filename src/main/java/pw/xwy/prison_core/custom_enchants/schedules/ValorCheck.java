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
// on 10:51 PM

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;

public class ValorCheck implements Runnable {
	
	private JavaPlugin main;
	
	public ValorCheck(JavaPlugin main) {
		
		this.main = main;
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
						if (p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
							p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 45, 0));
					}
				}
			}
		}, 0, 40);
	}
	
	boolean ItemCheck(ItemStack i) {
		
		if (i != null && i.hasItemMeta() && i.getItemMeta().hasLore() && i.getItemMeta().getLore().contains(CEnchant.VALOR.getName())) {
			return true;
		}
		return false;
	}
}
