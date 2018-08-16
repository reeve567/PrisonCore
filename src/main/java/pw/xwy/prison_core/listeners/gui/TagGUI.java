package pw.xwy.prison_core.listeners.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pw.xwy.prison_core.custom_enchants.utilities.InventoryUtility;
import pw.xwy.prison_core.utility.CustomItem;
import pw.xwy.prison_core.utility.PlayerManager;
import pw.xwy.prison_core.utility.Tag;
import pw.xwy.prison_core.utility.XPlayer;

public class TagGUI implements Listener {
	
	public TagGUI(Player player) {
		if (player != null) {
			Inventory inventory = Bukkit.createInventory(player, 45, "§6Tags");
			InventoryUtility.setBackground(inventory, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(15).setName(" "));
			XPlayer player1 = PlayerManager.getXPlayer(player);
			int i = 11;
			for (Tag t : Tag.values()) {
				if (i == 13 || i == 31) {
					i++;
				} else if (i == 16) i = 20;
				else if (i == 25) i = 29;
				boolean has = player.hasPermission("xwy.tag." + t.toString().toLowerCase());
				boolean active = player1.getData().getActiveTag() == t;
				
				if (has) {
					if (active) {
						inventory.setItem(i, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(5).setName(t.getDisplay()));
					} else {
						inventory.setItem(i, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(4).setName(t.getDisplay()));
					}
				} else {
					inventory.setItem(i, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(14).setName(t.getDisplay()));
				}
				
				i++;
			}
			
			int j = 18;
			int k = 26;
			
			if (player1.getData().isTagToggle()) {
				CustomItem temp = new CustomItem(Material.STAINED_GLASS).setDurability(5).setName("§aTag ON");
				inventory.setItem(j, temp);
				inventory.setItem(k, temp);
			} else {
				CustomItem temp = new CustomItem(Material.STAINED_GLASS).setDurability(14).setName("§aTag OFF");
				inventory.setItem(j, temp);
				inventory.setItem(k, temp);
			}
			
			player.openInventory(inventory);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§6Tags")) {
			if (e.getAction() != InventoryAction.NOTHING) {
				if (e.getRawSlot() < 45) {
					e.setCancelled(true);
					int i = e.getRawSlot();
					XPlayer player = PlayerManager.getXPlayer((Player) e.getWhoClicked());
					if (i == 18 || i == 26) {
						if (e.getCurrentItem().getDurability() == 14) {
							player.getData().setTagToggle(true);
						} else if (e.getCurrentItem().getDurability() == 5) {
							player.getData().setTagToggle(false);
						}
						new TagGUI((Player) e.getWhoClicked());
					}
					
					
					if (!(e.getCurrentItem().getDurability() == 4)) {
						return;
					}
					
					switch (i) {
						case 11:
							player.getData().setActiveTag(Tag.HIGHROLLER);
							break;
						case 12:
							player.getData().setActiveTag(Tag.XWY);
							break;
						case 14:
							player.getData().setActiveTag(Tag.GAMBLER);
							break;
						case 15:
							player.getData().setActiveTag(Tag.HACKER);
							break;
						case 20:
							player.getData().setActiveTag(Tag.MUDKIP);
							break;
						case 21:
							player.getData().setActiveTag(Tag.NERD);
							break;
						case 22:
							player.getData().setActiveTag(Tag.PVP);
							break;
						case 23:
							player.getData().setActiveTag(Tag.MUDKIPPER);
							break;
						case 24:
							player.getData().setActiveTag(Tag.SALTY);
							break;
						case 29:
							player.getData().setActiveTag(Tag.NEWB);
							break;
						case 30:
							player.getData().setActiveTag(Tag.MLG);
							break;
						case 32:
							player.getData().setActiveTag(Tag.MAXED);
							break;
						case 33:
							player.getData().setActiveTag(Tag.DED);
							break;
						
					}
					new TagGUI((Player) e.getWhoClicked());
				}
			}
		}
	}
	
}
