package pw.xwy.prison_core.utility;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class MineManager implements Listener {
	public static final ItemStack mineSelector = new CustomItem(Material.STICK).setName("§6Mine Stick").setLore("§7Left click to set first corner", "§7Right click to set second corner", "§7Shift-right click in the air to reset corners.");
	public static HashMap<ERank, Mine> mines = new HashMap<>();
	public static HashMap<UUID, Location> locationOneHashMap = new HashMap<>();
	public static HashMap<UUID, Location> locationTwoHashMap = new HashMap<>();
	
	public MineManager() {
		for (ERank rank : ERank.values()) {
			if (rank != ERank.Free) {
				mines.put(rank, new Mine(rank.toString()));
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getItem() != null && e.getItem().getType().equals(Material.STICK) && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equals(mineSelector.getItemMeta().getDisplayName())) {
			if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
				e.setCancelled(true);
				locationOneHashMap.put(e.getPlayer().getUniqueId(), e.getClickedBlock().getLocation());
				e.getPlayer().sendMessage("§6Location 1 set");
			} else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				e.setCancelled(true);
				locationTwoHashMap.put(e.getPlayer().getUniqueId(), e.getClickedBlock().getLocation());
				e.getPlayer().sendMessage("§6Location 2 set");
			} else if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getPlayer().isSneaking()) {
				e.setCancelled(true);
				locationOneHashMap.remove(e.getPlayer().getUniqueId());
				locationTwoHashMap.remove(e.getPlayer().getUniqueId());
				e.getPlayer().sendMessage("§6Locations reset");
			}
		}
	}
}
