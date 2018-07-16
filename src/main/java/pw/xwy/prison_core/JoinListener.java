package pw.xwy.prison_core;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.ERank;

public class JoinListener implements Listener {
	
	public JoinListener() {
		PrisonCore.registerEvents(this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!PlayerDataManager.getPlayerData().keySet().contains(e.getPlayer().getUniqueId())) {
			PlayerDataManager.addPlayerData(e.getPlayer().getUniqueId(), new PlayerData(e.getPlayer().getUniqueId(),
					ConfigurationHandler.getPlayerBalanceData().getDouble("players." + e.getPlayer().getUniqueId(), PlayerDataManager.startingMoney),
					ERank.valueOf(ConfigurationHandler.getPlayerRankData().getString("players." + e.getPlayer().getUniqueId(), "A")),
					ConfigurationHandler.getPlayerPrestigeData().getInt("players." + e.getPlayer().getUniqueId(), 0)
			));
		}
		
		
	}
	
}
