////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.listeners;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.custom_enchants.soulcrates.CrateOpen;

public class CEListenerHandler {
	
	private JavaPlugin main;
	
	public CEListenerHandler(JavaPlugin main) {
		this.main = main;
	}
	
	public void Init() {
		CrateOpen crateOpen = new CrateOpen(main);
		register(
				new HitListener(main),
				new EnchantDrop(),
				new MenuClick(),
				new FeedListener(),
				//new ExplosionListener(),
				new DeathListener(),
				new BowListener(main),
				new ArmorListener(),
				new RespawnListener(),
				new SprintListener(),
				new JoinListener(crateOpen, main),
				new BlockListener(),
				new NPCClick()
		);
		
		//GorixClick gorixClick = new GorixClick(main);
		//register(gorixClick);
	}
	
	private void register(Listener... listeners) {
		for (Listener l : listeners) {
			main.getServer().getPluginManager().registerEvents(l, main);
		}
	}
}
