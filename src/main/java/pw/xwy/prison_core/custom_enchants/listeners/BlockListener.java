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
// on 7:36 PM

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.ItemSets;
import pw.xwy.prison_core.custom_enchants.utilities.FortuneCalc;

import java.util.ArrayList;
import java.util.List;

public class BlockListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		
		if (ItemSets.AXE.setContains(e.getPlayer().getItemInHand().getType()) || ItemSets.PICK.setContains(e.getPlayer().getItemInHand().getType())) {
			
			if (!e.getPlayer().getItemInHand().hasItemMeta() || !e.getPlayer().getItemInHand().getItemMeta().hasLore()) {
				return;
			}
			
			Player player = e.getPlayer();
			
			if (player.getGameMode() == GameMode.CREATIVE) return;
			
			if (!shouldAdd(e.getBlock().getType(), player.getItemInHand())) return;
			
			e.setCancelled(true);
			if (player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore()) {
				ItemStack i = player.getItemInHand();
				if (ItemSets.AXE.setContains(i.getType()) && i.getItemMeta().getLore().contains(CEnchant.LUMBERJACK.getName()) && e.getBlock().getType().equals(Material.LOG)) {
					List<ItemStack> drops = new ArrayList<>();
					
					int y = 0;
					Location loc = e.getBlock().getLocation();
					while (hasWood(loc.getBlockX(), loc.getBlockY() + y, loc.getBlockZ(), loc.getWorld())) {
						for (int x = -2; x <= 2; x++) {
							for (int z = -2; z <= 2; z++) {
								if (loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z).getType().equals(Material.LOG)) {
									Block b = loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z);
									drops.addAll(b.getDrops());
									b.setType(Material.AIR);
								}
							}
						}
						y++;
					}
					e.getBlock().setType(Material.AIR);
					for (ItemStack it : drops) {
						loc.getWorld().dropItemNaturally(loc, it);
					}
					return;
				}
				boolean hasMag = false;
				boolean hasExp = false;
				boolean hasSm = false;
				boolean hasFor = false;
				boolean hasSilk = false;
				int forLvl = 0;
				if (i.getItemMeta().getLore().contains(CEnchant.MAGNET.getName())) {
					hasMag = true;
				}
				if (i.getItemMeta().getLore().contains(CEnchant.SMELTING.getName())) {
					hasSm = true;
				}
				if (i.getItemMeta().getLore().contains(CEnchant.EXPLOSIVEPICK.getName())) {
					hasExp = true;
				}
				if (i.getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
					hasFor = true;
				}
				if (i.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
					hasSilk = true;
				}
				if (hasExp) {
					List<ItemStack> drops = new ArrayList<>();
					Location mainLoc = e.getBlock().getLocation();
					for (int x = -1; x <= 1; x++) {
						for (int y = -1; y <= 1; y++) {
							for (int z = -1; z <= 1; z++) {
								Block b = mainLoc.getWorld().getBlockAt(mainLoc.getBlockX() + x,
										mainLoc.getBlockY() + y,
										mainLoc.getBlockZ() + z);
								byte data = b.getData();
								if (b.getType() != Material.BEDROCK) {
									drops.addAll(getDrop(b, hasSm, hasFor, forLvl, data, e.getPlayer().getItemInHand()));
									b.setType(Material.AIR);
								}
							}
						}
					}
					if (hasMag) {
						for (ItemStack j : drops) {
							e.getPlayer().getInventory().addItem(j);
						}
					} else {
						for (ItemStack j : drops) {
							e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), j);
						}
					}
				} else {
					byte data = e.getBlock().getData();
					if (!hasMag) {
						for (ItemStack j : getDrop(e.getBlock(), hasSm, hasFor, forLvl, data, e.getPlayer().getItemInHand())) {
							e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), j);
						}
					} else {
						for (ItemStack j : getDrop(e.getBlock(), hasSm, hasFor, forLvl, data, e.getPlayer().getItemInHand())) {
							e.getPlayer().getInventory().addItem(j);
						}
					}
					
					e.getBlock().setType(Material.AIR);
					
					if (i.getItemMeta().getLore().contains(CEnchant.HARDENED.getName())) {
						int n = EnchantDrop.getRandomNumberFrom(1, 100);
						if (!(n <= 25)) {
							i.setDurability((short) (i.getDurability() + 1));
						}
					} else {
						i.setDurability((short) (i.getDurability() + 1));
					}
					player.updateInventory();
				}
				
			} else {
				e.getBlock().setType(Material.AIR);
			}
		}
	}
	
	/*
	private boolean factionBreak(Location loc, Player p) {
		try {
			Plugin plugin = Bukkit.getPluginManager().getPlugin("XFactions-Core");
			if (plugin == null || !(plugin instanceof XFactionsCore)) {
				return true;
			}
			XPlayer player = XPlayer.getXPlayer(p);
			XFaction faction = player.getFaction();
			if (faction != null && faction.claim.isInClaim(loc.getChunk()) && player.permCheck("break")) {
				return true;
			}
			return ClaimManager.getChunk(loc.getChunk()) == null;
		} catch (Exception ignored) {
		
		}
		return true;
	}
	*/
	private static boolean shouldAdd(Material mat, ItemStack is) {
		
		if (mat == Material.BEDROCK || mat == Material.COMMAND || mat == Material.ENDER_PORTAL || mat == Material.ENDER_PORTAL_FRAME || mat == Material.LAVA || mat == Material.STATIONARY_LAVA || mat == Material.WATER || mat == Material.STATIONARY_WATER) {
			return false;
		} else if (mat == Material.OBSIDIAN) {
			return is.getType() == Material.DIAMOND_PICKAXE;
		} else if (mat == Material.ENDER_CHEST || mat == Material.ANVIL || mat == Material.COAL_BLOCK
				|| mat == Material.REDSTONE_BLOCK || mat == Material.ENCHANTMENT_TABLE ||
				mat == Material.IRON_BARDING || mat == Material.IRON_DOOR_BLOCK || mat == Material.MOB_SPAWNER ||
				mat == Material.DISPENSER || mat == Material.DROPPER || mat == Material.COAL_ORE ||
				mat == Material.ENDER_STONE || mat == Material.HOPPER || mat == Material.QUARTZ_ORE ||
				mat == Material.BRICK_STAIRS || mat == Material.BRICK || mat == Material.CAULDRON ||
				mat == Material.COBBLESTONE || mat == Material.COBBLESTONE_STAIRS || mat == Material.COBBLE_WALL ||
				mat == Material.MOSSY_COBBLESTONE || mat == Material.NETHER_BRICK || mat == Material.NETHER_BRICK_STAIRS
				|| mat == Material.STONE_PLATE || (mat.getId() == 43 || mat.getId() == 44) || mat == Material.STONE ||
				(mat.getId() == 94 || mat.getId() == 109) || (mat.getId() == 159 || mat.getId() == 172) ||
				mat == Material.QUARTZ_BLOCK || mat == Material.SANDSTONE || mat == Material.SANDSTONE_STAIRS ||
				mat == Material.NETHERRACK) {
			return is.getType() == Material.WOOD_PICKAXE || is.getType() == Material.STONE_PICKAXE ||
					is.getType() == Material.IRON_PICKAXE || is.getType() == Material.DIAMOND_PICKAXE;
		} else if (mat == Material.IRON_BLOCK || mat == Material.IRON_ORE || mat == Material.LAPIS_BLOCK || mat ==
				Material.LAPIS_ORE) {
			return is.getType() == Material.STONE_PICKAXE || is.getType() == Material.IRON_PICKAXE || is.getType() ==
					Material.DIAMOND_PICKAXE;
		} else if (mat == Material.DIAMOND_ORE || mat == Material.EMERALD_ORE || mat == Material.GOLD_ORE || mat ==
				Material.REDSTONE_ORE || mat == Material.GLOWING_REDSTONE_ORE) {
			return is.getType() == Material.IRON_PICKAXE || is.getType() == Material.DIAMOND_PICKAXE;
		}
		return true;
	}
	
	private boolean hasWood(int x, int y, int z, World world) {
		
		Location loc = new Location(world, x, y, z);
		for (int xi = -2; xi <= 2; xi++) {
			for (int zi = -2; zi <= 2; zi++) {
				if (loc.getWorld().getBlockAt(new Location(world, loc.getBlockX() + xi, loc.getBlockY(), loc.getBlockZ() + zi)).getType().equals(Material.LOG)) {
					return true;
				}
			}
			
		}
		return false;
	}
	
	List<ItemStack> getDrop(Block b, boolean smelt, boolean fortune, int lvl, byte data, ItemStack it) {
		
		Material type = b.getType();
		List<ItemStack> drops = new ArrayList<ItemStack>();
		if (shouldAdd(b.getType(), it)) {
			if (smelt) {
				if (type.equals(Material.GOLD_ORE) || type.equals(Material.IRON_ORE) || type.equals(Material.COBBLESTONE) || type.equals(Material.STONE)) {
					drops.add(smelting(type, getAmount(fortune, lvl, b.getType(), true)));
				} else {
					for (ItemStack j : b.getDrops()) {
						drops.add(new ItemStack(j.getType(), getAmount(fortune, lvl, b.getType(), true), data));
					}
				}
			} else {
				for (ItemStack j : b.getDrops()) {
					drops.add(new ItemStack(j.getType(), getAmount(fortune, lvl, b.getType(), false), data));
				}
			}
		}
		return drops;
	}
	
	ItemStack smelting(Material type, int amount) {
		
		if (type.equals(Material.GOLD_ORE)) {
			return new ItemStack(Material.GOLD_INGOT, amount);
		} else if (type.equals(Material.IRON_ORE)) {
			return new ItemStack(Material.IRON_INGOT, amount);
		} else if (type.equals(Material.COBBLESTONE)) {
			return new ItemStack(Material.STONE);
		} else if (type.equals(Material.STONE)) {
			return new ItemStack(Material.STONE);
		}
		return new ItemStack(Material.AIR);
	}
	
	private int getAmount(boolean check, int forLevel, Material type, boolean smelt) {
		
		if (check) {
			if (smelt) {
				if (type.equals(Material.IRON_ORE) || type.equals(Material.GOLD_ORE)) {
					return FortuneCalc.numDroppedFromFortune(forLevel, type, 1);
				}
			}
			if (type.equals(Material.EMERALD_ORE) ||
					type.equals(Material.DIAMOND_ORE) ||
					type.equals(Material.COAL_ORE) ||
					type.equals(Material.REDSTONE_ORE) ||
					type.equals(Material.LAPIS_ORE) ||
					type.equals(Material.CARROT) ||
					type.equals(Material.POTATO) ||
					type.equals(Material.GLOWSTONE) ||
					type.equals(Material.QUARTZ_ORE) ||
					type.equals(Material.MELON_BLOCK) ||
					type.equals(Material.NETHER_WARTS)) {
				return FortuneCalc.numDroppedFromFortune(forLevel, type, 1);
			}
		}
		return 1;
	}
	
}
