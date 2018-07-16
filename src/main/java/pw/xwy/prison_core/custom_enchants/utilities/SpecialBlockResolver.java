////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.utilities;
// made by reeve
// on 10:45 PM


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator: Revolution
 * Project: DropsToInv
 * Date: 6/16/2014, 10:22 AM
 * Usage: Resolves the other part of the "bed" item in minecraft as well as other multiblock structures such as doors
 */
public class SpecialBlockResolver {
	
	public static Location getBed(Location l) {
		return checkForMaterial(l, Material.BED_BLOCK);
	}
	
	private static Location checkForMaterial(Location l, Material mat) {
		Location returnV = null;
		List<Location> locs = new ArrayList<Location>();
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY(), l.getZ()));
		locs.add(new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() + 1));
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY(), l.getZ() + 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY(), l.getZ()));
		locs.add(new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY(), l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY(), l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY(), l.getZ() + 1));
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY() + 1, l.getZ()));
		locs.add(new Location(l.getWorld(), l.getX(), l.getY() + 1, l.getZ() + 1));
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY() + 1, l.getZ() + 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY() + 1, l.getZ()));
		locs.add(new Location(l.getWorld(), l.getX(), l.getY() + 1, l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY() + 1, l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY() + 1, l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY() + 1, l.getZ() + 1));
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY() - 1, l.getZ()));
		locs.add(new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ() + 1));
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY() - 1, l.getZ() + 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY() - 1, l.getZ()));
		locs.add(new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY() - 1, l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() + 1, l.getY() - 1, l.getZ() - 1));
		locs.add(new Location(l.getWorld(), l.getX() - 1, l.getY() - 1, l.getZ() + 1));
		for (Location ll : locs) {
			Block b = ll.getBlock();
			if (b.getType() == mat) {
				returnV = ll;
				break;
			}
		}
		return returnV;
	}
	
	public static Location getDoor(Location l) {
		Location returnV = checkForMaterial(l, Material.IRON_DOOR_BLOCK);
		if (returnV == null)
			returnV = checkForMaterial(l, Material.WOODEN_DOOR);
		return returnV;
	}
	
	public static Location getPiston(Location l) {
		Location returnV = checkForMaterial(l, Material.PISTON_BASE);
		if (returnV == null) {
			returnV = checkForMaterial(l, Material.PISTON_EXTENSION);
			if (returnV == null) {
				returnV = checkForMaterial(l, Material.PISTON_MOVING_PIECE);
				if (returnV == null) {
					returnV = checkForMaterial(l, Material.PISTON_STICKY_BASE);
					return returnV;
				} else {
					return returnV;
				}
			} else {
				return returnV;
			}
		} else {
			return returnV;
		}
	}
}