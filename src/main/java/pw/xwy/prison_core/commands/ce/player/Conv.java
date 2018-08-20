////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 6/17/18 12:12 PM                            /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.commands.ce.player;

import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.ce.ConversionMenu;

public class Conv {
	
	public static void run(Player player) {
		new ConversionMenu().openInventory(player);
	}
	
	
}
