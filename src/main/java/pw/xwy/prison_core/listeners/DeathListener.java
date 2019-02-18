////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.listeners;
// made by reeve
// on 8:00 PM

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.RealName;
import pw.xwy.prison_core.utility.CustomBowDeathEnchant;
import pw.xwy.prison_core.utility.CustomDeathEnchant;

import java.util.HashMap;
import java.util.List;

public class DeathListener implements Listener {

	static HashMap<Player, List<ItemStack>> keptItems = new HashMap<>();

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {

		Player p = e.getEntity();
		Player k = p.getKiller();
		if (k != null) {
			if (k.getItemInHand().hasItemMeta()) {
				if (k.getItemInHand().getItemMeta().hasLore()) {
					for (String s : k.getItemInHand().getItemMeta().getLore()) {
						if (s.equalsIgnoreCase(RealName.DECAPITATE.getEnchant().getName())) {
							((CustomDeathEnchant) RealName.DECAPITATE.getEnchant()).event(e);
						}
					}
				}
			}
		}

		((CustomDeathEnchant) RealName.SOULBOUND.getEnchant()).event(e);

	}

	@EventHandler
	public void entityKill(EntityDeathEvent e) {

		if (e.getEntity() != null && !(e.getEntity() instanceof Player) && e.getEntity().getKiller() != null) {
			Player p = e.getEntity().getKiller();
			ItemStack i = p.getItemInHand();

			if (i != null && i.hasItemMeta() && i.getItemMeta().hasLore()) {
				if (i.getItemMeta().getLore().contains(RealName.MOBSLAYERI.getEnchant().getName()))
					((CustomDeathEnchant) RealName.MOBSLAYERI.getEnchant()).event(e);
				else if (i.getItemMeta().getLore().contains(RealName.MOBSLAYERII.getEnchant().getName()))
					((CustomDeathEnchant) RealName.MOBSLAYERI.getEnchant()).event(e);
				else if (i.getItemMeta().getLore().contains(RealName.MOBSLAYERIII.getEnchant().getName()))
					((CustomDeathEnchant) RealName.MOBSLAYERI.getEnchant()).event(e);
				if (i.getItemMeta().getLore().contains(RealName.ANIMALCOOKER.getEnchant().getName()))
					((CustomDeathEnchant) RealName.ANIMALCOOKER.getEnchant()).event(e);
			}
			((CustomBowDeathEnchant) RealName.FURNACE.getEnchant()).event(e);
		}
	}

}