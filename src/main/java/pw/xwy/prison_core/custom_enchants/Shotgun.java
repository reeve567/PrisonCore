package pw.xwy.prison_core.custom_enchants;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import pw.xwy.prison_core.utility.CustomBowShootEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Shotgun extends CustomBowShootEnchant {
	public Shotgun(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Shotgun(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void event(EntityShootBowEvent e) {
		ItemStack i = e.getBow();
		if (i.getItemMeta().getLore().contains(ChatColor.GRAY + "Mode: Default")) {
			if (((Player) e.getEntity()).getInventory().contains(Material.ARROW, 4)) {

				e.getProjectile().remove();
				((Player) e.getEntity()).getInventory().addItem(new ItemStack(Material.ARROW, 1));

				e.getBow().setDurability((short) (e.getBow().getDurability() - 1));
				for (int r = 1; r <= 4; r++) {
					((Player) e.getEntity()).getInventory().removeItem(new ItemStack(Material.ARROW, 1));
					Arrow arrow = e.getEntity().launchProjectile(Arrow.class);
					arrow.setBounce(false);
					Vector v = e.getEntity().getLocation().getDirection();
					v.add(new Vector(Vec(), 0, Vec()));
					arrow.setVelocity(v);
					((Player) e.getEntity()).updateInventory();
				}
			} else {
				e.setCancelled(true);
				e.getEntity().sendMessage(Messages.noAmmo.get());
				((Player) e.getEntity()).updateInventory();
			}
		}
	}

	private float Vec() {

		float spread = (float) .2;
		return -spread + (float) (Math.random() * ((spread + spread)));
	}
}
