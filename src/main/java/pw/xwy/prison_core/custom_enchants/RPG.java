package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import pw.xwy.prison_core.listeners.DamageListener;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomBowEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class RPG extends CustomBowEnchant {
	public RPG(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public RPG(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void launch(ProjectileLaunchEvent e) {

	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow && ((Arrow) e.getDamager()).getShooter() instanceof Player) {
			Arrow arrow = (Arrow) e.getDamager();
			if (arrow.hasMetadata("RPG")) {
				int num = EnchantDrop.getRandomNumberFrom(1, 100);
				if (num <= 15) {
					DamageListener.playerMadeExplosion = true;
					e.getEntity().getLocation().getWorld().createExplosion(e.getEntity().getLocation(), 5);
				}
			}
		}
	}
}
