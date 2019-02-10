package pw.xwy.prison_core.listeners;

import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.misc_managers.CrateManager;

public class VoteListener implements Listener {

	@EventHandler
	public void voteMade(VotifierEvent e) {
		PrisonCore.log(e.getVote().getUsername() + " voted on " + e.getVote().getServiceName() + " -- " + e.getVote().getAddress(), 2);
		Player player = Bukkit.getPlayer(e.getVote().getUsername());
		if (player != null && player.isOnline()) {
			Bukkit.broadcastMessage("§6§lSystem §8§l»» §6" + e.getVote().getUsername() + " §7has voted on " + e.getVote().getServiceName());
			player.getInventory().addItem(CrateManager.getKey(CrateManager.CrateType.VOTE));
			player.getInventory().addItem(new CustomItem(Material.COOKED_BEEF).setCustomAmount(16));
		}
	}

}
