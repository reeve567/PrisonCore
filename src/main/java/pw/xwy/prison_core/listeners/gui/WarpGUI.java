package pw.xwy.prison_core.listeners.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomItem;

public class WarpGUI implements Listener {
	
	private Inventory mainMenu = Bukkit.createInventory(null, 54, "§6Warps");
	private Inventory donorWarps = Bukkit.createInventory(null, 27, "§6Donor Warps");
	private Inventory mineWarps = Bukkit.createInventory(null, 54, "§6Mine Warps");
	
	public WarpGUI(Player player) {
		//ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 15);
		ItemStack backgroup = new CustomItem(Material.STAINED_GLASS_PANE).setDurability(15).setName(" ");
		setBackground(mainMenu, backgroup);
		setBackground(donorWarps, backgroup);
		setBackground(mineWarps, backgroup);
		
		
	}
	
	public static Inventory setBackground(Inventory inventory, ItemStack stack) {
		for (int i = 0; i < inventory.getSize(); i++)
			inventory.setItem(i, stack);
		return inventory;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
	
	}
	
}
