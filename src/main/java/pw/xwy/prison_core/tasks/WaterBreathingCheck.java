package pw.xwy.prison_core.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterBreathingCheck implements Runnable {

	private JavaPlugin main;

	public WaterBreathingCheck(JavaPlugin main) {
		this.main = main;
		run();
	}

	@Override
	public void run() {

		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
			if (Bukkit.getOnlinePlayers().size() > 0) {
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
