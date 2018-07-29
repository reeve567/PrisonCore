package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.utilities.InventoryUtility;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.CustomItem;
import pw.xwy.prison_core.utility.PlayerConfig;
import pw.xwy.prison_core.utility.TimeFormatting;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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
			items.add(new Slot(30, 11, "§6Shark", names[5]));
			items.add(new Slot(32, 3, "§6Whale", names[6]));
			items.add(new Slot(34, 6, "§6Jellyfish", names[7]));
			
			for (Slot s : items) {
				if (player.hasPermission("kits." + s.mapName)) {
					if (!player.hasPermission("kits.no-cool-downs")) {
						s.lore = ready;
					} else {
						if (s.ready) {
							s.lore = ready;
						} else {
							s.lore = "§7You do not have this kit for another++" + TimeFormatting.kitFormat(TimeFormatting.getTime((int) getDateDiff(data.getLastUsed(s.mapName), new Date(), TimeUnit.SECONDS)));
						}
					}
				} else {
					s.durability = 14;
					s.lore = "§7No permission to use this kit!";
				}
				inventory.setItem(s.slotNumber, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(s.durability).setName(s.name).setLore(conv(s.lore)));
			}
		}
	}
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	private ArrayList<String> conv(String desc) {
		if (!desc.contains("++")) {
			return new ArrayList<>(Arrays.asList(desc));
		}
		ArrayList<String> de = new ArrayList<>();
		while (desc.contains("++")) {
			desc = desc.replace(desc.substring(0, desc.indexOf("++") + 2), "");
		}
		return de;
	}
	
	private boolean isIn24Hours(Date date) {
		Instant now = Instant.now();
		return (!date.toInstant().isBefore(now.minus(24, ChronoUnit.HOURS))) && (date.toInstant().isBefore(now));
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!e.getInventory().getTitle().equalsIgnoreCase("§6Kits")) {
			return;
		}
		if (e.getRawSlot() == -1 || !(e.getRawSlot() < 45)) {
			return;
		}
		
		e.setCancelled(true);
		
		ArrayList<ItemStack> stacks = new ArrayList<>();
		
		if (e.getCurrentItem() == null || e.getCurrentItem().getDurability() == 14) {
			return;
		}
		Player player = (Player) e.getWhoClicked();
		PlayerConfig config = ConfigurationHandler.playerConfigs.get(player.getUniqueId());
		
		switch (e.getRawSlot()) {
			case 10:
				//god tools
				config.setLastUsed("god-tools");
				
				stacks.add(new CustomItem(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3));
				stacks.add(new CustomItem(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.SILK_TOUCH, 1));
				
				stacks.add(new CustomItem(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3));
				stacks.add(new CustomItem(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.SILK_TOUCH, 1));
				
				stacks.add(new CustomItem(Material.DIAMOND_SPADE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3));
				stacks.add(new CustomItem(Material.DIAMOND_SPADE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.SILK_TOUCH, 1));
				break;
			case 12:
				config.setLastUsed("god-axe");
				stacks.add(new CustomItem(Material.DIAMOND_AXE).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.FIRE_ASPECT, 2));
				stacks.add(new CustomItem(Material.GOLDEN_APPLE).setDurability(1).setCustomAmount(20));
				break;
			case 14:
				config.setLastUsed("pvp");
				stacks.add(new CustomItem(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3));
				
				stacks.add(new CustomItem(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3));
				
				stacks.add(new CustomItem(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.FIRE_ASPECT, 2));
				stacks.add(new CustomItem(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.ARROW_INFINITE, 1).addEnchant(Enchantment.ARROW_FIRE, 1));
				stacks.add(new CustomItem(Material.ARROW));
				stacks.add(new CustomItem(Material.GOLDEN_APPLE).setDurability(1).setCustomAmount(20));
				break;
			case 16:
				config.setLastUsed("guppy");
				stacks.add(new CustomItem(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).setName("§3Guppy §7Helmet"));
				stacks.add(new CustomItem(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).setName("§3Guppy §7Chestplate"));
				stacks.add(new CustomItem(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).setName("§3Guppy §7Leggings"));
				stacks.add(new CustomItem(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).setName("§3Guppy §7Boots"));
				
				stacks.add(new CustomItem(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.DURABILITY, 1));
				stacks.add(new CustomItem(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 3).addEnchant(Enchantment.DURABILITY, 1));
				stacks.add(new CustomItem(Material.ARROW).setCustomAmount(64));
				
				stacks.add(new CustomItem(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 3).addEnchant(Enchantment.DURABILITY, 1));
				stacks.add(new CustomItem(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 3).addEnchant(Enchantment.DURABILITY, 1));
				stacks.add(new CustomItem(Material.DIAMOND_SPADE).addEnchant(Enchantment.DIG_SPEED, 3).addEnchant(Enchantment.DURABILITY, 1));
				stacks.add(new CustomItem(Material.GOLDEN_APPLE).setDurability(1));
				break;
			case 28:
				config.setLastUsed("mudkip");
				stacks.add(new CustomItem(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.DURABILITY, 2).setName("§6Mud§bKip §7Helmet"));
				stacks.add(new CustomItem(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.DURABILITY, 2).setName("§6Mud§bKip §7Chestplate"));
				stacks.add(new CustomItem(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.DURABILITY, 2).setName("§6Mud§bKip §7Leggings"));
				stacks.add(new CustomItem(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.DURABILITY, 2).setName("§6Mud§bKip §7Boots"));
				
				stacks.add(new CustomItem(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 4).addEnchant(Enchantment.DURABILITY, 2));
				stacks.add(new CustomItem(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 4).addEnchant(Enchantment.DURABILITY, 2));
				stacks.add(new CustomItem(Material.ARROW).setCustomAmount(64));
				
				stacks.add(new CustomItem(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 4).addEnchant(Enchantment.DURABILITY, 2));
				stacks.add(new CustomItem(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 4).addEnchant(Enchantment.DURABILITY, 2));
				stacks.add(new CustomItem(Material.DIAMOND_SPADE).addEnchant(Enchantment.DIG_SPEED, 4).addEnchant(Enchantment.DURABILITY, 2));
				stacks.add(new CustomItem(Material.GOLDEN_APPLE).setDurability(1).setCustomAmount(4));
				break;
			
			case 30:
				config.setLastUsed("shark");
				stacks.add(new CustomItem(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§1Shark §7Helmet"));
				stacks.add(new CustomItem(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§1Shark §7Chestplate"));
				stacks.add(new CustomItem(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§1Shark §7Leggings"));
				stacks.add(new CustomItem(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§1Shark §7Boots"));
				
				stacks.add(new CustomItem(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.ARROW_INFINITE, 1));
				stacks.add(new CustomItem(Material.ARROW));
				
				stacks.add(new CustomItem(Material.EXP_BOTTLE).setCustomAmount(64));
				
				stacks.add(new CustomItem(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_SPADE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.GOLDEN_APPLE).setDurability(1).setCustomAmount(12));
				break;
			case 32:
				config.setLastUsed("whale");
				stacks.add(new CustomItem(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§9Whale §7Helmet"));
				stacks.add(new CustomItem(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§9Whale §7Chestplate"));
				stacks.add(new CustomItem(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§9Whale §7Leggings"));
				stacks.add(new CustomItem(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§9Whale §7Boots"));
				
				stacks.add(new CustomItem(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.ARROW_INFINITE, 1));
				stacks.add(new CustomItem(Material.ARROW));
				
				stacks.add(new CustomItem(Material.EXP_BOTTLE).setCustomAmount(64));
				stacks.add(new CustomItem(Material.EXP_BOTTLE).setCustomAmount(64));
				stacks.add(new CustomItem(Material.EXP_BOTTLE).setCustomAmount(64));
				
				stacks.add(new CustomItem(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_SPADE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				
				stacks.add(new CustomItem(Material.GOLDEN_APPLE).setDurability(1).setCustomAmount(24));
				break;
			case 34:
				config.setLastUsed("jellyfish");
				stacks.add(new CustomItem(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§d§lJellyfish §7Helmet"));
				stacks.add(new CustomItem(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§d§lJellyfish §7Chestplate"));
				stacks.add(new CustomItem(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§d§lJellyfish §7Leggings"));
				stacks.add(new CustomItem(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§d§lJellyfish §7Boots"));
				
				stacks.add(new CustomItem(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§d§lJellyfish §7Helmet"));
				stacks.add(new CustomItem(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§d§lJellyfish §7Chestplate"));
				stacks.add(new CustomItem(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§d§lJellyfish §7Leggings"));
				stacks.add(new CustomItem(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName("§d§lJellyfish §7Boots"));
				
				stacks.add(new CustomItem(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.ARROW_INFINITE, 1));
				stacks.add(new CustomItem(Material.ARROW));
				
				stacks.add(new CustomItem(Material.EXP_BOTTLE).setCustomAmount(64));
				stacks.add(new CustomItem(Material.EXP_BOTTLE).setCustomAmount(64));
				stacks.add(new CustomItem(Material.EXP_BOTTLE).setCustomAmount(64));
				stacks.add(new CustomItem(Material.EXP_BOTTLE).setCustomAmount(64));
				
				stacks.add(new CustomItem(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				stacks.add(new CustomItem(Material.DIAMOND_SPADE).addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.DURABILITY, 3));
				
				stacks.add(new CustomItem(Material.GOLDEN_APPLE).setDurability(1).setCustomAmount(48));
				break;
		}
		player.getInventory().addItem(stacks.toArray(new ItemStack[0]));
		player.closeInventory();
		new KitGUI(player).openInventory();
	}
	
	public void openInventory() {
		player.openInventory(inventory);
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
