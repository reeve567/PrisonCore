package pw.xwy.prison_core.utility.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.prison_core.commands.ToggleCommand;

public class ScoreboardTask extends BukkitRunnable {
	
	private int i = ScoreboardsManager.delay;
	private int index = 0;
	
	@Override
	public void run() {
		i += ScoreboardsManager.delay;
		if (i >= ScoreboardsManager.getScoreboards().get(index).getDuration()) {
			i = 0;
			index++;
			if (index >= ScoreboardsManager.getScoreboards().size()) index = 0;
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!ToggleCommand.disabled.contains(p.getUniqueId())) {
					ScoreboardsManager.getScoreboards().get(index).setScoreboard(p);
				}
			}
		}
	}
}
