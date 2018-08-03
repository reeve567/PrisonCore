package pw.xwy.prison_core.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.prison_core.PrisonCore;

import java.util.HashMap;
import java.util.Random;

public class Mine implements Listener {
	
	public HashMap<Material, Integer> materials = new HashMap<>();
	public Task task = new Task();
	private String name;
	private Random random = new Random();
	private Rect3D area = null;
	private int percentLeft;
	private Block progressSign = null;
	private Location warp = null;
	
	public Mine(String name) {
		this.name = name;
		task.runTaskTimer(PrisonCore.getInstance(), 20, 20);
		Bukkit.getPluginManager().registerEvents(this, PrisonCore.getInstance());
	}
	
	public boolean canSet(int amount) {
		return total() + amount <= 1000;
	}
	
	public int total() {
		int total = 0;
		for (Material m : materials.keySet()) {
			total += materials.get(m);
		}
		return total;
	}
	
	public boolean canSet(int amount, Material material) {
		return totalWithout(material) + amount <= 1000;
	}
	
	public int totalWithout(Material material) {
		int total = 0;
		for (Material m : materials.keySet()) {
			if (m != material) {
				total += materials.get(m);
			}
		}
		return total;
	}
	
	public void setProgressSign(Block sign) {
		progressSign = sign;
	}
	
	public String[] getProgressSignStrings() {
		if (progressSign != null) {
			Location l = progressSign.getLocation();
			return new String[]{l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ(), l.getWorld().getName()};
		}
		return new String[]{"unset"};
	}
	
	public Rect3D getArea() {
		return area;
	}
	
	public String[] areaStrings() {
		if (area == null) {
			return new String[]{"unset"};
		} else {
			return area.toStrings();
		}
	}
	
	public int airCheck() {
		return 1000 - total();
	}
	
	public boolean setRectangle(Location location1, Location location2) {
		if (location1 != null && location2 != null)
			if (location1.getWorld().equals(location2.getWorld())) {
				area = new Rect3D(location1, location2);
				return true;
			}
		return false;
	}
	
	public void setRectangle(String[] rect) {
		if (rect.length >= 1) {
			if (rect[0].equalsIgnoreCase("unset")) {
				area = null;
			} else {
				area = new Rect3D(rect);
			}
		}
	}
	
	public void clean() {
		if (area != null) {
			if (warp != null) {
				for (Player p : area.playersInside()) {
					p.teleport(warp);
				}
			}
			//then actually clean
			for (Block b : area.getBlocks()) {
				b.setType(randomMaterial());
			}
			calculatePercentLeft();
		}
	}
	
	public Material randomMaterial() {
		int i = random.nextInt(999) + 1;
		int total = 0;
		Material material = null;
		for (Material m : materials.keySet()) {
			if (material == null) {
				total += materials.get(m);
				if (i < total) {
					material = m;
				}
			}
		}
		if (material == null) {
			material = Material.AIR;
		}
		return material;
	}
	
	public void calculatePercentLeft() {
		if (area != null) {
			int total = 0;
			for (Block b : area.getBlocks()) {
				if (b != null)
					if (b.getType() != Material.AIR) {
						total++;
					}
			}
			percentLeft = (int) (((double) total / area.getSize()) * 100.0);
		} else percentLeft = 100;
	}
	
	public Block getProgressSign() {
		return progressSign;
	}
	
	public void setProgressSign(String[] s) {
		if (s.length >= 1) {
			if (s[0].equalsIgnoreCase("unset")) {
				progressSign = null;
			} else {
				progressSign = locationFromString(s[0], s[1]).getBlock();
			}
		} else {
			progressSign = null;
		}
	}
	
	private Location locationFromString(String string, String world) {
		int x, y, z;
		x = Integer.parseInt(string.substring(0, string.indexOf(":")));
		string = string.substring(string.indexOf(":") + 1);
		y = Integer.parseInt(string.substring(0, string.indexOf(":")));
		string = string.substring(string.indexOf(":") + 1);
		z = Integer.parseInt(string);
		return new Location(Bukkit.getWorld(world), x, y, z);
	}
	
	public void setWarp(String[] strings) {
		if (strings.length >= 1) {
			if (strings[0].equalsIgnoreCase("unset")) {
				warp = null;
			} else {
				warp = locationFromStringPichYaw(strings[0], strings[1]);
			}
		} else {
			warp = null;
		}
	}
	
	private Location locationFromStringPichYaw(String string, String world) {
		double x, y, z, pi, ya;
		x = Double.parseDouble(string.substring(0, string.indexOf(":")));
		string = string.substring(string.indexOf(":") + 1);
		y = Double.parseDouble(string.substring(0, string.indexOf(":")));
		string = string.substring(string.indexOf(":") + 1);
		z = Double.parseDouble(string.substring(0, string.indexOf(":")));
		string = string.substring(string.indexOf(":") + 1);
		pi = Double.parseDouble(string.substring(0, string.indexOf(":")));
		string = string.substring(string.indexOf(":") + 1);
		ya = Double.parseDouble(string);
		
		return new Location(Bukkit.getWorld(world), x, y, z, (float) pi, (float) ya);
	}
	
	public void setWarp(Location location) {
		warp = location;
	}
	
	public String[] getWarpStrings() {
		if (warp == null) return new String[]{"unset"};
		return new String[]{warp.getX() + ":" + warp.getY() + ":" + warp.getZ() + ":" + warp.getPitch() + ":" + warp.getYaw(), warp.getWorld().getName()};
	}
	
	public String compositionString() {
		String s = "";
		for (Material m : materials.keySet()) {
			s += m.toString() + ":" + materials.get(m) + ";";
		}
		return s;
	}
	
	public void setComposition(String composition) {
		while (composition.contains(";")) {
			materials.put(Material.valueOf(composition.substring(0, composition.indexOf(":"))), Integer.valueOf(composition.substring(composition.indexOf(":") + 1, composition.indexOf(";"))));
			composition = composition.substring(composition.indexOf(";") + 1);
		}
	}
	
	private class Task extends BukkitRunnable {
		
		boolean clean = false;
		int amount = 0;
		int timeDelay = 10;
		private TimeFormatting.Time time = TimeFormatting.getTime(timeDelay - amount);
		
		@Override
		public void run() {
			if (getProgressSign() != null && getProgressSign().getState() instanceof Sign) {
				time.update(timeDelay - amount);
				Sign sign = (Sign) getProgressSign().getState();
				
				sign.setLine(0, "§lReset Time:");
				sign.setLine(1, TimeFormatting.signFormat(time));
				sign.setLine(2, "§lBlocks left:");
				sign.setLine(3, "§l" + percentLeft + "%");
				sign.update(true);
			}
			
			if (amount % 3 == 0 && amount != 0) {
				calculatePercentLeft();
				if (airCheck() < 300) {
					if (percentLeft < 30) {
						amount = 0;
						clean = true;
					}
				}
			}
			
			
			if (amount >= timeDelay) {
				amount = 0;
				clean = true;
			} else {
				amount++;
			}
			if (clean) {
				clean();
				clean = false;
			}
		}
	}
	
}
