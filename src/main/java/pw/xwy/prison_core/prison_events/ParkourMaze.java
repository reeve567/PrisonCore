package pw.xwy.prison_core.prison_events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import pw.xwy.prison_core.utility.Rect3D;

public class ParkourMaze {
	
	private Rect3D area = new Rect3D(new Location(Bukkit.getWorld("world"), 644, 113, -344), new Location(Bukkit.getWorld("world"), 603, 44, -384));
	private Location spawn = new Location(Bukkit.getWorld("world"), 642.5, 67, -346.5);
	
	public Rect3D getArea() {
		return area;
	}
	
	public Location getSpawn() {
		return spawn;
	}
}
