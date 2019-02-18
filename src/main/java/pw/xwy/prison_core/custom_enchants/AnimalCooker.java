package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomDeathEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class AnimalCooker extends CustomDeathEnchant {
	public AnimalCooker(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public AnimalCooker(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void event(EntityDeathEvent e) {
		changeDrops(e);
	}

	private void changeDrops(EntityDeathEvent e) {

		for (ItemStack it : e.getDrops()) {
			if (it.getType().equals(Material.RAW_BEEF)) {
				it.setType(Material.COOKED_BEEF);
			} else if (it.getType().equals(Material.PORK)) {
				it.setType(Material.GRILLED_PORK);
			} else if (it.getType().equals(Material.RAW_CHICKEN)) {
				it.setType(Material.COOKED_CHICKEN);
			}
		}
	}
}
