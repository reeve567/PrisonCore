package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public abstract class CustomBowEnchant extends CustomDamageEnchant {
	public CustomBowEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public CustomBowEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	public abstract void launch(ProjectileLaunchEvent e);
}
