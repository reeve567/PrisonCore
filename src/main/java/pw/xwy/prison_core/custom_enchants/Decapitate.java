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

	public Decapitate() {
		super("Decapitate", ItemSets.SWORDAXE, Rarity.RARE, "This enchant ensures you salvage++an enemy player's head.", Material.SKULL_ITEM, 3);
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
