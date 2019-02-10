package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent;
import pw.xwy.prison_core.utility.CustomEnviromentalDamageEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Guardian extends CustomEnviromentalDamageEnchant {
	public Guardian(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Guardian(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void event(EntityDamageEvent e) {
		e.setCancelled(true);
	}
}
