package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import pw.xwy.prison_core.events.BlockMinedEvent;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public abstract class CustomMineEnchant extends CustomEnchant {
	public CustomMineEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public CustomMineEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	public abstract void event(BlockMinedEvent e);
}
