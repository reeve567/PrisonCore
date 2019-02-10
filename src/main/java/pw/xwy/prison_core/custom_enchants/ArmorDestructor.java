package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomDamageEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class ArmorDestructor extends CustomDamageEnchant {

	public ArmorDestructor(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player && e.getEntity() instanceof Player)) {
			return;
		}

		int r = EnchantDrop.getRandomNumberFrom(1, 100);
		if (r <= 75) {
			Player player = (Player) e.getEntity();
			ItemStack boots = player.getInventory().getBoots();
			ItemStack legs = player.getInventory().getLeggings();
			ItemStack chest = player.getInventory().getChestplate();
			ItemStack helm = player.getInventory().getHelmet();

			if (boots != null) {
				boots.setDurability((short) (boots.getDurability() + 1));
			}
			if (legs != null) {
				legs.setDurability((short) (legs.getDurability() + 1));
			}
			if (chest != null) {
				chest.setDurability((short) (chest.getDurability() + 1));
			}
			if (helm != null) {
				helm.setDurability((short) (helm.getDurability() + 1));
			}

		}
	}
}
