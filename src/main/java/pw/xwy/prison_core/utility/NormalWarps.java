package pw.xwy.prison_core.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum NormalWarps {
	FURNACES,
	PLOTS,
	FREE,
	CRATES,
	RESOURCES,
	PVP,
	CELLS,
	INFO,
	SPAWN;
	
	private Location location;
	
	NormalWarps() {
		location = null;
	}
	
	public void loadLocationFromConfig(Config config) {
		if (config.getString("warps." + toString()).equalsIgnoreCase("unset")) {
			location = null;
		} else {
			String s = config.getString("warps." + toString());
			String world = s.substring(0, s.indexOf(';'));
			s = s.replaceFirst(world + ";", "");
			double x = Double.parseDouble(s.substring(0, s.indexOf(';')));
			s = s.replaceFirst(x + ";", "");
			double y = Double.parseDouble(s.substring(0, s.indexOf(';')));
			
			s = s.replaceFirst(y + ";", "");
			double z = Double.parseDouble(s.substring(0, s.indexOf(';')));
			
			s = s.replaceFirst(z + ";", "");
			float yaw = Float.parseFloat(s.substring(0, s.indexOf(';')));
			
			s = s.replaceFirst(yaw + ";", "");
			float pitch = Float.parseFloat(s);
			location = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
		}
	}
	
	public void save(Config config) {
		if (location == null) {
			config.set("warps." + toString(), "unset");
		} else {
			config.set("warps." + toString(), location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch());
		}
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
