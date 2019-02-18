package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;

import static pw.xwy.prison_core.listeners.BowListener.cantFire;

public class Rifle extends CustomEnchant {
	public Rifle(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Rifle(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	public void launch(PlayerInteractEvent e) {
		if (e.getPlayer().getInventory().contains(Material.ARROW)) {
			if (!cantFire.contains(e.getPlayer().getName())) {
				cantFire.add(e.getPlayer().getName());
				e.setCancelled(true);
				e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() + 1));
				e.getPlayer().getInventory().removeItem(new ItemStack(Material.ARROW, 1));
				Arrow arrow = e.getPlayer().launchProjectile(Arrow.class);
				arrow.setBounce(false);
				Vector v = e.getPlayer().getLocation().getDirection().multiply(3);
				arrow.setVelocity(v);
				e.getPlayer().updateInventory();

				Bukkit.getScheduler().runTaskLater(PrisonCore.getInstance(), () -> cantFire.remove(e.getPlayer().getName()), 100L);

			} else {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You may only do this every 5 seconds.");
			}
		} else {
			e.getPlayer().sendMessage(Messages.noAmmo.get());
		}
	}
}
