package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.EnderChest;
import pw.xwy.prison_core.utility.CustomItem;

public class CrateManager {
	
	private static Location location = null;
	
	public CrateManager(String location) {
		if (!location.equalsIgnoreCase("unset")) {
			String world = location.substring(0, location.indexOf(';'));
			location = location.replaceFirst(world + ";", "");
			int x = Integer.parseInt(location.substring(0, location.indexOf(';')));
			location = location.replaceFirst(x + ";", "");
			int y = Integer.parseInt(location.substring(0, location.indexOf(';')));
			location = location.replaceFirst(y + ";", "");
			int z = Integer.parseInt(location);
			
			CrateManager.location = new Location(Bukkit.getWorld(world), x, y, z);
		}
	}
	
	public static boolean isCrate(Block block) {
		if (location != null) {
			return block.getLocation().equals(location);
		}
		return false;
	}
	
	public static boolean setCrate(Player player) {
		
		if (player.getTargetBlock(null, 5) instanceof EnderChest) {
			location = player.getTargetBlock(null, 5).getLocation();
			return true;
		}
		return false;
	}
	
	public static String crateString() {
		if (location == null) return "unset";
		return location.getWorld().getName() + ";" + location.getBlockX() + ";" + location.getBlockY() + ";" + location.getBlockZ();
	}
	
	public static ItemStack getKey(CrateType type) {
		return new CustomItem(Material.TRIPWIRE_HOOK).setName(type.name + " §fKey");
	}
	
	enum CrateType {
		VOTE("§aVote"), RARE("§dRare"), ELITE("§4Elite"), TAG("§eTag");
		
		private String name;
		
		CrateType(String s) {
			name = s;
		}
		
		public String getName() {
			return name;
		}
	}
}
