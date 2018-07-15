package pw.xwy.prison_core.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Rect3D {
	private String worldName;
	private int x1, y1, z1;
	private int x2, y2, z2;
	private Block[] blocks;
	
	public Rect3D(String[] strings) {
		Location loc1 = locationFromString(strings[0], strings[2]);
		Location loc2 = locationFromString(strings[1], strings[2]);
		worldName = loc1.getWorld().getName();
		x1 = loc1.getBlockX();
		y1 = loc1.getBlockY();
		z1 = loc1.getBlockZ();
		x2 = loc2.getBlockX();
		y2 = loc2.getBlockY();
		z2 = loc2.getBlockZ();
		ArrayList<Block> blocks = new ArrayList<>();
		if (x1 < x2) {
			addBlocks(blocks, x1, x2);
		} else {
			addBlocks(blocks, x2, x1);
		}
		this.blocks = blocks.toArray(new Block[0]);
	}
	
	private Location locationFromString(String string, String world) {
		int x, y, z;
		x = Integer.parseInt(string.substring(0, string.indexOf(":")));
		string = string.substring(string.indexOf(":") + 1);
		y = Integer.parseInt(string.substring(0, string.indexOf(":")));
		string = string.substring(string.indexOf(":") + 1);
		z = Integer.parseInt(string);
		return new Location(Bukkit.getWorld(world), x, y, z);
	}
	
	private void addBlocks(ArrayList<Block> blocks, int x1, int x2) {
		for (int i = x1; i <= x2; i++) {
			if (y1 < y2) {
				addBlocks2(blocks, i, y1, y2);
			} else {
				addBlocks2(blocks, i, y2, y1);
			}
		}
	}
	
	private void addBlocks2(ArrayList<Block> blocks, int i, int y1, int y2) {
		for (int j = y1; j <= y2; j++) {
			if (z1 < z2) {
				for (int k = z1; k <= z2; k++) {
					blocks.add(getWorld().getBlockAt(i, j, k));
				}
			} else {
				for (int k = z2; k <= z1; k++) {
					blocks.add(getWorld().getBlockAt(i, j, k));
				}
			}
		}
	}
	
	public World getWorld() {
		World world = Bukkit.getWorld(worldName);
		if (world == null) {
			throw new IllegalStateException("world '" + worldName + "' is not loaded");
		}
		return world;
	}
	
	public Rect3D(Location loc1, Location loc2) {
		worldName = loc1.getWorld().getName();
		x1 = loc1.getBlockX();
		y1 = loc1.getBlockY();
		z1 = loc1.getBlockZ();
		x2 = loc2.getBlockX();
		y2 = loc2.getBlockY();
		z2 = loc2.getBlockZ();
		ArrayList<Block> blocks = new ArrayList<>();
		if (x1 < x2) {
			addBlocks(blocks, x1, x2);
		} else {
			addBlocks(blocks, x2, x1);
		}
		this.blocks = blocks.toArray(new Block[0]);
	}
	
	/**
	 * Check if the given Block is contained within this Cuboid.
	 *
	 * @param b the Block to check for
	 * @return true if the Block is within this Cuboid, false otherwise
	 */
	public boolean contains(Block b) {
		return contains(b.getLocation());
	}
	
	/**
	 * Check if the given Location is contained within this Cuboid.
	 *
	 * @param l the Location to check for
	 * @return true if the Location is within this Cuboid, false otherwise
	 */
	public boolean contains(Location l) {
		return worldName.equals(l.getWorld().getName()) && contains(l.getBlockX(), l.getBlockY(), l.getBlockZ());
	}
	
	/**
	 * Return true if the point at (x,y,z) is contained within this Cuboid.
	 *
	 * @param x the X co-ordinate
	 * @param y the Y co-ordinate
	 * @param z the Z co-ordinate
	 * @return true if the given point is within this Cuboid, false otherwise
	 */
	public boolean contains(int x, int y, int z) {
		return x >= x1 && x <= x2 && y >= y1 && y <= y2 && z >= z1 && z <= z2;
	}
	
	public int getSize() {
		return getBlocks().length;
	}
	
	public Block[] getBlocks() {
		return blocks;
	}
	
	public String[] toStrings() {
		return new String[]{x1 + ":" + y1 + ":" + z1, x2 + ":" + y2 + ":" + z2, worldName};
	}
	
	
}
