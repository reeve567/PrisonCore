package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomBlockEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

import java.util.ArrayList;
import java.util.List;

public class Lumberjack extends CustomBlockEnchant {

	public Lumberjack(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		super(name, sets, rarity, description, displayItem);
	}

	@Override
	public void event(BlockBreakEvent e) {
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
}
