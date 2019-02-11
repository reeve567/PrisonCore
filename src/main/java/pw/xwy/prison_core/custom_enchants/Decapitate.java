package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomDeathEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;
import pw.xwy.prison_core.utility.item.PlayerSkull;

import java.util.List;

public class Decapitate extends CustomDeathEnchant {

	public Decapitate(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Decapitate(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	// TODO: Setup for entities, and add chance
	public void event(EntityDeathEvent e) {

		if (e.getEntity() instanceof Player) {
			List<ItemStack> drops = e.getDrops();
			drops.add(new PlayerSkull((Player) e.getEntity()));
		}
	}
}
