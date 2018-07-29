////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.listeners;
// made by reeve
// on 12:52 PM

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchantDrop implements Listener {
	
	@EventHandler
	public void eDrop(InventoryClickEvent e) {
		
		Player player = (Player) e.getWhoClicked();
		Inventory inventory = e.getView().getBottomInventory();
		ItemStack itemOnCursor = e.getCursor(); //Ench book
		ItemStack itemInteractedWith = e.getCurrentItem(); //Item to be enchanted or repaired
		
		if (e.getAction().equals(InventoryAction.SWAP_WITH_CURSOR)) {
			if (inventory.getType().equals(InventoryType.PLAYER) ||
					inventory.getType().equals(InventoryType.CRAFTING) ||
					inventory.getType().equals(InventoryType.CREATIVE)) {
				if (itemOnCursor.getType().equals(Material.BOOK)) {
					
					int ret = canEnchantWithBook(itemOnCursor, itemInteractedWith);
					
					if (ret == 1) {
						successFail(e, itemInteractedWith, itemOnCursor, player, false, "");
						if (itemInteractedWith.hasItemMeta() && itemInteractedWith.getItemMeta().hasLore()) {
							if (itemInteractedWith.getItemMeta().getLore().contains(CEnchant.XWY.getName())) {
								ItemMeta meta = itemInteractedWith.getItemMeta();
								meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3&l«&9&l&kk&3&l» &6&lXwy's Boots &3&l«&9&l&kf&3&l»"));
								itemInteractedWith.setItemMeta(meta);
							}
						}
					} else if (ret == 2) {
						ItemMeta emeta = itemOnCursor.getItemMeta();
						ItemMeta tmeta = itemInteractedWith.getItemMeta();
						
						String old = "";
						
						List<String> lore = tmeta.getLore();
						if (lore.contains(emeta.getDisplayName().substring(0, emeta.getDisplayName().length() - 1))) {
							old = emeta.getDisplayName().substring(0, emeta.getDisplayName().length() - 1);
						} else if (lore.contains(emeta.getDisplayName().substring(0, emeta.getDisplayName().length() - 2))) {
							old = emeta.getDisplayName().substring(0, emeta.getDisplayName().length() - 2);
						}
						
						successFail(e, itemInteractedWith, itemOnCursor, player, true, old);
					} else if (ret == 3) {
						successFail(e, itemInteractedWith, itemOnCursor, player, false, "");
					}
				}
			}
		}
	}
	
	private int canEnchantWithBook(ItemStack enchantmentBook, ItemStack itemToEnchant) {
		
		if (itemToEnchant.getItemMeta().hasLore()) {
			if (checkCan(enchantmentBook.getItemMeta().getDisplayName(), itemToEnchant.getType())) {
				
				
				if (enchantmentBook.getItemMeta().getDisplayName().equalsIgnoreCase(CEnchant.FORTUNEV.getName())) {
					
					if (itemToEnchant.getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
						if (!(itemToEnchant.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 5)) {
							return 3;
						}
					} else {
						return 3;
					}
					
					
				} else {
					
					List<String> meta = itemToEnchant.getItemMeta().getLore();
					String ench = enchantmentBook.getItemMeta().getDisplayName();
					boolean conflictFound = false;
					
					
					if (meta.contains(ench)) {
						conflictFound = true;
					}
					
					if (!conflictFound) {
						if (ench.charAt(ench.length() - 1) == 'I') {
							String slvl = ench.substring(ench.indexOf("I"), ench.length());
							int nlvl = 0;
							
							if (slvl.equalsIgnoreCase("I")) {
								nlvl = 1;
							} else if (slvl.equalsIgnoreCase("II")) {
								nlvl = 2;
							} else if (slvl.equalsIgnoreCase("III")) {
								nlvl = 3;
							}
							
							
							if (nlvl == 1) {
								String upper = ench.replaceAll("I", "II");
								if (meta.contains(upper) || meta.contains(upper + "I")) {
									return 0;
								}
							} else if (nlvl == 2) {
								String lower = ench.replace(ench.substring(ench.indexOf("I"), ench.length()), "I");
								String upper = ench.replaceAll("II", "III");
								
								if (meta.contains(upper)) {
									return 0;
								}
								if (meta.contains(lower)) {
									return 2;
								}
							} else if (nlvl == 3) {
								String lower = ench.replace(ench.substring(ench.indexOf('I'), ench.length()), "I");
								if (meta.contains(lower) || meta.contains(lower + "I")) {
									return 2;
								}
							}
							
						}
						return 1;
					}
				}
			}
		} else if (checkCan(enchantmentBook.getItemMeta().getDisplayName(), itemToEnchant.getType())) {
			return 1;
		}
		return 0;
	}
	
	private void successFail(InventoryClickEvent e, ItemStack itemInteractedWith, ItemStack itemOnCursor, Player player, boolean removeOld, String old) {
		
		List<String> lore = itemOnCursor.getItemMeta().getLore();
		for (String string : lore) {
			
			if (string.contains(ChatColor.GREEN + "Success:")) {
				string = string.substring(string.indexOf(" ") + 3, string.indexOf("%"));
				Integer randomChance = Integer.parseInt(string);
				Integer randomNumber = getRandomNumberFrom(1, 100);
				if (randomNumber < randomChance) {
					e.setCancelled(true);
					
					//bow check
					
					if (itemInteractedWith.getType().equals(Material.BOW)) {
						if (itemInteractedWith.hasItemMeta()) {
							if (itemInteractedWith.getItemMeta().hasLore()) {
								boolean found = false;
								for (String s : itemInteractedWith.getItemMeta().getLore()) {
									if (s.contains("Mode:")) {
										found = true;
									}
								}
								if (!found) {
									if (enchant(itemOnCursor, itemInteractedWith)) {
										player.setItemOnCursor(null);
										player.sendMessage(Messages.itemSuccess.get());
									}
									List<String> l = itemInteractedWith.getItemMeta().getLore();
									ItemMeta m = itemInteractedWith.getItemMeta();
									l.add(ChatColor.GRAY + "Mode: Default");
									m.setLore(l);
									itemInteractedWith.setItemMeta(m);
								} else {
									List<String> l = itemInteractedWith.getItemMeta().getLore();
									ItemMeta m = itemInteractedWith.getItemMeta();
									for (String s : itemInteractedWith.getItemMeta().getLore()) {
										if (s.contains("Mode:")) {
											l.remove(s);
											m.setLore(l);
											itemInteractedWith.setItemMeta(m);
											if (enchant(itemOnCursor, itemInteractedWith)) {
												player.setItemOnCursor(null);
												player.sendMessage(Messages.itemSuccess.get());
											}
											l = itemInteractedWith.getItemMeta().getLore();
											l.add(ChatColor.GRAY + "Mode: Default");
											m.setLore(l);
											itemInteractedWith.setItemMeta(m);
										}
									}
								}
							}
						} else {
							if (enchant(itemOnCursor, itemInteractedWith)) {
								player.setItemOnCursor(null);
								player.sendMessage(Messages.itemSuccess.get());
							}
							List<String> l = itemInteractedWith.getItemMeta().getLore();
							ItemMeta m = itemInteractedWith.getItemMeta();
							l.add(ChatColor.GRAY + "Mode: Default");
							m.setLore(l);
							itemInteractedWith.setItemMeta(m);
						}
					} else {
						ItemMeta meta = itemInteractedWith.getItemMeta();
						if (removeOld) {
							List<String> l = meta.getLore();
							if (l.contains(old)) {
								l.remove(old);
							}
							meta.setLore(l);
						}
						itemInteractedWith.setItemMeta(meta);
						
						if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(CEnchant.FORTUNEV.getName())) {
							itemInteractedWith.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
							itemInteractedWith.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
							player.setItemOnCursor(null);
							player.sendMessage(Messages.itemSuccess.get());
						} else if (enchant(itemOnCursor, itemInteractedWith)) {
							player.setItemOnCursor(null);
							player.sendMessage(Messages.itemSuccess.get());
						}
					}
				} else {
					for (String s : lore) {
						if (s.contains(ChatColor.RED + "Destroy:")) {
							s = s.substring(s.indexOf(" ") + 3, s.indexOf("%"));
							Integer randomChance1 = Integer.parseInt(s);
							Integer randomNumber1 = getRandomNumberFrom(1, 100);
							if (randomNumber1 < randomChance1) {
								e.setCancelled(true);
								player.setItemOnCursor(null);
								
								player.sendMessage(Messages.itemBroke.get());
								
								removeItemFromSlot(player, e.getSlot());
								
								player.closeInventory();
							} else {
								e.setCancelled(true);
								player.setItemOnCursor(null);
								player.sendMessage(Messages.itemUnsuccessful.get());
							}
							return;
						}
					}
				}
			}
		}
		
	}
	
	private boolean checkCan(String name, Material type) {
		
		if (name != null) {
			
			for (CEnchant e : CEnchant.values()) {
				if (e.getName().equalsIgnoreCase(name)) {
					if (e.checkSets(type)) {
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
	public static int getRandomNumberFrom(int min, int max) {
		
		Random r = new Random();
		return r.nextInt((max + 1) - min) + min;
	}
	
	private boolean enchant(ItemStack itemOnCursor, ItemStack iTW) {
		
		if (!itemOnCursor.getType().equals(Material.AIR)) {
			if (itemOnCursor.hasItemMeta()) {
				if (itemOnCursor.getItemMeta().hasDisplayName()) {
					if (iTW.getItemMeta().hasLore()) {
						List<String> lore = iTW.getItemMeta().getLore();
						ItemMeta meta = iTW.getItemMeta();
						lore.add(itemOnCursor.getItemMeta().getDisplayName());
						meta.setLore(lore);
						iTW.setItemMeta(meta);
						return true;
					} else {
						List<String> lore = new ArrayList<>();
						ItemMeta meta = iTW.getItemMeta();
						lore.add(itemOnCursor.getItemMeta().getDisplayName());
						meta.setLore(lore);
						iTW.setItemMeta(meta);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private void removeItemFromSlot(Player player, int slot) {
		
		player.getInventory().setItem(slot, new ItemStack(Material.AIR));
	}
}
