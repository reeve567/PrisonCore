package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public abstract class CustomTaskEnchant extends CustomEnchant {

	public CustomTaskEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	public CustomTaskEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability) {
		super(name, sets, rarity, description, displayItem, durability);
	}

	public abstract void event(Player player);

}
