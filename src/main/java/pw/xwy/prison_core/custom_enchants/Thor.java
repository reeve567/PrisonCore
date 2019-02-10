package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pw.xwy.prison_core.listeners.DamageListener;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomDamageEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Thor extends CustomDamageEnchant {

	public Thor(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		if (EnchantDrop.getRandomNumberFrom(1, 100) <= 10) {
			e.getDamager().getLocation().getWorld().strikeLightning(e.getEntity().getLocation());
			DamageListener.summoner = (Player) e.getDamager();
		}
	}
}
