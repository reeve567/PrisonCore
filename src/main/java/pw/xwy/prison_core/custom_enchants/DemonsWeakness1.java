package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class DemonsWeakness1 extends CustomEnchant {

	public DemonsWeakness1() {
		super("Demon's Weakness I", ItemSets.SWORDAXE, Rarity.HYDRO, "Has a chance to give the opponent++weakness for 2 seconds.", Material.EYE_OF_ENDER);
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		//Make sure it's players being damaged
		if (!(e.getDamager() instanceof Player && e.getEntity() instanceof Player)) {
			return;
		}

		int r = EnchantDrop.getRandomNumberFrom(1, 100);
		if (r < 85) {
			((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 0));
		}
	}
}
