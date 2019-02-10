package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public abstract class CustomBlockEnchant extends CustomEnchant {
	public CustomBlockEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	public CustomBlockEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability) {
		super(name, sets, rarity, description, displayItem, durability);
	}

	public abstract void event(BlockBreakEvent e);

}
