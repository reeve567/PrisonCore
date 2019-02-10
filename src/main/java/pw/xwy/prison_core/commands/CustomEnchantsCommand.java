////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.prison_core.listeners.gui.ConversionMenu;
import pw.xwy.prison_core.listeners.gui.ce.MainMenu;
import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.ce.CustomEnchantsManager;
import pw.xwy.prison_core.utility.enums.ChangeLog;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Souls;
import pw.xwy.prison_core.utility.item.VoucherUtility;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

import static pw.xwy.prison_core.utility.ce.EnchantUtility.bookGive;
import static pw.xwy.prison_core.utility.ce.EnchantUtility.cmdCheck;

public class CustomEnchantsCommand implements CommandExecutor {

	public CustomEnchantsCommand() {
		Bukkit.getServer().getPluginCommand("ce").setExecutor(this);
		Bukkit.getServer().getPluginCommand("conv").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getLabel().equalsIgnoreCase("ce")) {
			if (args.length > 0) {
				if (argCheck(args[0])) {
					if (sender.hasPermission("ce.admin") || sender.getName().equalsIgnoreCase("Xwy")) {
						if (args.length >= 3) {
							if (args[2].equalsIgnoreCase("true")) {

								CustomEnchant customEnchant = CustomEnchantsManager.manager.getEnchantsByLabel(args[1]);

								if (customEnchant != null) {
									((Player) sender).getInventory().addItem(bookGive(args[1], true));
									sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have received: " + customEnchant.getName());
								} else
									sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Unknown enchantment/soul: " + args[1]);

							} else {
								Player target = null;
								for (Player p : Bukkit.getOnlinePlayers()) {
									if (p.getName().equalsIgnoreCase(args[2])) {
										target = p;
									}
								}
								if (target != null) {
									if (args[1].equalsIgnoreCase("scroll")) {
										target.getInventory().addItem(VoucherUtility.getScroll());
										sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have given a scroll to " + target.getName());
									} else {
										boolean found = false;

										CustomEnchant customEnchant = CustomEnchantsManager.manager.getEnchantsByLabel(args[1]);

										if (customEnchant != null) {
											((Player) sender).getInventory().addItem(bookGive(args[1], false));
											sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have given " + customEnchant.getName() + ChatColor.GRAY + " to " + ChatColor.RED + target.getName());
										} else {
											for (Souls s : Souls.values()) {
												if (cmdCheck(s.getCommandLabel(), args[1])) {
													found = true;
													target.getInventory().addItem(s.getItem());
													sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have given " + s.getName() + ChatColor.GRAY + " to " +
															ChatColor.RED + target.getName());
												}
											}
										}

										if (!found && customEnchant == null) {
											sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Unknown enchantment/soul: " + args[1]);
										}
									}
								} else
									sender.sendMessage(ChatColor.RED + "Player " + ChatColor.AQUA + args[2] + " not found.");
							}
						} else if (args.length == 2) {
							if (sender instanceof Player) {
								if (args[1].equalsIgnoreCase("scroll")) {
									((Player) sender).getInventory().addItem(VoucherUtility.getScroll());
									sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have received a scroll");
								} else {
									boolean found = false;

									CustomEnchant customEnchant = CustomEnchantsManager.manager.getEnchantsByLabel(args[1]);

									if (customEnchant != null) {
										((Player) sender).getInventory().addItem(bookGive(args[1], false));
										sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have received: " + customEnchant.getName());
									} else {
										for (Souls s : Souls.values()) {
											if (cmdCheck(s.getCommandLabel(), args[1])) {
												found = true;
												((Player) sender).getInventory().addItem(s.getItem());
												sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You have received: " + s.getName());
											}
										}
									}

									if (!found && customEnchant == null) {
										sender.sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Unknown enchantment/soul: " + args[1]);
									}
								}
							} else {
								sender.sendMessage(Messages.senderIsConsole.get());
							}
						} else {
							if (sender instanceof Player) {
								sender.sendMessage(ChatColor.GRAY + "/ce give" + ChatColor.RED + " <enchant> [player]");
							} else {
								sender.sendMessage(Messages.senderIsConsole.get());
							}
						}
					}
				} else if (args[0].equalsIgnoreCase("version")) {
					sender.sendMessage(ChangeLog.getStrings().toArray(new String[0]));
				} else if (args[0].equalsIgnoreCase("organize")) {
					if (sender.hasPermission("ce.admin") || sender.getName().equalsIgnoreCase("Xwy")) {
						if (!(sender instanceof Player)) {
							return true;
						}
						Player player = (Player) sender;
						ItemMeta meta = player.getItemInHand().getItemMeta();
						List<String> lore = meta.getLore();

						List<String> common = new ArrayList<>();
						List<String> uncommon = new ArrayList<>();
						List<String> rare = new ArrayList<>();
						List<String> mystical = new ArrayList<>();
						List<String> legendary = new ArrayList<>();
						List<String> admin = new ArrayList<>();
						List<String> other = new ArrayList<>();

						for (String s : lore) {
							boolean found = false;
							for (CustomEnchant ce : CustomEnchantsManager.manager.getEnchantsByRealName().values()) {
								if (ce.getName().equalsIgnoreCase(s)) {
									found = true;
									switch (ce.getRarity()) {
										case ADMIN:
											admin.add(s);
											break;
										case COMMON:
											common.add(s);
											break;
										case UNCOMMON:
											uncommon.add(s);
											break;
										case RARE:
											rare.add(s);
											break;
										case MYSTICAL:
											mystical.add(s);
											break;
										case HYDRO:
											legendary.add(s);
											break;
									}
									break;
								}
							}
							if (!found) {
								other.add(s);
							}
						}
						sortStrings(common);
						sortStrings(uncommon);
						sortStrings(rare);
						sortStrings(mystical);
						sortStrings(legendary);
						sortStrings(admin);

						ArrayList<String> lores = new ArrayList<>();
						lores.addAll(common);
						lores.addAll(uncommon);
						lores.addAll(rare);
						lores.addAll(mystical);
						lores.addAll(legendary);
						lores.addAll(admin);
						lores.addAll(other);

						meta.setLore(lores);
						player.getItemInHand().setItemMeta(meta);
					}
				} else if (args[0].equalsIgnoreCase("enchant")) {
					if (sender.hasPermission("xwy.ce.admin")) {
						if (sender instanceof Player) {
							ItemStack stack = ((Player) sender).getItemInHand();
							stack.addUnsafeEnchantment(Enchantment.getByName(args[1]), Integer.parseInt(args[2]));
							((Player) sender).setItemInHand(stack);
							((Player) sender).updateInventory();
						}

					}


				}
			} else {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					Inventory inv = new MainMenu(player).get();
					player.openInventory(inv);
				} else
					sender.sendMessage(Messages.senderIsConsole.get());
			}
		} else if (command.getLabel().equalsIgnoreCase("conv")) {
			new ConversionMenu().openInventory((Player) sender);
		}
		return true;
	}

	public void sortStrings(List<String> strings) {
		strings.sort(Collator.getInstance());
	}

	private boolean argCheck(String sent) {
		return "give".equalsIgnoreCase(sent);
	}
}
