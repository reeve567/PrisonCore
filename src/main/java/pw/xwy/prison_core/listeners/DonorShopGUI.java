package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.PlayerData;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.CustomItem;

public class DonorShopGUI implements Listener {
	
	private Inventory inventory;
	
	public DonorShopGUI(Player player) {
		if (player != null) {
			inventory = Bukkit.createInventory(player, 27, "§6Donor Shop");
			setBackground(inventory, new CustomItem(Material.STAINED_GLASS_PANE).setDurability(15).setName(" "));
			//inventory.setItem(4, new PlayerSkull(player).setName("§6Balance").setLore("§7" + PlayerDataManager.moneySymbol + PlayerDataManager.getPlayerData(player.getUniqueId()).getBalance()));
			inventory.setItem(9, new CustomItem(Material.GLOWSTONE_DUST).setName("§6Glowstone Dust").setCustomAmount(16).setLore("§716 for $5k"));
			inventory.setItem(10, new CustomItem(Material.ENDER_PEARL).setName("§6Ender Pearl").setCustomAmount(4).setLore("§74 for $10k"));
			inventory.setItem(11, new CustomItem(Material.MELON).setName("§6Melon").setCustomAmount(1).setLore("§71 for $2k"));
			inventory.setItem(12, new CustomItem(Material.NETHER_STALK).setName("§6Nether Wart").setCustomAmount(8).setLore("§78 for $10k"));
			inventory.setItem(13, new CustomItem(Material.SUGAR_CANE).setName("§6Sugar Cane").setCustomAmount(1).setLore("§71 for $2k"));
			inventory.setItem(14, new CustomItem(Material.SOUL_SAND).setName("§6Soul Sand").setCustomAmount(4).setLore("§74 for $8k"));
			inventory.setItem(15, new CustomItem(Material.LEATHER).setName("§6Leather").setCustomAmount(1).setLore("§71 for $7.5k"));
			inventory.setItem(16, new CustomItem(Material.CARROT).setName("§6Carrot").setCustomAmount(1).setLore("§71 for $2k"));
			inventory.setItem(17, new CustomItem(Material.BLAZE_ROD).setName("§6Blaze Rod").setCustomAmount(1).setLore("§71 for $2k"));
		}
	}
	
	private static void setBackground(Inventory inventory, ItemStack stack) {
		for (int i = 0; i < inventory.getSize(); i++)
			inventory.setItem(i, stack);
	}
	
	public void openInventory(Player player) {
		player.openInventory(inventory);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!e.getInventory().getTitle().equalsIgnoreCase("§6Donor Shop")) {
			return;
		}
		
		if (e.getRawSlot() != -1 && e.getRawSlot() < 27) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null) {
				Player player = (Player) e.getWhoClicked();
				PlayerData data = ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData();
				;
				
				switch (e.getCurrentItem().getType()) {
					case GLOWSTONE_DUST:
						if (data.getBalance() >= 5000) {
							player.getInventory().addItem(new ItemStack(Material.GLOWSTONE_DUST, 16));
							data.addBalance(-5000);
						}
						break;
					case ENDER_PEARL:
						if (data.getBalance() >= 10000) {
							player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 4));
							data.addBalance(-10000);
						}
						break;
					case MELON:
						if (data.getBalance() >= 2000) {
							player.getInventory().addItem(new ItemStack(Material.MELON));
							data.addBalance(-2000);
						}
						break;
					case NETHER_STALK:
						if (data.getBalance() >= 10000) {
							player.getInventory().addItem(new ItemStack(Material.NETHER_STALK, 8));
							data.addBalance(-10000);
						}
						break;
					case SUGAR_CANE:
						if (data.getBalance() >= 2000) {
							player.getInventory().addItem(new ItemStack(Material.SUGAR_CANE));
							data.addBalance(-2000);
						}
						break;
					case SOUL_SAND:
						if (data.getBalance() >= 8000) {
							player.getInventory().addItem(new ItemStack(Material.SOUL_SAND, 4));
							data.addBalance(-8000);
						}
						break;
					case LEATHER:
						if (data.getBalance() >= 7500) {
							player.getInventory().addItem(new ItemStack(Material.LEATHER));
							data.addBalance(-7500);
						}
						break;
					case CARROT:
						if (data.getBalance() >= 2000) {
							player.getInventory().addItem(new ItemStack(Material.CARROT));
							data.addBalance(-2000);
						}
						break;
					case BLAZE_ROD:
						if (data.getBalance() >= 2000) {
							player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
							data.addBalance(-2000);
						}
						break;
				}
			}
		}
	}
	
	
}
