package pw.xwy.prison_core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.player.XPlayer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SignListener implements Listener {
	
	private HashMap<Material, List<String>> aliases = new HashMap<>();
	
	public SignListener() {
		aliases.put(Material.DIAMOND_BLOCK, Arrays.asList("db", "diamondblock", String.valueOf(Material.DIAMOND_BLOCK.getId())));
		aliases.put(Material.GOLD_BLOCK, Arrays.asList("gb", "goldblock", String.valueOf(Material.GOLD_BLOCK.getId())));
		aliases.put(Material.IRON_BLOCK, Arrays.asList("ib", "ironblock", String.valueOf(Material.IRON_BLOCK.getId())));
		aliases.put(Material.COAL_BLOCK, Arrays.asList("cb", "coalblock", String.valueOf(Material.COAL_BLOCK.getId())));
		aliases.put(Material.EMERALD_BLOCK, Arrays.asList("eb", "emeraldblock", String.valueOf(Material.EMERALD_BLOCK.getId())));
		aliases.put(Material.LAPIS_BLOCK, Arrays.asList("lb", "lapisblock", String.valueOf(Material.LAPIS_BLOCK.getId())));
		
	}
	
	/*
		line one -- chestshop or name
		line two -- B/S <price>
		line three -- amount
		line four -- material
	*/
	@EventHandler
	public void onChange(SignChangeEvent e) {
		Block b = getBlockSignAttachedTo(e.getBlock());
		if (b != null && b.getType() == Material.CHEST) {
			if (e.getLine(0).equalsIgnoreCase("cs") || e.getLine(0).equalsIgnoreCase("chestshop")) {
				e.setLine(0, "§l" + e.getPlayer().getName());
				if (Integer.parseInt(e.getLine(2)) > 64) {
					e.setLine(2, "64");
				} else if (Integer.parseInt(e.getLine(2)) <= 0) {
					e.setLine(2, "1");
				}
			}
		}
		
	}
	
	public static Block getBlockSignAttachedTo(Block block) {
		if (block.getType().equals(Material.WALL_SIGN))
			switch (block.getData()) {
				case 2:
					return block.getRelative(BlockFace.SOUTH);
				case 3:
					return block.getRelative(BlockFace.NORTH);
				case 4:
					return block.getRelative(BlockFace.EAST);
				case 5:
					return block.getRelative(BlockFace.WEST);
			}
		return null;
	}
	
	
	/*
		line one -- chestshop or name
		line two -- B/S <price>
		line three -- amount
		line four -- material
	*/
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.WALL_SIGN) {
			Sign sign = (Sign) e.getClickedBlock().getState();
			Block chest = getBlockSignAttachedTo(e.getClickedBlock());
			if (chest != null && chest.getType() == Material.CHEST) {
				Material material = null;
				int amount = -1;
				double price = -1;
				for (Material m : aliases.keySet()) {
					if (aliases.get(m).contains(sign.getLine(3))) {
						material = m;
						amount = Integer.parseInt(sign.getLine(2));
						price = Integer.parseInt(sign.getLine(1).substring(2));
					}
				}
				if (material != null && amount != -1 && amount <= 64 && price != -1) {
					Chest chest1 = (Chest) chest.getState();
					
					XPlayer player = PlayerManager.getXPlayer(e.getPlayer());
					XPlayer owner = PlayerManager.getXPlayer(PlayerManager.getPlayer(ChatColor.stripColor(sign.getLine(0))));
					
					if (e.getPlayer().getUniqueId().equals(PlayerManager.getPlayer(ChatColor.stripColor(sign.getLine(0))))) {
						e.getPlayer().openInventory(chest1.getInventory());
						return;
					}
					
					boolean sell = sign.getLine(1).substring(0, 1).equalsIgnoreCase("S");
					boolean buy = sign.getLine(1).substring(0, 1).equalsIgnoreCase("B");
					
					if (sell) {
						//check if owner has money && the chest has space
						if (owner.getBalance() >= price) {
							if (chest1.getInventory().firstEmpty() != -1) {
								if (e.getPlayer().getInventory().contains(material, amount)) {
									
									player.addBalance(price);
									owner.addBalance(-price);
									
									int i = 0;
									
									while (i != amount) {
										int number = e.getPlayer().getInventory().first(material);
										i += e.getPlayer().getInventory().getItem(number).getAmount();
										e.getPlayer().getInventory().setItem(number, new ItemStack(Material.AIR));
									}
									chest1.getInventory().addItem(new CustomItem(material).setCustomAmount(amount));
									
								} else {
									//send message player has no space
									e.getPlayer().sendMessage("§6§lSystem §8§l»» §7Shop requires more of the item to sell.");
								}
							} else {
								//send message no space
								e.getPlayer().sendMessage("§6§lSystem §8§l»» §7Shop does not have enough space.");
							}
						} else {
							//send message no money
							e.getPlayer().sendMessage("§6§lSystem §8§l»» §7Shop owner does not have enough money.");
						}
						
					} else if (buy) {
						//check if the chest has more of the block
						if (chest1.getInventory().contains(material, amount)) {
							if (e.getPlayer().getInventory().firstEmpty() != -1) {
								if (player.getBalance() >= price) {
									if (material != Material.TRIPWIRE_HOOK) {
										player.addBalance(-price);
										owner.addBalance(price);
										
										chest1.getInventory().remove(new CustomItem(material).setCustomAmount(amount));
										e.getPlayer().getInventory().addItem(new CustomItem(material).setCustomAmount(amount));
									}
								} else {
									e.getPlayer().sendMessage("§6§lSystem §8§l»» §7You do not have enough money.");
								}
							} else {
								e.getPlayer().sendMessage("§6§lSystem §8§l»» §7Your inventory is full.");
							}
						} else {
							e.getPlayer().sendMessage("§6§lSystem §8§l»» §7Shop is full.");
						}
						
					}
					
				}
			}
			
		}
	}
	
}
