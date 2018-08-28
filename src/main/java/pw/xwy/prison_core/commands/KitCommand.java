package pw.xwy.prison_core.commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.listeners.gui.KitGUI;
import pw.xwy.prison_core.utility.item.CustomItem;

public class KitCommand {
	
	
	public static ItemStack[] starterKit = new ItemStack[]{
			new CustomItem(Material.BREAD).setCustomAmount(64),
			new CustomItem(Material.IRON_PICKAXE)
	};
	
	private static ItemStack[] kit = new ItemStack[]{
			new ItemStack(Material.IRON_SWORD),
			new ItemStack(Material.BOW),
			new CustomItem(Material.ARROW).setCustomAmount(16),
			new CustomItem(Material.FISHING_ROD),
			new CustomItem(Material.BREAD).setCustomAmount(8),
			new ItemStack(Material.IRON_HELMET),
			new ItemStack(Material.IRON_CHESTPLATE),
			new ItemStack(Material.IRON_LEGGINGS),
			new ItemStack(Material.IRON_BOOTS)
	};
	
	public static void run(Player player, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("starter")) {
				for (ItemStack itemStack : starterKit) {
					player.getInventory().addItem(itemStack);
				}
				return;
			} else if (args[0].equalsIgnoreCase("fighter")) {
				for (ItemStack itemStack : kit) {
					player.getInventory().addItem(itemStack);
				}
				return;
			}
		}
		
		
		new KitGUI(player).openInventory();
	}
	
}
