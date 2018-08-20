package pw.xwy.prison_core.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.prison_core.utility.misc_managers.HologramsManager;

import java.util.ArrayList;

public class HologramAnimationTask extends BukkitRunnable {
	
	private final String goldBold = "§b§l";
	private String base = "§6§lMudKip Prison";
	private ArrayList<HologramsManager.TextHologram> holograms = HologramsManager.addMultilineTextHologram(new Location(Bukkit.getWorld("world"), 672.5, 94, -201.5), "Welcome to", base, "Use /warp to select a mine");
	private String[] strings = new String[14];
	
	private int index = 1;
	private boolean forwards = true;
	private int skip = 0;
	
	public HologramAnimationTask() {
		for (int i = 4; i < base.length(); i++) {
			if (base.indexOf(' ') != i) {
				strings[i - 4] = base.substring(0, i) + goldBold + base.substring(i);
			} else {
				skip = i - 4;
			}
		}
		strings[strings.length - 1] = base;
	}
	
	@Override
	public void run() {
		if (index != strings.length && index != -1) {
			holograms.get(1).setContent(strings[index]);
		} else {
			forwards = !forwards;
		}
		if (forwards) {
			index++;
		} else {
			index--;
		}
		
		if (index == skip) {
			if (forwards) {
				index++;
			} else {
				index--;
			}
		}
	}
}
