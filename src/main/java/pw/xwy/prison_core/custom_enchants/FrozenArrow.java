package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomBowEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;

public class FrozenArrow extends CustomBowEnchant {
	public FrozenArrow(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public FrozenArrow(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void launch(ProjectileLaunchEvent e) {
		e.getEntity().setMetadata("Freezer", new FixedMetadataValue(PrisonCore.getInstance(), ((Player) e.getEntity().getShooter()).getName()));
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow && ((Arrow) e.getDamager()).getShooter() instanceof Player) {
			Arrow arrow = (Arrow) e.getDamager();
			Player p = (Player) arrow.getShooter();
			if (arrow.hasMetadata("Freezer")) {
				int num = EnchantDrop.getRandomNumberFrom(1, 100);
				if (num <= 20) {
					if (e.getEntity() instanceof Player) {
						Player p1 = (Player) e.getEntity();
						p1.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 3));
						p.sendMessage(Messages.frozen.get());
					}
				}
			}
		}


	}
}
