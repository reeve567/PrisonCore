////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.soulcrates;
// made by reeve
// on 9:16 PM

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.custom_enchants.enums.Rarity;
import pw.xwy.prison_core.custom_enchants.enums.Souls;
import pw.xwy.prison_core.custom_enchants.listeners.EnchantDrop;
import pw.xwy.prison_core.custom_enchants.schedules.crates.*;

import java.util.ArrayList;

public class CrateOpen {
	
	private JavaPlugin main;
	
	public CrateOpen(JavaPlugin main) {
		
		this.main = main;
	}
	
	public void openCrate(final Player p, final Inventory inv, Rarity type, int runs) {
		
		if (type.equals(Rarity.COMMON)) {
			final ArrayList<Prize> prizes = CommonSC.get().getPrizes();
			
			int r = EnchantDrop.getRandomNumberFrom(30, 50);
			int ra = EnchantDrop.getRandomNumberFrom(9, 12);
			
			CommonSC.get().setRunningInventory(p, inv);
			
			new CommonCrate(prizes, inv, p, runs, 10, false).runTaskTimer(main, 0, 7);
			new CommonCrate(prizes, inv, p, runs + 10, r, false).runTaskTimer(main, 70L, 4L);
			new CommonCrate(prizes, inv, p, runs + 10 + r, ra, false).runTaskTimer(main, 70 + (4 * r), 7);
			new CommonCrate(prizes, inv, p, runs + 10 + ra + r, 3, true).runTaskTimer(main, 70 + (7 * ra) + (4 * r), 12);
			
			new RewardPlayer(inv, p, Souls.COMMON).runTaskLater(main, 70 + (7 * ra) + (4 * r) + 36);
		} else if (type.equals(Rarity.UNCOMMON)) {
			final ArrayList<Prize> prizes = UncommonSC.get().getPrizes();
			
			int r = EnchantDrop.getRandomNumberFrom(30, 50);
			int ra = EnchantDrop.getRandomNumberFrom(9, 12);
			
			UncommonSC.get().setRunningInventory(p, inv);
			
			new UncommonCrate(prizes, inv, p, runs, 10, false).runTaskTimer(main, 0, 7);
			new UncommonCrate(prizes, inv, p, runs + 10, r, false).runTaskTimer(main, 70L, 4L);
			new UncommonCrate(prizes, inv, p, runs + 10 + r, ra, false).runTaskTimer(main, 70 + (4 * r), 7);
			new UncommonCrate(prizes, inv, p, runs + 10 + ra + r, 3, true).runTaskTimer(main, 70 + (7 * ra) + (4 * r), 12);
			
			new RewardPlayer(inv, p, Souls.UNCOMMON).runTaskLater(main, 70 + (7 * ra) + (4 * r) + 36);
		} else if (type.equals(Rarity.RARE)) {
			final ArrayList<Prize> prizes = RareSC.get().getPrizes();
			
			int r = EnchantDrop.getRandomNumberFrom(30, 50);
			int ra = EnchantDrop.getRandomNumberFrom(9, 12);
			
			RareSC.get().setRunningInventory(p, inv);
			
			new RareCrate(prizes, inv, p, runs, 10, false).runTaskTimer(main, 0, 7);
			new RareCrate(prizes, inv, p, runs + 10, r, false).runTaskTimer(main, 70L, 4L);
			new RareCrate(prizes, inv, p, runs + 10 + r, ra, false).runTaskTimer(main, 70 + (4 * r), 7);
			new RareCrate(prizes, inv, p, runs + 10 + ra + r, 3, true).runTaskTimer(main, 70 + (7 * ra) + (4 * r), 12);
			
			new RewardPlayer(inv, p, Souls.MYSTICAL).runTaskLater(main, 70 + (7 * ra) + (4 * r) + 36);
		} else if (type.equals(Rarity.MYSTICAL)) {
			final ArrayList<Prize> prizes = MysticalSC.get().getPrizes();
			
			int r = EnchantDrop.getRandomNumberFrom(30, 50);
			int ra = EnchantDrop.getRandomNumberFrom(9, 12);
			
			MysticalSC.get().setRunningInventory(p, inv);
			
			new MysticalCrate(prizes, inv, p, runs, 10, false).runTaskTimer(main, 0, 7);
			new MysticalCrate(prizes, inv, p, runs + 10, r, false).runTaskTimer(main, 70L, 4L);
			new MysticalCrate(prizes, inv, p, runs + 10 + r, ra, false).runTaskTimer(main, 70 + (4 * r), 7);
			new MysticalCrate(prizes, inv, p, runs + 10 + ra + r, 3, true).runTaskTimer(main, 70 + (7 * ra) + (4 * r), 12);
			
			new RewardPlayer(inv, p, Souls.MYSTICAL).runTaskLater(main, 70 + (7 * ra) + (4 * r) + 36);
		} else if (type.equals(Rarity.HYDRO)) {
			final ArrayList<Prize> prizes = HydroSC.get().getPrizes();
			
			int r = EnchantDrop.getRandomNumberFrom(30, 50);
			int ra = EnchantDrop.getRandomNumberFrom(9, 12);
			
			HydroSC.get().setRunningInventory(p, inv);
			
			new HydroCrate(prizes, inv, p, runs, 10, false).runTaskTimer(main, 0, 7);
			new HydroCrate(prizes, inv, p, runs + 10, r, false).runTaskTimer(main, 70L, 4L);
			new HydroCrate(prizes, inv, p, runs + 10 + r, ra, false).runTaskTimer(main, 70 + (4 * r), 7);
			new HydroCrate(prizes, inv, p, runs + 10 + ra + r, 3, true).runTaskTimer(main, 70 + (7 * ra) + (4 * r), 12);
			
			new RewardPlayer(inv, p, Souls.HYDRO).runTaskLater(main, 70 + (7 * ra) + (4 * r) + 36);
		}
	}
	
}
