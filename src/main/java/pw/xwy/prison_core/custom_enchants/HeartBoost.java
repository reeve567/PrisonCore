package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.CustomTaskEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class HeartBoost extends CustomTaskEnchant {
	public HeartBoost(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	public HeartBoost(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability) {
		super(name, sets, rarity, description, displayItem, durability);
	}

	@Override
	public void event(Player player) {
		player.setMaxHealth(28);
	}
}
