package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent;
import pw.xwy.prison_core.utility.CustomEnviromentalDamageEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Xwy extends CustomEnviromentalDamageEnchant {

	public Xwy(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	@Override
	public void event(EntityDamageEvent e) {
		e.setCancelled(true);
	}
}
