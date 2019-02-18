package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.CustomBowEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Voltage extends CustomBowEnchant {
	public Voltage(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Voltage(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void launch(ProjectileLaunchEvent e) {
		e.getEntity().setMetadata("Voltage", new FixedMetadataValue(PrisonCore.getInstance(), ((Player) e.getEntity().getShooter()).getName()));
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {
		int num = EnchantDrop.getRandomNumberFrom(1, 100);
		if (num <= 20) {
			e.getEntity().getLocation().getWorld().strikeLightning(e.getEntity().getLocation());
			e.getDamager().sendMessage(Messages.smited.get());
		}
	}
}
