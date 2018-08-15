package pw.xwy.prison_core.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.*;

public class PlaceholderConversion {
	
	public static String convert(Player p, String s) {
		
		
		XPlayerData data = PlayerManager.getXPlayer(p).getData();
		s = s.replaceAll("<players-online>", String.valueOf(Bukkit.getOnlinePlayers().length));
		s = s.replaceAll("<player-username>", p.getName());
		s = s.replaceAll("<player-displayname>", p.getDisplayName());
		s = s.replaceAll("<player-level>", String.valueOf(p.getLevel()));
		s = s.replaceAll("<player-health", String.valueOf(p.getHealth()));
		s = s.replaceAll("<player-max-health>", String.valueOf(p.getMaxHealth()));
		s = s.replaceAll("<player-hunger>", String.valueOf(p.getFoodLevel()));
		s = s.replaceAll("<player-balance-readable>", data.getBalanceReadable());
		s = s.replaceAll("<player-balance>", String.valueOf(data.getBalance()));
		s = s.replaceAll("<player-mine-rank>", String.valueOf(data.getRank()));
		s = s.replaceAll("<player-prestige-rankup>", String.valueOf(data.getRank() == Rank.Z ? "Prestige Cost" : "Rankup Cost"));
		s = s.replaceAll("<player-rankup-progress>", String.valueOf(data.percentProgress()) + "%");
		s = s.replaceAll("<player-prestige>", String.valueOf(data.getPrestige()));
		s = s.replaceAll("<player-rankup-cost>", String.valueOf(ReadableNumbers.coolFormat(data.getRankupPriceMultiplied(), 0)));
		return s;
	}
	
}
