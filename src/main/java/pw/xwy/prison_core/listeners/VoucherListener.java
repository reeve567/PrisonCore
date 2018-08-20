package pw.xwy.prison_core.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.player.XPlayerData;

public class VoucherListener implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if (e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getType() == Material.PAPER && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Bank Note")) {
				XPlayerData XPlayerData = PlayerManager.getXPlayer(e.getPlayer()).getData();
				XPlayerData.addBalance(Double.parseDouble(e.getItem().getItemMeta().getLore().get(0).substring(5)));
				if (e.getItem().getAmount() > 1) {
					e.getPlayer().setItemInHand(new CustomItem(e.getPlayer().getItemInHand()).setCustomAmount(e.getItem().getAmount() - 1));
				} else {
					e.getPlayer().setItemInHand(new CustomItem(e.getPlayer().getItemInHand()).setCusomType(Material.AIR));
					
				}
				
				
			}
		}
	}
	
}
