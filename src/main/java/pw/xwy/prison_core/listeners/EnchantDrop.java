////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.listeners;
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
import pw.xwy.prison_core.RealName;
import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.CustomEnchantManager;
import pw.xwy.prison_core.utility.ce.CustomEnchantsManager;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.item.VoucherUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchantDrop implements Listener {

	private final int CANT_ENCHANT = 0;
	private final int HAS_LOWER = 2;
	private final int HAS_HIGHER = 6;
	private final int HAS_SAME = 1;
	private final int CAN_ENCHANT = 10;
	private final int ADMIN_ENCHANT = 5;

	//TODO: make this manageable

	public static int getRandomNumberFrom(int min, int max) {

		Random r = new Random();
		return r.nextInt((max + 1) - min) + min;
	}

	/*
	@EventHandler
	public void onDrop(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		ItemStack cursor = e.getCursor();
		ItemStack slot = e.getCurrentItem();

		if (e.getAction() != InventoryAction.SWAP_WITH_CURSOR) {
			return;
		}

		if (cursor == null) {
			return;
		}

		if (cursor.getType() == Material.BOOK) {


		}

	}
	*/
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

					int ret = canEnchant(itemOnCursor, itemInteractedWith);

					if (ret == ADMIN_ENCHANT) {
						successFail(e, itemInteractedWith, itemOnCursor, player, false, "");
						if (itemInteractedWith.hasItemMeta() && itemInteractedWith.getItemMeta().hasLore()) {
							if (itemInteractedWith.getItemMeta().getLore().contains(RealName.XWY.getEnchant().getName())) {
								ItemMeta meta = itemInteractedWith.getItemMeta();
								meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3&l«&9&l&kk&3&l» &6&lXwy's Boots &3&l«&9&l&kf&3&l»"));
								itemInteractedWith.setItemMeta(meta);
							}
						}
					} else if (ret == HAS_LOWER) {
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
					} else if (ret == CAN_ENCHANT) {
						successFail(e, itemInteractedWith, itemOnCursor, player, false, "");
					}
				} else if (itemOnCursor.getType().equals(Material.PAPER)) {
					CustomItem scroll = VoucherUtility.getScroll();

					if (itemOnCursor.hasItemMeta() && itemOnCursor.getItemMeta().hasDisplayName() && itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(scroll.getItemMeta().getDisplayName())) {
						if (ItemSets.EVERYTHING.setContains(itemInteractedWith.getType())) {

							if (itemOnCursor.getAmount() != 1) {
								player.setItemOnCursor(new CustomItem(itemOnCursor).setCustomAmount(itemOnCursor.getAmount() - 1));
							} else {
								player.setItemOnCursor(null);
							}
							if (itemInteractedWith.hasItemMeta() && itemInteractedWith.getItemMeta().hasLore()) {
								List<String> strings = itemInteractedWith.getItemMeta().getLore();
								strings.add("§f§lScroll");
								ItemMeta meta = itemInteractedWith.getItemMeta();
								meta.setLore(strings);
								itemInteractedWith.setItemMeta(meta);
							} else {
								List<String> strings = new ArrayList<>();
								strings.add("§f§lScroll");
								ItemMeta meta = itemInteractedWith.getItemMeta();
								meta.setLore(strings);
								itemInteractedWith.setItemMeta(meta);
							}
						}
					}

				}
			}
		}
	}

	private int canEnchant(ItemStack book, ItemStack item) {
		if (item == null) {
			return CANT_ENCHANT;
		}
		if (book.hasItemMeta() && book.getItemMeta().hasLore() && book.getItemMeta().hasDisplayName() && checkCan(book.getItemMeta().getDisplayName(), item.getType())) {
			//find custom enchant
			CustomEnchant customEnchant = CustomEnchantManager.getInstance().getEnchantsByLore().get(book.getItemMeta().getDisplayName());

			if (customEnchant == null) {
				return CANT_ENCHANT;
			}

			if (customEnchant == RealName.XWY.getEnchant()) {
				return ADMIN_ENCHANT;
			}

			//if the item to be enchanted has item meta, check for conflicts
			if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
				//check for conflicts
				//see if there can be conflicts

				List<String> meta = item.getItemMeta().getLore();

				for (int i = 0; i < meta.size(); i++) {
					meta.set(0, ChatColor.stripColor(meta.get(0)));
				}

				String ench = ChatColor.stripColor(book.getItemMeta().getDisplayName());
				if (meta.contains(ench)) {
					return HAS_SAME;
				}

				if (customEnchant.getName().charAt(customEnchant.getName().length() - 1) == 'I') {
					String slvl = ench.substring(ench.indexOf('I'));
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
							return HAS_HIGHER;
						}
					} else if (nlvl == 2) {
						String lower = ench.replaceAll("II", "I");
						String upper = ench.replaceAll("II", "III");

						if (meta.contains(upper)) {
							return HAS_HIGHER;
						}
						if (meta.contains(lower)) {
							return HAS_LOWER;
						}
					} else if (nlvl == 3) {
						String lowest = ench.replaceAll("III", "I");
						String low = ench.replaceAll("III", "II");
						if (meta.contains(lowest) || meta.contains(low)) {
							return HAS_LOWER;
						}
					}

				}
				return CAN_ENCHANT;
			} else {
				return CAN_ENCHANT;
			}

		}
		return CANT_ENCHANT;
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
							boolean found = false;
							if (itemInteractedWith.getItemMeta().hasLore()) {
								for (String s : itemInteractedWith.getItemMeta().getLore()) {
									if (s.contains("Mode:")) {
										found = true;
										break;
									}
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
										break;
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
							l.remove(old);
							meta.setLore(l);
						}
						itemInteractedWith.setItemMeta(meta);

						if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.FORTUNEV.getEnchant().getName())) {
							if (itemInteractedWith.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
								if (itemInteractedWith.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) == 5)
									return;
							}
							itemInteractedWith.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
							itemInteractedWith.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
							player.setItemOnCursor(null);
							player.sendMessage(Messages.itemSuccess.get());
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYI.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 1);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYII.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 2);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYIII.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 3);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYIV.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 4);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYV.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 5);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYVI.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 6);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYVII.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 7);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYVIII.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 8);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYIX.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 9);
						} else if (itemOnCursor.getItemMeta().getDisplayName().equalsIgnoreCase(RealName.EFFICIENCYX.getEnchant().getName())) {
							effEnch(player, itemInteractedWith, 10);
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

								if (itemInteractedWith.hasItemMeta() && itemInteractedWith.getItemMeta().hasLore() && itemInteractedWith.getItemMeta().getLore().contains(VoucherUtility.getScroll().getItemMeta().getDisplayName())) {
									ArrayList<String> strings = (ArrayList<String>) itemInteractedWith.getItemMeta().getLore();
									strings.remove(VoucherUtility.getScroll().getItemMeta().getDisplayName());
									ItemMeta meta = itemInteractedWith.getItemMeta();
									meta.setLore(strings);
									itemInteractedWith.setItemMeta(meta);

									player.sendMessage(Messages.prefix.get() + "§7Your scroll prevented your item from breaking and was removed from your item.");
								} else {
									removeItemFromSlot(player, e.getSlot());
								}
							} else {
								e.setCancelled(true);
								player.setItemOnCursor(null);
								player.sendMessage(Messages.itemUnsuccessful.get());
							}
							break;
						}
					}
				}
				break;
			}
		}

	}

	private boolean checkCan(String name, Material type) {

		if (name != null) {
			return CustomEnchantsManager.manager.getEnchantsByName().get(name).checkSets(type);
		}
		return false;
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

	public void effEnch(Player player, ItemStack pick, int i) {
		if (pick.getItemMeta().hasEnchant(Enchantment.DIG_SPEED)) {
			if (pick.getItemMeta().getEnchantLevel(Enchantment.DIG_SPEED) >= i)
				return;
		}
		pick.removeEnchantment(Enchantment.DIG_SPEED);
		pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, i);
		player.sendMessage(Messages.itemSuccess.get());
		player.setItemOnCursor(null);
	}
}
