package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.xwy.prison_core.PlayerData;
import pw.xwy.prison_core.PlayerDataManager;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.Rank;

public class JoinListener implements Listener {
	public static int UniquePlayers;
	
	public JoinListener() {
		PrisonCore.registerEvents(this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!PlayerDataManager.getPlayerData().keySet().contains(e.getPlayer().getUniqueId())) {
			PlayerDataManager.addPlayerData(e.getPlayer().getUniqueId(), new PlayerData(e.getPlayer().getUniqueId(),
					ConfigurationHandler.getPlayerBalanceData().getDouble("players." + e.getPlayer().getUniqueId(), PlayerDataManager.startingMoney),
					Rank.valueOf(ConfigurationHandler.getPlayerRankData().getString("players." + e.getPlayer().getUniqueId(), "A")),
					ConfigurationHandler.getPlayerPrestigeData().getInt("players." + e.getPlayer().getUniqueId(), 0)
			));
		}
		
		if (ConfigurationHandler.isUniquePlayer(e.getPlayer())) {
			Bukkit.broadcastMessage("§7Welcome §6" + e.getPlayer().getName() + " §7to the server");
			Bukkit.broadcastMessage("§6" + ++UniquePlayers + " §7unique players have joined");
			ConfigurationHandler.addPlayerToUnique(e.getPlayer());
		}
		e.getPlayer().sendMessage("§7Welcome to §6Mud§6kip §8Prison§7!");
	}
	
}
