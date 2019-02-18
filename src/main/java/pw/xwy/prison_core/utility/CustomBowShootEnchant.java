package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityShootBowEvent;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public abstract class CustomBowShootEnchant extends CustomEnchant {
	public CustomBowShootEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public CustomBowShootEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	public abstract void event(EntityShootBowEvent e);
}
