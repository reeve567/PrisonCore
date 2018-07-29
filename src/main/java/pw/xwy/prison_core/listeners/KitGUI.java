package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import pw.xwy.prison_core.custom_enchants.utilities.InventoryUtility;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.CustomItem;
import pw.xwy.prison_core.utility.PlayerConfig;
import pw.xwy.prison_core.utility.TimeFormatting;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class KitGUI implements Listener {
	
	private static String[] names = {"god-tools", "god-axe", "pvp", "guppy", "mudkip", "shark", "whale", "jellyfish"};
	private final String ready = "§7Ready!";
	private final Player player;
	private Inventory inventory;
	private ArrayList<Slot> items = new ArrayList<>();
	private PlayerConfig data;
	
	
	public KitGUI(Player player) {
		this.player = player;
		if (player != null) {
			inventory = Bukkit.createInventory(player, 45, "§6Kits");
			InventoryUtility.setBackground(inventory, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(15).setName(" "));
			data = ConfigurationHandler.playerConfigs.get(player.getUniqueId());
			
			items.add(new Slot(10, 0, "§6God Tools", names[0]));
			items.add(new Slot(12, 4, "§6God Axe", names[1]));
			items.add(new Slot(14, 10, "§6PvP", names[2]));
			items.add(new Slot(16, 10, "§6Guppy", names[3]));
			items.add(new Slot(28, 1, "§6Mudkip", names[4]));
			items.add(new Slot(30, 3, "§6Shark", names[5]));
			items.add(new Slot(32, 11, "§6Whale", names[6]));
			items.add(new Slot(34, 6, "§6Jellyfish", names[7]));
			
			for (Slot s : items) {
				if (player.hasPermission("kits." + s.mapName)) {
					if (!player.hasPermission("kits.no-cool-downs")) {
						s.lore = ready;
					} else {
						if (s.ready) {
							s.lore = ready;
						} else {
							s.lore = "§7You do not have this kit for another\n" + TimeFormatting.kitFormat(TimeFormatting.getTime((int) getDateDiff(data.getLastUsed(s.mapName), new Date(), TimeUnit.SECONDS)));
						}
					}
				} else {
					s.durability = 14;
					s.lore = "§7No permission ot use this kit!";
				}
				inventory.setItem(s.slotNumber, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(s.durability).setName(s.name).setLore(s.lore));
			}
		}
	}
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	public void openInventory() {
		player.openInventory(inventory);
	}
	
	private boolean isIn24Hours(Date date) {
		Instant now = Instant.now();
		return (!date.toInstant().isBefore(now.minus(24, ChronoUnit.HOURS))) && (date.toInstant().isBefore(now));
	}
	
	private class Slot {
		final int slotNumber;
		final String name;
		final boolean ready;
		final String mapName;
		int durability;
		String lore;
		
		private Slot(int slotNumber, int durability, String name, String mapName) {
			this.slotNumber = slotNumber;
			this.durability = durability;
			this.name = name;
			this.mapName = mapName;
			ready = !isIn24Hours(data.getLastUsed(mapName));
		}
	}
	
}
