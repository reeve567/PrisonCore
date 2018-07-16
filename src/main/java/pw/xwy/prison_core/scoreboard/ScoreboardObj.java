package pw.xwy.prison_core.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreboardObj {
	
	private List<String> strings;
	private String title;
	private int duration;
	
	public ScoreboardObj(List<String> strings, String title, double duration) {
		this.strings = strings;
		this.title = title;
		this.duration = (int) (duration * 20);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getStrings() {
		return strings;
	}
	
	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setScoreboard(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj;
		obj = board.registerNewObjective("main", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
		int i = 16;
		for (String s : strings) {
			Score sco = obj.getScore(ChatColor.translateAlternateColorCodes('&', PlaceholderConversion.convert(p, s)));
			sco.setScore(i--);
		}
		p.setScoreboard(board);
	}
}
