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
	
	public static void run(Player player, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("starter")) {
				for (ItemStack itemStack : starterKit) {
					player.getInventory().addItem(itemStack);
				}
			}
		}
		
		
		new KitGUI(player).openInventory();
	}
	
}
