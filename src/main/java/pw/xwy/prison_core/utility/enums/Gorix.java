////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility.enums;
// made by reeve
// on 6:47 PM

import org.bukkit.ChatColor;

public enum Gorix {
	GORIX(ChatColor.GREEN + "" + ChatColor.BOLD + "Rubix"),
	COMMONCRATE(Rarity.COMMON.getLabel() + " Crate"),;
	
	private final String value;
	
	Gorix(String value) {
		this.value = value;
	}
	
	public String get() {
		return value;
	}
}
