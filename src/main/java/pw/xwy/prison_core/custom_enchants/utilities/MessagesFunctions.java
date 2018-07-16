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
// on 5:32 PM

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.custom_enchants.enums.Messages;

public class MessagesFunctions {
	
	private JavaPlugin main;
	
	public MessagesFunctions(JavaPlugin main) {
		this.main = main;
	}
	
	public static String modeChanged(String mode) {
		return Messages.prefix.get() + ChatColor.GRAY + "Bow firing mode changed to " + ChatColor.GREEN + mode;
	}
	
}
