////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 6/17/18 2:08 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.listeners;

import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pw.xwy.prison_core.custom_enchants.menus.MainMenu;

public class NPCClick implements Listener {
	
	@EventHandler
	public void onRightClick(NPCRightClickEvent e) {
		onClick(e);
	}
	
	private void onClick(NPCClickEvent e) {
		if (e.getNPC().getName().contains("CE Menu") || e.getNPC().getName().contains("CustomEnchants")) {
			if (e.getClicker() != null) {
				e.getClicker().openInventory(new MainMenu().get());
			}
		}
	}
	
	@EventHandler
	public void onLeftClick(NPCLeftClickEvent e) {
		onClick(e);
	}
	
}
