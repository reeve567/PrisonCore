////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.enums;

import org.bukkit.ChatColor;

public enum Rarity {
	COMMON(ChatColor.GREEN, "Common"),
	UNCOMMON(ChatColor.DARK_GREEN, "Uncommon"),
	RARE(ChatColor.BLUE, "Rare"),
	MYSTICAL(ChatColor.DARK_PURPLE, "Mystical"),
	HYDRO(ChatColor.GOLD, "Legendary"),
	ADMIN(ChatColor.AQUA, "Admin");
	
	private final String label;
	private final ChatColor color;
	
	Rarity(ChatColor color, String label) {
		this.color = color;
		this.label = label;
	}
	
	public String getLabel() {
		return color + label;
	}
	
	public ChatColor getColor() {
		return color;
	}
}
