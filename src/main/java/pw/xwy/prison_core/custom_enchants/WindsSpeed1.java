package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.utility.CustomTaskEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class WindsSpeed1 extends CustomTaskEnchant {
	public WindsSpeed1(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	public WindsSpeed1(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability) {
		super(name, sets, rarity, description, displayItem, durability);
	}

	@Override
	public void event(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 45, 0));
		if (player.hasPotionEffect(PotionEffectType.SPEED)) {
			player.removePotionEffect(PotionEffectType.SPEED);
		}
	}
}
