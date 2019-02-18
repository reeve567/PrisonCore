package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.utility.CustomTaskEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Glowing extends CustomTaskEnchant {
	public Glowing(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	public Glowing(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability) {
		super(name, sets, rarity, description, displayItem, durability);
	}

	@Override
	public void event(Player p) {
		if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0));
	}
}
