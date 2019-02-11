package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomDamageEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class BattleRoar extends CustomDamageEnchant {
	public BattleRoar(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public BattleRoar(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			int g = EnchantDrop.getRandomNumberFrom(1, 100);
			if (g <= 20) {
				int r = EnchantDrop.getRandomNumberFrom(1, 100);
				if (r <= 50) {
					if (((Player) e.getEntity()).hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
						((Player) e.getEntity()).removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
				} else {
					if (((Player) e.getEntity()).hasPotionEffect(PotionEffectType.WITHER))
						((Player) e.getEntity()).removePotionEffect(PotionEffectType.WITHER);
					((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 0));
				}
			}
		}
	}
}
