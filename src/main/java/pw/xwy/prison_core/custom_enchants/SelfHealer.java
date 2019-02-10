package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.utility.CustomDamageEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class SelfHealer extends CustomDamageEnchant {

	public SelfHealer(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		if (((Player) e.getEntity()).getHealth() <= 5) {
			((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 0));
		}
	}
}
