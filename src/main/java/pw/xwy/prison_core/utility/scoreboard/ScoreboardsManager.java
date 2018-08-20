package pw.xwy.prison_core.utility.scoreboard;

import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.scoreboard.ScoreboardObj;
import pw.xwy.prison_core.utility.scoreboard.ScoreboardTask;

import java.util.ArrayList;

public class ScoreboardsManager {
	
	public static final int delay = 5;
	public static ArrayList<ScoreboardObj> scoreboards = new ArrayList<>();
	
	public static ArrayList<ScoreboardObj> getScoreboards() {
		return scoreboards;
	}
	
	public static ArrayList<String> setupDefault() {
		ArrayList<String> temp = new ArrayList<>();
		temp.add("&a&7------------");
		temp.add("");
		temp.add("&6Balance:");
		temp.add("&f<player-balance-readable>");
		temp.add("&6Rank:");
		temp.add("&f<player-mine-rank>");
		temp.add("&6Next Rank:");
		temp.add("&f<player-rankup-cost>");
		temp.add("&6Progress:");
		temp.add("&f<player-rankup-progress>");
		temp.add("&6Prestige:");
		temp.add("&f<player-prestige>");
		temp.add(" ");
		temp.add("&7------------");
		return temp;
	}
	
	public static void startTask() {
		new ScoreboardTask().runTaskTimer(PrisonCore.getInstance(), 0, delay);
	}
}
