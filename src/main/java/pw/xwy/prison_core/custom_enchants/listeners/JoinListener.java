////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.listeners;
// made by reeve
// on 11:45 PM

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.custom_enchants.enums.Rarity;
import pw.xwy.prison_core.custom_enchants.soulcrates.CommonSC;
import pw.xwy.prison_core.custom_enchants.soulcrates.CrateOpen;

public class JoinListener implements Listener {
	
	private CrateOpen crateOpen;
	private JavaPlugin main;
	
	JoinListener(CrateOpen crateOpen, JavaPlugin main) {
		this.crateOpen = crateOpen;
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		if (CommonSC.get().isRunningInventory(e.getPlayer())) {
			Bukkit.getScheduler().runTaskLater(main, () -> crateOpen.openCrate(e.getPlayer(), GorixClick.openCommon(), Rarity.COMMON, GorixClick.r + GorixClick.j), 1);
		}
	}
	
}
