package pw.xwy.prison_core.utility.misc_managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.ArrayList;

public class HologramsManager implements Listener {
	
	private static ArrayList<TextHologram> textHolograms = new ArrayList<>();
	private static boolean loaded = false;
	
	public HologramsManager() {
		if (!loaded) {
			loaded = true;
			for (Entity e : Bukkit.getWorld("world").getEntities()) {
				if (e instanceof ArmorStand)
					e.remove();
			}
			
			new TextHologram(new Location(Bukkit.getWorld("world"), 667.5, 95, -215.5), "§6Crates");
		}
	}
	
	public static ArrayList<TextHologram> addMultilineTextHologram(Location location, String... content) {
		ArrayList<TextHologram> holos = new ArrayList<>();
		int amount = 0;
		for (String s : content) {
			Location location1 = new Location(Bukkit.getWorlds().get(0), location.getX(), location.getY() - (amount * 0.25), location.getZ());
			TextHologram textHologram = new TextHologram(location1, s);
			holos.add(textHologram);
			textHolograms.add(textHologram);
			amount++;
		}
		return holos;
	}
	
	public static TextHologram addTextHologram(Location location, String content) {
		TextHologram textHologram = new TextHologram(location, content);
		textHolograms.add(textHologram);
		return textHologram;
	}
	
	public static void disable() {
		for (TextHologram hologram : textHolograms) {
			hologram.disable();
		}
	}
	
	@EventHandler
	public void onUnload(ChunkUnloadEvent e) {
		for (TextHologram t : textHolograms) {
			if (t.location.getChunk() == e.getChunk()) {
				e.setCancelled(true);
				return;
			}
		}
		
	}
	
	public static class TextHologram {
		
		private Location location;
		private ArmorStand stand;
		
		TextHologram(Location location, String content) {
			this.location = location;
			stand = (ArmorStand) location.getWorld().spawnEntity(this.location, EntityType.ARMOR_STAND);
			stand.setMarker(true);
			stand.setVisible(false);
			stand.setGravity(false);
			stand.setCustomName(content);
			stand.setCustomNameVisible(true);
		}
		
		public void setContent(String content) {
			stand.setCustomName(content);
		}
		
		public void disable() {
			stand.remove();
		}
		
		public Location getLocation() {
			return location;
		}
	}
	
}
