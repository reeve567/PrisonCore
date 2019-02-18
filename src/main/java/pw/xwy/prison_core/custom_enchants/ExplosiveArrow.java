package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomBowEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class ExplosiveArrow extends CustomBowEnchant {
	public ExplosiveArrow(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public ExplosiveArrow(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void launch(ProjectileLaunchEvent e) {
		e.getEntity().setMetadata("Explosive", new FixedMetadataValue(PrisonCore.getInstance(), ((Player) e.getEntity().getShooter()).getName()));
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow && ((Arrow) e.getDamager()).getShooter() instanceof Player) {
			Arrow arrow = (Arrow) e.getDamager();
			Player p = (Player) arrow.getShooter();
			if (arrow.hasMetadata("Explosive")) {
				int num = EnchantDrop.getRandomNumberFrom(1, 100);
				if (num <= 70) {
					e.setDamage(e.getDamage() * 2);
				}
			}
		}
	}
}
