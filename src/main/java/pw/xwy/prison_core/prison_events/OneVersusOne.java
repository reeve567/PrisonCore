package pw.xwy.prison_core.prison_events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.Rect3D;
import pw.xwy.prison_core.utility.item.CustomItem;

public class OneVersusOne {
	
	private final ItemStack[] kit = new ItemStack[]{
			new ItemStack(Material.IRON_SWORD),
			new ItemStack(Material.BOW),
			new CustomItem(Material.ARROW).setCustomAmount(16),
			new CustomItem(Material.GOLDEN_APPLE).setCustomAmount(5),
			new CustomItem(Material.COOKED_BEEF).setCustomAmount(64)
	};
	
	private final ItemStack[] armor = new ItemStack[]{
			new ItemStack(Material.IRON_HELMET),
			new ItemStack(Material.DIAMOND_CHESTPLATE),
			new ItemStack(Material.IRON_LEGGINGS),
			new ItemStack(Material.IRON_BOOTS)
	};
	private Location toGo = new Location(Bukkit.getWorld("world"), -202.5, 68, -699.5);
	private Location spec = new Location(Bukkit.getWorld("world"), -194.5, 68, -692.5);
	private Location win = new Location(Bukkit.getWorld("world"), -202.5, 68, -683.5);
	private Location blue = new Location(Bukkit.getWorld("world"), -182.5, 69, -709.5);
	private Location red = new Location(Bukkit.getWorld("world"), -182.5, 69, -675.5);
	
	private Rect3D area = new Rect3D(
			new Location(Bukkit.getWorld("world"), -173, 88, -673),
			new Location(Bukkit.getWorld("world"), -193, 53, -713));
	private Rect3D specArea = new Rect3D(
			new Location(Bukkit.getWorld("world"), -194, 72, -696),
			new Location(Bukkit.getWorld("world"), -195, 68, -689));
	private Rect3D winArea = new Rect3D(
			new Location(Bukkit.getWorld("world"), -198, 72, -678),
			new Location(Bukkit.getWorld("world"), -206, 67, -692));
	
	public Location getToGo() {
		return toGo;
	}
	
	public Location getSpec() {
		return spec;
	}
	
	public Location getWin() {
		return win;
	}
	
	public void moveToActive(Player player1, Player player2) {
		player1.getInventory().clear();
		player2.getInventory().clear();
		player1.teleport(blue);
		player2.teleport(red);
		for (ItemStack s : kit) {
			player1.getInventory().addItem(s);
			player2.getInventory().addItem(s);
		}
		player1.getInventory().setHelmet(armor[0]);
		player1.getInventory().setChestplate(armor[1]);
		player1.getInventory().setLeggings(armor[2]);
		player1.getInventory().setBoots(armor[3]);
		
		player2.getInventory().setHelmet(armor[0]);
		player2.getInventory().setChestplate(armor[1]);
		player2.getInventory().setLeggings(armor[2]);
		player2.getInventory().setBoots(armor[3]);
	}
	
	public Rect3D getArea() {
		return area;
	}
	
	public Rect3D getSpecArea() {
		return specArea;
	}
	
	public Rect3D getWinArea() {
		return winArea;
	}
}
