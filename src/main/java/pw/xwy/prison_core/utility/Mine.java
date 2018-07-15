package pw.xwy.prison_core.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
			//then actually clean
			for (Block b : area.getBlocks()) {
				b.setType(randomMaterial());
			}
			PrisonCore.log("Cleaned " + name);
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
		
		
		@Override
		public void run() {
			if (airCheck() > 300) {
				if (amount % 3 == 0) {
					calculatePercentLeft();
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
	}
	
}
