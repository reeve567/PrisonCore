package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.CustomBowDeathEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Furnace extends CustomBowDeathEnchant {
	public Furnace(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Furnace(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void event(EntityDeathEvent e) {
		Entity killed = e.getEntity();
		EntityDamageEvent ev_killed = killed.getLastDamageCause(); // We need to find the last cause of damage before the death.
		if (ev_killed instanceof EntityDamageByEntityEvent) { // If it is a Entity, who killed him, the Event can be casted.
			EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) ev_killed;
			if (event.getDamager().hasMetadata("Furnace")) {
				changeDrops(e);
			}
		}
	}


	@Override
	public void launch(ProjectileLaunchEvent e) {
		e.getEntity().setMetadata("Furnace", new FixedMetadataValue(PrisonCore.getInstance(), ((Player) e.getEntity().getShooter()).getName()));
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
