package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pw.xwy.prison_core.utility.CustomDamageEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class GuardiansBlock extends CustomDamageEnchant {
	public GuardiansBlock(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public GuardiansBlock(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		if (((Player) e.getEntity()).isBlocking())
			e.setDamage(e.getDamage() / 2);
	}
}
