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
import pw.xwy.prison_core.custom_enchants.CustomEnchantsHandler;

import java.util.ArrayList;
import java.util.List;

public enum ChangeLog {
	TOP(""),
	LINE2("&7Enchants Enabled: &d" + CustomEnchantsHandler.ceCount),
	LINE3("&7Latest Update: &7redid everything and put in prison core"),
	BOTTOM("");
	private String changes;
	
	ChangeLog(String s) {
		changes = s;
	}
	
	public static List<String> getStrings() {
		ArrayList<String> strings = new ArrayList<>();
		for (ChangeLog changeLog : ChangeLog.values()) {
			strings.add(changeLog.get());
		}
		return strings;
	}
	
	public String get() {
		return ChatColor.translateAlternateColorCodes('&', changes);
	}
}
