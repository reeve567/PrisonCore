package pw.xwy.prison_core.utility.item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VoucherUtility {
	
	public static ItemStack getMoneyVoucher(Player player, double amount) {
		CustomItem voucher = new CustomItem(Material.PAPER).setName("§6Bank Note").addLore("§2$§7" + amount);
		if (player != null) {
			voucher.addLore("§7Withdrawn by " + player.getName());
		}
		return voucher;
	}
	
	public static CustomItem getScroll() {
		return new CustomItem(Material.PAPER).setName("§f§lScroll").addLore("§7Protection against one destroy");
	}
	
	
}
