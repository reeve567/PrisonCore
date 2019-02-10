package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.RealName;
import pw.xwy.prison_core.utility.CustomBowEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;

import static pw.xwy.prison_core.listeners.BowListener.cantShoot;

public class GrapplingBow extends CustomBowEnchant {

	public GrapplingBow(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public GrapplingBow(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void launch(ProjectileLaunchEvent e) {
		Player player = (Player) e.getEntity().getShooter();


		ItemStack boots = player.getInventory().getBoots();
		if (boots != null && boots.getItemMeta().getLore().contains(RealName.XWY.getEnchant().getName())) {
			//Starting to redo here
			e.getEntity().setMetadata("Grappling", new FixedMetadataValue(PrisonCore.getInstance(), player.getName()));
		} else {
			if (!cantShoot.contains(player.getName())) {
				//And here
				e.getEntity().setMetadata("Grappling", new FixedMetadataValue(PrisonCore.getInstance(), player.getName()));

				cantShoot.add(player.getName());
				Bukkit.getScheduler().runTaskLater(PrisonCore.getInstance(), () -> cantShoot.remove(player.getName()), 300);
			} else {
				player.sendMessage(Messages.fifsecCooldown.get());
				e.setCancelled(true);
				player.getInventory().addItem(new ItemStack(Material.ARROW));
			}
		}
	}

	@Override
	public void event(EntityDamageByEntityEvent e) {

	}
}
