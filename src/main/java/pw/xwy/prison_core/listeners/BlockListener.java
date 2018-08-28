package pw.xwy.prison_core.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.commands.AutosellCommand;
import pw.xwy.prison_core.events.BlockMinedEvent;
import pw.xwy.prison_core.utility.FortuneCalc;
import pw.xwy.prison_core.utility.enums.*;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.mine.Mine;
import pw.xwy.prison_core.utility.mine.MineManager;
import pw.xwy.prison_core.utility.misc_managers.ExperienceManager;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.player.XPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!e.isCancelled()) {
			Mine mi = null;
			for (Rank rank : MineManager.mines.keySet()) {
				Mine mine = MineManager.mines.get(rank);
				if (mine.getArea() != null) {
					if (mine.getArea().contains(e.getBlock())) {
						mi = mine;
						break;
					}
				}
			}
			if (mi == null) {
				for (ExtraRank rank : MineManager.extraMines.keySet()) {
					Mine mine = MineManager.extraMines.get(rank);
					if (mine.getArea() != null) {
						if (mine.getArea().contains(e.getBlock())) {
							mi = mine;
							break;
						}
					}
				}
			}
			
			
			if (mi == null) {
				if (NormalWarps.SPAWN.getLocation() == null) {
					if (!(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {
						e.setCancelled(true);
					}
				} else if (e.getBlock().getWorld().getName().equalsIgnoreCase(NormalWarps.SPAWN.getLocation().getWorld().getName())) {
					if (!(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {
						e.setCancelled(true);
					}
				}
				return;
			}
			e.setCancelled(true);
			BlockMinedEvent.call(e.getBlock(), e.getPlayer(), mi, true);
		}
	}
	
	@EventHandler
	public void onLogBreak(BlockBreakEvent e) {
		
		if (ItemSets.AXE.setContains(e.getPlayer().getItemInHand().getType())) {
			
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
				}
			} else {
				e.getBlock().setType(Material.AIR);
			}
		}
	}
	
	private boolean shouldAdd(Material mat, ItemStack is) {
		
		if (mat == Material.BEDROCK || mat == Material.COMMAND || mat == Material.ENDER_PORTAL || mat == Material.ENDER_PORTAL_FRAME || mat == Material.LAVA || mat == Material.STATIONARY_LAVA || mat == Material.WATER || mat == Material.STATIONARY_WATER) {
			return false;
		} else if (mat == Material.OBSIDIAN) {
			return (is != null) && is.getType() == Material.DIAMOND_PICKAXE;
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
			return (is != null) && (is.getType() == Material.WOOD_PICKAXE || is.getType() == Material.STONE_PICKAXE ||
					is.getType() == Material.IRON_PICKAXE || is.getType() == Material.DIAMOND_PICKAXE);
		} else if (mat == Material.IRON_BLOCK || mat == Material.IRON_ORE || mat == Material.LAPIS_BLOCK || mat ==
				Material.LAPIS_ORE) {
			return (is != null) && (is.getType() == Material.STONE_PICKAXE || is.getType() == Material.IRON_PICKAXE || is.getType() ==
					Material.DIAMOND_PICKAXE);
		} else if (mat == Material.DIAMOND_ORE || mat == Material.EMERALD_ORE || mat == Material.GOLD_ORE || mat ==
				Material.REDSTONE_ORE || mat == Material.GLOWING_REDSTONE_ORE) {
			return (is != null) && (is.getType() == Material.IRON_PICKAXE || is.getType() == Material.DIAMOND_PICKAXE);
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
	
	@EventHandler
	public void onSpecialEvent(BlockMinedEvent e) {
		if (e.getMine() == null || e.getBlock().getType() == Material.BEDROCK || e.getBlock().getType() == Material.AIR) {
			return;
		}
		ItemStack tool = e.getPlayer().getItemInHand();
		boolean hasSmelt = CEnchant.hasEnchant(e.getPlayer().getItemInHand(), CEnchant.SMELTING);
		int fortune = 0;
		if (tool != null && tool.hasItemMeta() && tool.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
			fortune = tool.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);
		}
		ArrayList<ItemStack> drops = new ArrayList<>(getDrop(e.getBlock(), hasSmelt, fortune != 0, fortune, e.getBlock().getData(), tool));
		XPlayer player = PlayerManager.getXPlayer(e.getPlayer());
		
		if (e.getBlock().getType() == Material.COAL_ORE || e.getBlock().getType() == Material.REDSTONE_ORE || e.getBlock().getType() == Material.LAPIS_ORE || e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.EMERALD_ORE) {
			int xp = 0;
			Random random = new Random();
			switch (e.getBlock().getType()) {
				case DIAMOND_ORE:
					xp = random.nextInt(5) + 3;
					break;
				case EMERALD_ORE:
					xp = random.nextInt(5) + 3;
					break;
				case COAL_ORE:
					xp = random.nextInt(3);
					break;
				case LAPIS_ORE:
					xp = random.nextInt(4) + 2;
					break;
				case QUARTZ_ORE:
					xp = random.nextInt(4) + 2;
					break;
				case REDSTONE_ORE:
					xp = random.nextInt(5) + 1;
					break;
			}
			ExperienceManager experienceManager = new ExperienceManager(player);
			experienceManager.setTotalExperience(experienceManager.getTotalExperience() + xp + 1);
		}
		
		e.getBlock().setType(Material.AIR);
		
		if (AutosellCommand.toggled.contains(e.getPlayer().getUniqueId())) {
			//if toggled autosell
			Double total = 0.0;
			for (int i = 0; i < drops.size(); i++) {
				if (drops.get(i) != null) {
					if (e.getMine().shopContains(drops.get(i).getType(), drops.get(i).getDurability())) {
						total += e.getMine().shopPriceFor(drops.get(i).getType(), drops.get(i).getDurability()) * drops.get(i).getAmount();
						drops.set(i, new CustomItem(drops.get(i)).setCusomType(Material.AIR).setDurability((int) drops.get(i).getDurability()));
					}
				}
				
			}
			player.getData().addBalance(total * player.getSellMultuplier());
			
		} else {
			//if no autosell
			for (ItemStack i : drops) {
				if (i.getType() == Material.INK_SACK) {
					e.getPlayer().getInventory().addItem(new CustomItem(i).setCusomType(Material.AIR).setDurability((int) i.getDurability()));
				}
				e.getPlayer().getInventory().addItem(i);
			}
		}
		
		
		if (e.isOriginal()) {
			//harded handle
			if (e.getPlayer().getInventory().firstEmpty() == -1) {
				e.getPlayer().sendMessage("§cInventory is full!");
			}
			//explosive handle
			if (CEnchant.hasEnchant(e.getPlayer().getItemInHand(), CEnchant.EXPLOSIVEPICKI)) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						for (int k = -1; k <= 1; k++) {
							Location temp = e.getBlock().getLocation();
							Location location = new Location(e.getBlock().getWorld(), temp.getX() + i, temp.getY() + j, temp.getZ() + k);
							Mine mine = null;
							if (e.getMine().getArea().contains(location)) {
								mine = e.getMine();
							}
							if (!(i == 0 && j == 0 && k == 0)) {
								BlockMinedEvent.call(location.getBlock(), e.getPlayer(), mine, false);
							}
						}
					}
				}
			} else if (CEnchant.hasEnchant(e.getPlayer().getItemInHand(), CEnchant.EXPLOSIVEPICKII)) {
				for (int i = -2; i <= 2; i++) {
					for (int j = -2; j <= 2; j++) {
						for (int k = -2; k <= 2; k++) {
							
							Location temp = e.getBlock().getLocation();
							Location location = new Location(e.getBlock().getWorld(), temp.getX() + i, temp.getY() + j, temp.getZ() + k);
							Mine mine = null;
							if (e.getMine().getArea().contains(location)) {
								mine = e.getMine();
							}
							if (!(i == 0 && j == 0 && k == 0)) {
								BlockMinedEvent.call(location.getBlock(), e.getPlayer(), mine, false);
							}
						}
					}
				}
			} else if (CEnchant.hasEnchant(e.getPlayer().getItemInHand(), CEnchant.EXPLOSIVEPICKIII)) {
				for (int i = -3; i <= 3; i++) {
					for (int j = -3; j <= 3; j++) {
						for (int k = -3; k <= 3; k++) {
							
							Location temp = e.getBlock().getLocation();
							Location location = new Location(e.getBlock().getWorld(), temp.getX() + i, temp.getY() + j, temp.getZ() + k);
							Mine mine = null;
							if (e.getMine().getArea().contains(location)) {
								mine = e.getMine();
							}
							if (!(i == 0 && j == 0 && k == 0)) {
								BlockMinedEvent.call(location.getBlock(), e.getPlayer(), mine, false);
							}
						}
					}
				}
			}
			
			
		} else {
			e.getBlock().getLocation().getWorld().playEffect(e.getBlock().getLocation(), Effect.STEP_SOUND, e.getBlock().getTypeId());
		}
		e.getPlayer().updateInventory();
		
	}
	
	private List<ItemStack> getDrop(Block b, boolean smelt, boolean fortune, int lvl, byte data, ItemStack it) {
		
		Material type = b.getType();
		List<ItemStack> drops = new ArrayList<>();
		if (shouldAdd(b.getType(), it)) {
			if (smelt) {
				if (type.equals(Material.GOLD_ORE) || type.equals(Material.IRON_ORE) || type.equals(Material.COBBLESTONE)) {
					drops.add(smelting(type, getAmount(fortune, lvl, b.getType())));
				} else {
					if (b.getType() == Material.LAPIS_ORE) data = 4;
					
					for (ItemStack j : b.getDrops()) {
						drops.add(new CustomItem(new ItemStack(j.getType(), getAmount(fortune, lvl, b.getType()), data)).setDurability((int) data));
					}
				}
			} else {
				if (b.getType() == Material.LAPIS_ORE) data = 4;
				
				for (ItemStack j : b.getDrops()) {
					drops.add(new CustomItem(new ItemStack(j.getType(), getAmount(fortune, lvl, b.getType()), data)).setDurability((int) data));
				}
			}
		}
		return drops;
	}
	
	private ItemStack smelting(Material type, int amount) {
		
		if (type.equals(Material.GOLD_ORE)) {
			return new ItemStack(Material.GOLD_INGOT, amount);
		} else if (type.equals(Material.IRON_ORE)) {
			return new ItemStack(Material.IRON_INGOT, amount);
		} else if (type.equals(Material.COBBLESTONE)) {
			return new ItemStack(Material.STONE);
		}
		return new ItemStack(Material.AIR);
	}
	
	private int getAmount(boolean fortune, int forLevel, Material type) {
		if (fortune) return FortuneCalc.numDroppedFromFortune(forLevel, type, 1);
		return 1;
	}
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (!e.isCancelled()) {
			//TODO: add support for cells
			if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
				return;
			}
			
			if (NormalWarps.SPAWN.getLocation() == null) {
				for (Mine mine : MineManager.mines.values()) {
					if (mine.getArea().contains(e.getBlockPlaced())) {
						return;
					}
				}
				e.setCancelled(true);
				
				
			} else if (e.getBlock().getWorld().getName().equalsIgnoreCase(NormalWarps.SPAWN.getLocation().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
	}
}
