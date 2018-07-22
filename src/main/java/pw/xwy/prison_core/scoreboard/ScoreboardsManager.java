package pw.xwy.prison_core.scoreboard;

import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.Config;
import pw.xwy.prison_core.utility.ConfigurationHandler;

import java.util.ArrayList;

public class ScoreboardsManager implements Listener {
	
	public static final int delay = 5;
	private static ArrayList<ScoreboardObj> scoreboards = new ArrayList<>();
	private ScoreboardTask scoreboardTask;
	private BukkitTask task;
	
	public ScoreboardsManager() {
		onEnable();
	}
	
	public void onEnable() {
		loadConfig();
		scoreboardTask = new ScoreboardTask();
		task = scoreboardTask.runTaskTimer(PrisonCore.getInstance(), 0, delay);
		new ToggleCommand();
		PrisonCore.registerEvents(this);
	}
	
	public void loadConfig() {
		Config config = ConfigurationHandler.getScoreboardConfiguration();
		if (config.getInt("ver") != 1) {
			//set defaults
			config.set("ver", 1);
			config.set("animationLength", 1);
			config.setComment("frames", "The duration is how long in seconds this frame is displayed in the animation, disregard this if you only are using one frame", "Copy all of frame '0' to another frame called '1' (and so on) to make an animation.", "If your frames aren't displaying, make sure the 'animationLength' is the right value");
			config.set("frames.0.title", "&aScoreboard Title");
			config.set("frames.0.strings", setupDefault());
			config.set("frames.0.duration", 1.0);
			config.saveConfig();
		}
		
		int animLength = config.getInt("animationLength");
		scoreboards = new ArrayList<>();
		for (int i = 0; i < animLength; i++) {
			scoreboards.add(new ScoreboardObj(config.getStringList("frames." + i + ".strings"), config.getString("frames." + i + ".title"), config.getDouble("frames.0.duration")));
		}
	}
	
	private ArrayList<String> setupDefault() {
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
	
	public static ArrayList<ScoreboardObj> getScoreboards() {
		return scoreboards;
	}
}
