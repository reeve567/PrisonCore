package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDeathEvent;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public abstract class CustomDeathEnchant extends CustomEnchant {

	public CustomDeathEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	public CustomDeathEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability) {
		super(name, sets, rarity, description, displayItem, durability);
	}

	public abstract void event(EntityDeathEvent e);
}
