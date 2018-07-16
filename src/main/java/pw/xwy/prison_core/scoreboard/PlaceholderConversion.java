package pw.xwy.prison_core.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.PlayerDataManager;

public class PlaceholderConversion {
	
	public static String convert(Player p, String s) {
		
		s = s.replaceAll("<players-online>", String.valueOf(Bukkit.getOnlinePlayers().length));
		s = s.replaceAll("<player-username>", p.getName());
		s = s.replaceAll("<player-displayname>", p.getDisplayName());
		s = s.replaceAll("<player-level>", String.valueOf(p.getLevel()));
		s = s.replaceAll("<player-health", String.valueOf(p.getHealth()));
		s = s.replaceAll("<player-max-health>", String.valueOf(p.getMaxHealth()));
		s = s.replaceAll("<player-hunger>", String.valueOf(p.getFoodLevel()));
		s = s.replaceAll("<player-balance>", String.valueOf(PlayerDataManager.getPlayerData(p.getUniqueId()).getBalance()));
		s = s.replaceAll("<player-mine-rank>", String.valueOf(PlayerDataManager.getPlayerData(p.getUniqueId()).getRank()));
		
		return s;
	}
	
}
