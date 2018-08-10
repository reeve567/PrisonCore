package pw.xwy.prison_core.listeners;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.BlockMinedEvent;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.listeners.EnchantDrop;
import pw.xwy.prison_core.utility.*;

import java.util.ArrayList;
import java.util.List;

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
				if (NormalWarps.SPAWN.getLocation() == null) {
					e.setCancelled(true);
				} else if (e.getBlock().getWorld().getName().equalsIgnoreCase(NormalWarps.SPAWN.getLocation().getWorld().getName())) {
					e.setCancelled(true);
				}
				return;
			}
			e.setCancelled(true);
			BlockMinedEvent.call(e.getBlock(), e.getPlayer(), mi, true);
		}
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
		e.getBlock().setType(Material.AIR);
		for (ItemStack i : drops) {
			e.getPlayer().getInventory().addItem(i);
		}
		
		
		if (e.isOriginal()) {
			//harded handle
			if (CEnchant.hasEnchant(tool, CEnchant.HARDENED)) {
				int n = EnchantDrop.getRandomNumberFrom(1, 100);
				if (!(n <= 25)) {
					tool.setDurability((short) (tool.getDurability() + 1));
				}
			} else if (tool != null) {
				tool.setDurability((short) (tool.getDurability() + 1));
			}
			
			if (e.getPlayer().getInventory().firstEmpty() == -1) {
				e.getPlayer().sendMessage("§cInventory is full!");
			}
			
			//explosive handle
			if (CEnchant.hasEnchant(e.getPlayer().getItemInHand(), CEnchant.EXPLOSIVEPICK)) {
				//break
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
			}
		} else {
			e.getBlock().getLocation().getWorld().playEffect(e.getBlock().getLocation(), Effect.STEP_SOUND, e.getBlock().getTypeId());
		}
		e.getPlayer().updateInventory();
		
	}
	
	private List<ItemStack> getDrop(Block b, boolean smelt, boolean fortune, int lvl, byte data, ItemStack it) {
		
		Material type = b.getType();
		List<ItemStack> drops = new ArrayList<ItemStack>();
		if (shouldAdd(b.getType(), it)) {
			if (smelt) {
				if (type.equals(Material.GOLD_ORE) || type.equals(Material.IRON_ORE) || type.equals(Material.COBBLESTONE)) {
					drops.add(smelting(type, getAmount(fortune, lvl, b.getType())));
				} else {
					for (ItemStack j : b.getDrops()) {
						drops.add(new ItemStack(j.getType(), getAmount(fortune, lvl, b.getType()), data));
					}
				}
			} else {
				for (ItemStack j : b.getDrops()) {
					drops.add(new ItemStack(j.getType(), getAmount(fortune, lvl, b.getType()), data));
				}
			}
		}
		return drops;
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
	
	
	/*@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (!e.isCancelled()) {
			//TODO: add support for cells
			if (NormalWarps.SPAWN.getLocation() == null) {
				e.setCancelled(true);
			} else if (e.getBlock().getWorld().getName().equalsIgnoreCase(NormalWarps.SPAWN.getLocation().getWorld().getName())) {
				e.setCancelled(true);
			}
		}
	}*/
}
