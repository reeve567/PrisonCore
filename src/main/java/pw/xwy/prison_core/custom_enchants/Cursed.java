package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomDamageEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Cursed extends CustomDamageEnchant {

	public Cursed() {
		super("Cursed", ItemSets.AXE, Rarity.HYDRO, "Has a chance to give the opponent++Mining Fatigue for 8 seconds.", Material.WOOD_PICKAXE);
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		int r = EnchantDrop.getRandomNumberFrom(1, 100);
		if (r > 85) {
			if (e.getEntity() instanceof Player) {
				((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 160, 0));
				e.getDamager().sendMessage(Messages.cursed.get());
			}
		}

	}
}
