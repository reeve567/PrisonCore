package pw.xwy.prison_core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import pw.xwy.prison_core.utility.*;

import static pw.xwy.prison_core.utility.SellUtility.sell;

public class SignListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getType().equals(Material.SIGN) || e.getClickedBlock().getType().equals(Material.SIGN_POST)) {
				if (e.getClickedBlock().getState() instanceof Sign) {
					Sign sign = (Sign) e.getClickedBlock().getState();
					if (sign.getLine(0).equalsIgnoreCase("§lSell Sign")) {
						String sRank = sign.getLine(1).toUpperCase();
						if (MineManager.isNormalMine(sRank)) {
							Rank rank = Rank.valueOf(ChatColor.stripColor(sign.getLine(1)));
							sell(e.getPlayer(),rank);
						} else if (MineManager.isDonorMine(sRank)) {
							ExtraRank rank = ExtraRank.valueOf(ChatColor.stripColor(sign.getLine(1)));
							sell(e.getPlayer(),rank);
						}
					}
				}
			}
		}
	}
	
	
}
