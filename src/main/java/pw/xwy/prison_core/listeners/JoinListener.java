package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.enums.NormalWarps;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.player.PlayerManager;

public class JoinListener implements Listener {
	public static int UniquePlayers;
	
	public final ItemStack[] starterKit = new ItemStack[]{
			new CustomItem(Material.BREAD).setCustomAmount(64),
			new CustomItem(Material.IRON_PICKAXE)
	};
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		PlayerManager.getXPlayer(e.getPlayer());
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			e.getPlayer().setAllowFlight(false);
		}
		if (PlayerManager.isUniquePlayer(e.getPlayer())) {
			Bukkit.broadcastMessage("§7Welcome §6" + e.getPlayer().getName() + " §7to the server");
			Bukkit.broadcastMessage("§6" + ++UniquePlayers + " §7unique players have joined");
			e.getPlayer().teleport(NormalWarps.SPAWN.getLocation());
			//TODO STARTER KIT
			for (ItemStack itemStack : starterKit) {
				e.getPlayer().getInventory().addItem(itemStack);
			}
		}
		e.getPlayer().sendMessage("§7Welcome to §6Mud§6kip §8Prison§7!");
	}
	
}
