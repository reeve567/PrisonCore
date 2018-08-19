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
	
	public HashMap<MineMaterial, Integer> materials = new HashMap<>();
	public HashMap<MineMaterial, Double> shop = new HashMap<>();
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
	
	public boolean canSet(int amount, Material material, short durability) {
		return totalWithout(material, durability) + amount <= 1000;
	}
	
	public int totalWithout(Material material, int data) {
		int total = 0;
		for (MineMaterial m : materials.keySet()) {
			if (m.getMaterial() != material && m.getDurability() != data) {
				total += materials.get(m);
			}
		}
		return total;
	}
	
	public void addToShop(Material material, Double price) {
		
		switch (material) {
			case DIAMOND_BLOCK:
				addToShop(Material.DIAMOND, price / 9.0);
				addToShop(Material.DIAMOND_ORE, price / 10.0);
				break;
			case GOLD_BLOCK:
				addToShop(Material.GOLD_INGOT, price / 9.0);
				addToShop(Material.GOLD_ORE, price / 10.0);
				break;
			case IRON_BLOCK:
				addToShop(Material.IRON_INGOT, price / 9.0);
				addToShop(Material.IRON_ORE, price / 10.0);
				break;
			case EMERALD_BLOCK:
				addToShop(Material.EMERALD, price / 9.0);
				addToShop(Material.EMERALD_ORE, price / 10.0);
				break;
			case COAL_BLOCK:
				addToShop(Material.COAL, price / 9.0);
				addToShop(Material.COAL_ORE, price / 10.0);
				break;
			case REDSTONE_BLOCK:
				addToShop(Material.REDSTONE, price / 9.0);
				addToShop(Material.REDSTONE_ORE, price / 10.0);
				break;
			case LAPIS_BLOCK:
				addToShop(Material.INK_SACK, price / 9.0, (short) 4);
				addToShop(Material.LAPIS_ORE, price / 10.0);
				break;
		}
		shop.put(get(material), price);
	}
	
	public void removeFromShop(Material material, short durability) {
		shop.remove(get(material, durability));
	}
	
	public void removeFromShop(Material material) {
		switch (material) {
			case DIAMOND_BLOCK:
				removeFromShop(Material.DIAMOND);
				removeFromShop(Material.DIAMOND_ORE);
				break;
			case GOLD_BLOCK:
				removeFromShop(Material.GOLD_INGOT);
				removeFromShop(Material.GOLD_ORE);
				break;
			case IRON_BLOCK:
				removeFromShop(Material.IRON_INGOT);
				removeFromShop(Material.IRON_ORE);
				break;
			case EMERALD_BLOCK:
				removeFromShop(Material.EMERALD);
				removeFromShop(Material.EMERALD_ORE);
				break;
			case COAL_BLOCK:
				removeFromShop(Material.COAL);
				removeFromShop(Material.COAL_ORE);
				break;
			case REDSTONE_BLOCK:
				removeFromShop(Material.REDSTONE);
				removeFromShop(Material.REDSTONE_ORE);
				break;
			case LAPIS_BLOCK:
				removeFromShop(Material.INK_SACK, (short) 4);
				removeFromShop(Material.LAPIS_ORE);
				break;
		}
		shop.remove(get(material));
	}
	
	public void addToShop(Material material, Double price, short durability) {
		shop.put(get(material, durability), price);
	}
	
	private MineMaterial get(Material material) {
		return new MineMaterial(material);
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
	
	public boolean shopContains(Material material, short durability) {
		for (MineMaterial m : shop.keySet()) {
			if (m.getMaterial() == material && m.getDurability() == durability) return true;
		}
		return false;
	}
	
	public double shopPriceFor(Material material, short durability) {
		for (MineMaterial m : shop.keySet()) {
			if (m.getMaterial() == material && m.getDurability() == durability) return shop.get(m);
		}
		return 0;
	}
	
	public int airCheck() {
		return 1000 - total();
	}
	
	public int total() {
		int total = 0;
		for (MineMaterial m : materials.keySet()) {
			total += materials.get(m);
		}
		return total;
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
				MineMaterial mineMaterial = randomMaterial();
				b.setType(mineMaterial.getMaterial());
				b.setData((byte) mineMaterial.getDurability());
			}
			calculatePercentLeft();
		}
	}
	
	public MineMaterial randomMaterial() {
		int i = random.nextInt(999) + 1;
		int total = 0;
		MineMaterial material = null;
		for (MineMaterial m : materials.keySet()) {
			if (material == null) {
				total += materials.get(m);
				if (i < total) {
					material = m;
				}
			}
		}
		if (material == null) {
			material = new MineMaterial(Material.AIR);
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
		if (s != null) {
			if (s.length >= 1) {
				if (s[0].equalsIgnoreCase("unset")) {
					progressSign = null;
				} else {
					progressSign = locationFromString(s[0], s[1]).getBlock();
				}
			} else {
				progressSign = null;
			}
		} else
			progressSign = null;
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
	
	public String[] getWarpStrings() {
		if (warp == null) return new String[]{"unset"};
		return new String[]{warp.getX() + ":" + warp.getY() + ":" + warp.getZ() + ":" + warp.getPitch() + ":" + warp.getYaw(), warp.getWorld().getName()};
	}
	
	public String compositionString() {
		String s = "";
		for (MineMaterial m : materials.keySet()) {
			s += m.material.toString() + ":" + m.durability + ":" + materials.get(m) + ";";
		}
		return s;
	}
	
	public String shopString() {
		String s = "";
		for (MineMaterial m : shop.keySet()) {
			s += m.material.toString() + ":" + m.durability + ":" + shop.get(m) + ";";
		}
		return s;
	}
	
	public void setShop(String shop) {
		if (shop != null) {
			while (shop.contains(";")) {
				String temp = shop.substring(0, shop.indexOf(";") + 1);
				this.shop.put(get(Material.valueOf(temp.substring(0, temp.indexOf(":"))), Short.valueOf(temp.substring(temp.indexOf(":") + 1, temp.lastIndexOf(":")))), Double.valueOf(temp.substring(temp.lastIndexOf(":") + 1, temp.indexOf(";"))));
				shop = shop.replaceFirst(temp, "");
			}
		}
	}
	
	private MineMaterial get(Material material, short durability) {
		return new MineMaterial(material, durability);
	}
	
	public void addMaterial(Material material, int durability, int amount) {
		MineMaterial mineMaterial = null;
		for (MineMaterial m : materials.keySet()) {
			if (m.getMaterial() == material && m.getDurability() == durability) {
				mineMaterial = m;
				break;
			}
		}
		if (mineMaterial != null) {
			if (amount == 0)
				materials.remove(mineMaterial);
			else
				materials.put(mineMaterial, amount);
		} else
			materials.put(new MineMaterial(material, (short) durability), amount);
	}
	
	public void setComposition(String composition) {
		if (composition != null) {
			while (composition.contains(";")) {
				String temp = composition.substring(0, composition.indexOf(";") + 1);
				materials.put(get(Material.valueOf(temp.substring(0, temp.indexOf(":"))), Short.valueOf(temp.substring(temp.indexOf(":") + 1, temp.lastIndexOf(":")))), Integer.valueOf(temp.substring(temp.lastIndexOf(":") + 1, temp.indexOf(";"))));
				composition = composition.replaceFirst(temp, "");
			}
		}
	}
	
	public Location getWarp() {
		return warp;
	}
	
	public void setWarp(Location location) {
		warp = location;
	}
	
	private class MineMaterial {
		private Material material;
		private short durability;
		
		
		private MineMaterial(Material material, short durability) {
			this.material = material;
			this.durability = durability;
		}
		
		private MineMaterial(Material material) {
			this.material = material;
			this.durability = 0;
		}
		
		public Material getMaterial() {
			return material;
		}
		
		public short getDurability() {
			return durability;
		}
	}
	
	private class Task extends BukkitRunnable {
		
		boolean clean = false;
		int amount = 0;
		int timeDelay = 600;
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
