////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.menus;
// made by reeve
// on 6:53 PM

import pw.xwy.prison_core.custom_enchants.enums.ItemSets;

public class LeggingsMenu extends Menu {
	
	private static Menu menu;
	
	public LeggingsMenu() {
		super(ItemSets.LEGGINGS);
		menu = this;
	}
	
	public static Menu get() {
		return menu;
	}
	
}